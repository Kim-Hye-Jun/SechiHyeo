package com.ssafy.backend.api.service;


import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.dto.Room;
import com.ssafy.backend.dto.SessionRoom;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.request.RoomSetReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import com.ssafy.backend.dto.response.RoomSearchRes;
import com.ssafy.backend.dto.response.RoomSetRes;
import io.openvidu.java.client.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomServiceImpl implements RoomService {
    @Value("${thumbnail.path}")
    private String thumbnailPath;

    static class userInfo {
        String nickname;
        String side;
        String profileName;
        String profileUrl;
    }

    private final String DEFAULT_URL = "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6";


    //오픈비두 객체
    private OpenVidu openVidu;

    // 오픈비두 세션을 저장할 Map
//    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    //오픈비두 세션과 방 정보를 저장하는 Map..?
    private Map<String, SessionRoom> roomWithSession = new ConcurrentHashMap<>();

    //방과 방 내부 접속자를 저장할 Map
    //방에 접속할 때 배열을 앞에서부터 채워나가고(2*3으로 총 6칸) 방을 나가면 한 명씩 지우다 배열이 전부 null이 되면 해당 방 삭제하고 roomWithSession에서도 방 삭제
    private Map<String, String[][]> roomWithParticipant = new ConcurrentHashMap<>();

    // 오픈비두 서버 주소
    private String OPENVIDU_URL;
    // 오픈비두 서버와 공유

    private String OPENVIDU_SECRET;

    //방과 방에 따

    @Autowired
    public RoomServiceImpl(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
        this.OPENVIDU_SECRET = secret;
        //오픈비두 서버로 요청
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);

//        testMethod();
    }

    @Autowired
    public JWTUtil jwtUtil;

    @Autowired
    public MemberService memberService;

    private void testMethod() {
        Room room = Room.builder()
                .roomTitle("testTitle")
                .roomType("public")
                .debateTopic("너는 누구냐")
                .maxNumOfPeople(6)
                .roomId("openViduId")
                .password("0213")
                .sideA("나")
                .sideB("너")
                .thumbnail("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg")
                .build();


        for (int i = 0; i < 15; i++) {
            roomList.add(room);
        }
    }

    Map<String, Room> rooms = new ConcurrentHashMap<>();
    List<Room> roomList = new ArrayList<Room>();

    @Override
    public List<RoomSearchRes> getRooms(int pageInfo) {

        List<RoomSearchRes> tmp = new ArrayList<>();

        if (roomList.size() > (pageInfo - 1) * 6) {
            for (int i = Math.max(0, roomList.size() - 1 - (pageInfo - 1) * 6); i > roomList.size() - 1 - (pageInfo - 1) * 6 - 6 && i >= 0; i--) {
                tmp.add(roomList.get(i).convertToRoomSearchRes(roomList.get(i)));
            }
            return tmp;
        }
        return null;
    }

    @Override
    public List<Room> getRoomsWithKeyword(int pageInfo, String keyword) {
        List<Room> tmp = new ArrayList<Room>();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomTitle().contains(keyword))
                tmp.add(roomList.get(i));
        }
        List<Room> tmp2 = new ArrayList<Room>();
        if (tmp.size() > (pageInfo - 1) * 6) {
            for (int i = Math.max(0, tmp.size() - 1 - (pageInfo - 1) * 6 + 1); i > tmp.size() - 1 - (pageInfo - 1) * 6 + 1 + 5; i--) {
                tmp2.add(tmp.get(i));
            }
            return tmp2;
        }
        return null;
    }

    @Override
    public RoomCreateRes createRoom(RoomCreateReq roomCreateReq) {
        Room room = Room.builder()
                .roomTitle(roomCreateReq.getRoomTitle())
                .roomType(roomCreateReq.getRoomType())
                .roomId(String.valueOf(UUID.randomUUID()))
                .maxNumOfPeople(roomCreateReq.getHeadCount())
                .curNumOfPeople(0)
//                .thumbnail(thumbnailPath + saveName)
                .thumbnail(null)
                .debateTopic(roomCreateReq.getDebateTopic())
                .debateType(roomCreateReq.getDebateType())
                .password((roomCreateReq.getPassword()))
                .sideA(roomCreateReq.getSideA())
                .sideB(roomCreateReq.getSideB())
                .build();

        System.out.println(room);

        try {
//            SessionProperties properties = new SessionProperties.Builder().build();
            Session session = openVidu.createSession(new SessionProperties.Builder().build());

            //아직 방에 입장하지 않았으므로 Host는 빈 칸
            SessionRoom sessionRoom = new SessionRoom(session, room, "");
            roomWithSession.put(room.getRoomId(), sessionRoom);
            roomList.add(room);

            //roomWithSession 생성 - 인원수 길이만큼 배열 생성
            roomWithParticipant.put(room.getRoomId(), new String[2][(int) (room.getMaxNumOfPeople() / 2)]);

            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomCreateRes.builder()
                    .roomId(room.getRoomId())
                    .build();
        } catch (OpenViduJavaClientException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void uploadThumbnail(String roomId, MultipartFile thumbnail) {
        //썸네일 업로드
        //파일 업로드 경로 및 파일명
        String fileName = thumbnail.getOriginalFilename(); // 원본 파일 이름
        String saveName = UUID.randomUUID() + "_" + fileName; // UUID로 저장(파일명 중복 방지)

//        썸네일 업로드 테스트용
//        String thumbnailPath = System.getProperty("user.dir") + "/src/main/resources/static/thumbnail";
        try {
            //파일객체 생성 및 업로드
            File file = new File(thumbnailPath, saveName);
            if(!new File(thumbnailPath).exists())
                new File(thumbnailPath).mkdirs();
            thumbnail.transferTo(file);

            //기존 프로필 삭제(있으면)
//            if(member.getProfileName() != null) {
//                File deleteFile = new File(profilePath, member.getProfileName());
//                System.out.println(member.getProfileName());
//                if (deleteFile.exists()) deleteFile.delete();
//            }

//            //db에 프로필 이미지 정보 및 경로 저장
//            member.setProfileName(saveName);
//            member.setProfileUrl(profilePath + saveName);
//            memberRepository.save(member);

            //만들어진 방 객체를 찾아 thumbnail을 수정
            //roomWithSession, roomList
            roomWithSession.get(roomId).getRoom().setThumbnail(thumbnailPath+saveName);
            for (int i = 0; i < roomList.size(); i++) {
                if(roomList.get(i).getRoomId().equals(roomId)){
                    roomList.get(i).setThumbnail(thumbnailPath+saveName);
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RoomJoinRes joinRoom(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq) {
        //session_id를 파라미터로 받아와서 roomJoinReq 만들기

        System.out.println("==============ALL ROOMS=============");
        for (String s : roomWithSession.keySet()) {

            System.out.println(roomWithSession.get(s).getRoom());
        }
        System.out.println("ROOM JOIN ID => " + roomJoinReq.getRoomId());

        try {
            //세션 가져오기
            SessionRoom sessionRoom = roomWithSession.get(roomJoinReq.getRoomId());
            if (sessionRoom == null) throw new Error(roomJoinReq.getRoomId() + "not exists");
            Session session = sessionRoom.getSession();
            Room room = sessionRoom.getRoom();
            room.setCurNumOfPeople(room.getCurNumOfPeople() + 1);

            //JWT 토큰에서 사용자 정보 받아오기
            String memberToken = httpServletRequest.getHeader("access-token");
            System.out.println("TOKEN : " + memberToken);
            String loginId = jwtUtil.getInfo(memberToken).getLoginId();
            System.out.println(loginId);

            Member member = memberService.getInfoByLoginId(loginId);

            //json
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", member.getLoginId());
            jsonObject.put("nickname", member.getNickname());
            jsonObject.put("profileUrl", DEFAULT_URL);
//            jsonObject.put("profileName", member.getProfileName());
//            jsonObject.put("profileUrl", member.getProfileUrl());

            //접속자용 커넥션 생성
            OpenViduRole role = OpenViduRole.PUBLISHER;
            ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                    .type(ConnectionType.WEBRTC)
                    .role(role)
                    .data(jsonObject.toJSONString())
                    .build();

            System.out.println("1. " + connectionProperties.getData());

            System.out.println("TOKEN XXXXX");
            //토큰
            String token = session.createConnection(connectionProperties).getToken();
            System.out.println("TOKEN OOOOO");

            String[][] participants = roomWithParticipant.get(roomJoinReq.getRoomId());

            //방에 접속하는 첫 번째 사람이라면 해당 사람의 아이디를 방장 변수에 저장
            boolean checkHost = true;
            loop:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if (participants[i][j] != null) {
                        checkHost = false;
                        break loop;
                    }
                }
            }
            if (!checkHost)
                roomWithSession.get(roomJoinReq.getRoomId()).setHost(loginId);

            //서버에서 랜덤으로 진영 순서를 배정한 후에 그 값을 반환할 것
            //roomWithParticipant 가져와서 빈자리 하나 배정
            //A진영의 첫번째 순서라면 A1을 반환
            String sideOrder = "";
            loop:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if (participants[i][j] == null) {
                        participants[i][j] = loginId;
                        if (i == 0)
                            sideOrder += "a";
                        else
                            sideOrder += "b";
                        System.out.println("SIDEORDER : " + sideOrder);
                        sideOrder += (j + 1);
                        System.out.println("SIDEORDER : " + sideOrder);
                        break loop;
                    }
                }
            }

            //갱신한 접속자목록 반영
            roomWithParticipant.replace(roomJoinReq.getRoomId(), participants);
            System.out.println("2. " + connectionProperties.getData());
            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomJoinRes.builder()
                    .roomId(roomJoinReq.getRoomId())
                    .token(token)
                    .debateTopic(room.getDebateTopic())
                    .sideA(room.getSideA())
                    .sideB(room.getSideB())
                    .userSideOrder(sideOrder)
                    .isHost(checkHost)
                    .maxNumOfPeople(room.getMaxNumOfPeople())
                    .build();

        } catch (OpenViduJavaClientException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRoom(String OpenviduId) {
        //session 삭제
        List<Session> sessionList = openVidu.getActiveSessions();
        for (int i = 0; i < sessionList.size(); i++) {
            if (sessionList.get(i).getSessionId().equals(OpenviduId)) {
                try {
                    //openvidu session close
                    sessionList.get(i).close();
                    break;
                } catch (OpenViduJavaClientException e) {
                    throw new RuntimeException(e);
                } catch (OpenViduHttpException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //roomList 방 삭제
        for (int i = 0; i < roomList.size(); i++) {
            if(roomList.get(i).getRoomId().equals(OpenviduId)){
                roomList.remove(i);
            }
        }
    }

    //접속자가 방을 퇴장했을 때 back에서 관리하는 방 관련 Map에서 접속자를 삭제하기 위함
    @Override
    public void disconnectParticipant(String OpenviduId, String loginId) {
        //roomWithParticipant에서 세션 찾기
        String[][] participants = roomWithParticipant.get(OpenviduId);

        //for문을 돌면서 해당 id를 찾아 null로 바꾸기
        boolean check = false;
        for (int i = 0; i < 6; i++) {
            if(participants[i].equals(loginId)){
                participants[i] = null;
                break;
            }
            if(participants[i] != null){
                check = true;
            }
        }

        //if문으로 만약 배열의 모든 값들이 다 null이면 roomWithParticipant, roomWithSession에서 해당 세션 정보 삭제
        if(!check) {
            roomWithParticipant.remove(OpenviduId);
            roomWithSession.remove(OpenviduId);
            //세션 닫기
            this.deleteRoom(OpenviduId);
        }
    }

    //진영, 순서 설정
    @Override
    public void setSideOrder(RoomSetReq roomSetReq) {
        //RoomWithSession 객체에서 찾아 Room 객체 갱신
        SessionRoom sessionRoom = roomWithSession.get(roomSetReq.getRoomId());
        //토론 포맷 수정
        sessionRoom.getRoom().setDebateFormat(roomSetReq.getDebateFormat());

        //RoomWithMember 객체에서 찾아 토론 참여자 배열에 넣기
        String[][] participants = roomWithParticipant.get(roomSetReq.getRoomId());
        //A진영 참가자
        for (int a = 0; a < roomSetReq.getAParticipants().length; a++) {
            participants[0][a] = roomSetReq.getAParticipants()[a];
        }
        //B진영 참가자
        for (int b = 0; b < roomSetReq.getBParticipants().length; b++) {
            participants[0][b] = roomSetReq.getAParticipants()[b];
        }

        roomWithParticipant.replace(roomSetReq.getRoomId(), participants);

    }

    //토론 규칙
    @Override
    public RoomSetRes setDebateFormat(RoomSetReq roomSetReq) {
        //방 Dto의 debateFormat 수정
        roomWithSession.get(roomSetReq.getRoomId()).getRoom().setDebateFormat(roomSetReq.getDebateFormat());

        //토론 규칙을 json으로 만들어 넣고 return
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        switch (roomSetReq.getDebateFormat()){
            //1:1토론
            case "Lincoln-Douglas":
                String[][] LDorder = {
                        {"A", "1", "Constructive",  "6"},
                        {"B", "1", "CrossExamination", "3"},
                        {"B", "1", "Constructive_And_NegativeRebuttal", "7"},
                        {"A", "1", "CrossExamination", "3"},
                        {"A", "1", "FirstRebuttal", "4"},
                        {"B", "1", "NegativeRebuttal", "6"},
                        {"A", "1", "SecondRebuttal", "3"}
                };

                for (int i = 0; i < LDorder.length; i++) {
                    JSONObject data = new JSONObject();
                    data.put("side", LDorder[i][0]);
                    data.put("order", LDorder[i][1]);
                    data.put("role", LDorder[i][2]);
                    data.put("time", LDorder[i][3]);

                    jsonArray.add(data);
                }

                break;
            //2:2토론
            case "CEDA":
                String[][] CEDAorder = {
                        {"A", "1", "Constructive",  "9"},
                        {"B", "2", "CrossExamination", "3"},
                        {"B", "1", "Constructive", "9"},
                        {"A", "1", "CrossExamination", "3"},
                        {"A", "2", "Constructive", "9"},
                        {"B", "1", "CrossExamination", "3"},
                        {"B", "2", "Constructive", "9"},
                        {"A", "2", "CrossExamination", "3"},
                        {"all", "all", "Free", "6"}
                };

                for (int i = 0; i < CEDAorder.length; i++) {
                    JSONObject data = new JSONObject();
                    data.put("side", CEDAorder[i][0]);
                    data.put("order", CEDAorder[i][1]);
                    data.put("role", CEDAorder[i][2]);
                    data.put("time", CEDAorder[i][3]);

                    jsonArray.add(data);
                }
                break;
            //3:3토론
            case "KarlPopper":
                String[][] KPorder = {
                        {"A", "1", "Constructive",  "6"},
                        {"B", "3", "CrossExamination", "3"},
                        {"B", "1", "Constructive", "6"},
                        {"A", "3", "CrossExamination", "3"},
                        {"A", "2", "Rebuttal", "5"},
                        {"B", "1", "CrossExamination", "3"},
                        {"B", "2", "Rebuttal", "5"},
                        {"A", "1", "CrossExamination", "3"},
                        {"A", "3", "Rebuttal", "5"},
                        {"B", "3", "Rebuttal", "5"}
                };

                for (int i = 0; i < KPorder.length; i++) {
                    JSONObject data = new JSONObject();
                    data.put("side", KPorder[i][0]);
                    data.put("order", KPorder[i][1]);
                    data.put("role", KPorder[i][2]);
                    data.put("time", KPorder[i][3]);

                    jsonArray.add(data);
                }
                break;
        }

        jsonObject.put("debateOrder", jsonArray);
        return new RoomSetRes(roomSetReq.getRoomId(), jsonObject);
    }

    //방 내부 비어있는 진영, 순서 반환
    @Override
    public String[][] validSideOrder(String OpenviduId) {

        //오픈비두 세션 아이디로 RoomWithParticipant 찾기
        //해당 배열 반환
        return roomWithParticipant.get(OpenviduId);
    }

    @Override
    public void sendSignal(String OpenviduId) {
        try {
            URL url = new URL("https://i7a508.p.ssafy.io:8443/openvidu/api/signal");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("POST"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString("OPENVIDUAPP:MY_SECRET".getBytes())); // header의 auth 정보
            conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            // 서버에 데이터 전달
            String[][] participants = roomWithParticipant.get(OpenviduId);
//            String[][] participants = new String[2][3];

            System.out.println(participants[0].length);

            JSONArray jsonArray = new JSONArray();
            int count = participants[0].length;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < count; j++) {
                    JSONObject jsonObject = new JSONObject();

                    if(i == 0)
                        jsonObject.put("side", "A");
                    else jsonObject.put("side", "B");
                    jsonObject.put("order", j+1);
                    jsonObject.put("user", participants[i][j]);

                    jsonArray.add(jsonObject);
                }
            }

//            ArrayList<String> al = new ArrayList<>();

            JSONObject obj = new JSONObject();
            obj.put("session", OpenviduId);
//            obj.put("to", al);
            obj.put("type", "UPDATE_SIDE_ORDER");
            obj.put("data", jsonArray.toJSONString());

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(obj.toJSONString()); // 버퍼에 담기
            System.out.println("obj : " + obj.toJSONString());
            bw.flush(); // 버퍼에 담긴 데이터 전달
            bw.close();

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            if (responseCode == 200) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

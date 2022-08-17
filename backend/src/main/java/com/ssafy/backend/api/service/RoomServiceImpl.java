package com.ssafy.backend.api.service;


import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.dto.Room;
import com.ssafy.backend.dto.SessionRoom;
import com.ssafy.backend.dto.SideOrderInfo;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.request.RoomSetReq;
import com.ssafy.backend.dto.request.RoomUpdateUserSideOrderReq;
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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomServiceImpl implements RoomService {

    //서버(백엔드) 파일 업로드 경로
    @Value("${server.file.path}")
    private String fileUploadPath;
    //클라이언트(프론트 엔드) 파일 접근 경로
    @Value("${client.file.path}")
    private String fileUrl;

    //오픈비두 객체
    private OpenVidu openVidu;

    //오픈비두 세션과 방 정보를 저장하는 Map
    private Map<String, SessionRoom> roomWithSession = new ConcurrentHashMap<>();

    //방과 방 내부 접속자를 저장할 Map
    //방에 접속할 때 배열을 앞에서부터 채워나가고(2*3으로 총 6칸) 방을 나가면 한 명씩 지우다 배열이 전부 null이 되면 해당 방 삭제하고 roomWithSession에서도 방 삭제
    private Map<String, String[][]> roomWithParticipant = new ConcurrentHashMap<>();

    // 오픈비두 서버 주소
    private String OPENVIDU_URL;
    // 오픈비두 서버와 공유
    private String OPENVIDU_SECRET;

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

    //사용되지 않고 있는 map
//    Map<String, Room> rooms = new ConcurrentHashMap<>();
    List<Room> roomList = new ArrayList<Room>();

    @Override
    public List<RoomSearchRes> getRooms(int pageInfo) {

        List<RoomSearchRes> tmp = new ArrayList<>();

        if (roomList.size() > (pageInfo - 1) * 6) {
            for (int i = Math.max(0, roomList.size() - 1 - (pageInfo - 1) * 6); i > roomList.size() - 1 - (pageInfo - 1) * 6 - 6 && i >= 0; i--) {
                RoomSearchRes roomSearchRes = roomList.get(i).convertToRoomSearchRes(roomList.get(i));

                //sideOrderList를 만들기 위해 roomWithParticipant에서 찾는다.
                ArrayList<SideOrderInfo> sideOrderList = new ArrayList<>();
                String[][] participants = roomWithParticipant.get(roomList.get(i).getRoomId());
                for (int j = 0; j < participants[0].length; j++) {
                    for (int k = 0; k < 2; k++) {
                        SideOrderInfo sideOrderInfo = new SideOrderInfo();
                        //A진영이면 A, B진영이면 B를 sideOrder에 저장
                        //순서에 따라 sideOrder에 저장
                        if(k==0){
                            sideOrderInfo.setSideOrder("a"+(j+1));
                        } else {
                            sideOrderInfo.setSideOrder("b"+(j+1));
                        }

                        //해당 칸이 ""이면 isEmpty를 true, 아니면 false로 저장
                        if(participants[k][j].equals("")){
                            sideOrderInfo.setEmpty(true);
                        } else {
                            sideOrderInfo.setEmpty(false);
                        }

                        sideOrderList.add(sideOrderInfo);
                    }
                }

                //테스트용 출력
                System.out.println("sideOrderList : " + sideOrderList);
                roomSearchRes.setSideOrderList(sideOrderList);
                tmp.add(roomSearchRes);
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
        //여기도 getRooms처럼 반환해줘야함
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

        System.out.println("만들었을 때 방 인원수 : " + room.getCurNumOfPeople());

        try {
//            SessionProperties properties = new SessionProperties.Builder().build();
            //카메라용, 화면공유용 세션 2개 생성
            Session sessionCamera = openVidu.createSession(new SessionProperties.Builder().build());
//            Session sessionScreen = openVidu.createSession(new SessionProperties.Builder().build());

            //아직 방에 입장하지 않았으므로 Host는 빈 칸
            SessionRoom sessionRoom = new SessionRoom(sessionCamera, room, "");
            roomWithSession.put(room.getRoomId(), sessionRoom);
            roomList.add(room);

            //roomWithParticipant 생성 - 인원수 길이만큼 배열 생성
            String[][] participants = new String[2][(int) (room.getMaxNumOfPeople() / 2)];
            //문자열 배열을 모두 ""으로 초기화
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    participants[i][j] = "";
                }
            }
            roomWithParticipant.put(room.getRoomId(), participants);

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
        //썸네일 업로드 폴더명
        String thumbnail1Dir = "thumbnail";

        //파일 업로드 경로 및 파일명
        String fileName = thumbnail.getOriginalFilename(); // 원본 파일 이름
        String saveName = UUID.randomUUID() + "_" + fileName; // UUID로 저장(파일명 중복 방지)

//        썸네일 업로드 테스트용
//        String thumbnailPath = System.getProperty("user.dir") + "/src/main/resources/static/thumbnail";
        try {
            //파일객체 생성 및 업로드
            File file = new File(fileUploadPath + thumbnail1Dir, saveName);
            if (!new File(fileUploadPath + thumbnail1Dir).exists())
                new File(fileUploadPath + thumbnail1Dir).mkdirs();
            thumbnail.transferTo(file);

            //만들어진 방 객체를 찾아 thumbnail을 수정 (roomWithSession, roomList에서 같은 Room 객체 사용하므로, 하나만 수정)
            roomWithSession.get(roomId).getRoom().setThumbnail(fileUrl + thumbnail1Dir + "/" + saveName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadProof(MultipartFile proof) {
        //자료 파일 업로드 폴더명
        String proofDir = "proof";

        //파일 업로드 경로 및 파일명
        String fileName = proof.getOriginalFilename(); // 원본 파일 이름
        String saveName = UUID.randomUUID() + "_" + fileName; // UUID로 저장(파일명 중복 방지)

        String proofUrl = "";

        try {
            //파일객체 생성 및 업로드
            File file = new File(fileUploadPath + proofDir, saveName);
            if (!new File(fileUploadPath + proofDir).exists())
                new File(fileUploadPath + proofDir).mkdirs();
            proof.transferTo(file);

            proofUrl = fileUrl + proofDir + "/" + saveName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return proofUrl;
    }

    @Override
    public RoomJoinRes joinRoom_random(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq) {
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

            Session sessionCamera = sessionRoom.getSessionCamera();
//            Session sessionScreen = sessionRoom.getSessionScreen();

            //방 정보 가져오기
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
            jsonObject.put("profileUrl", member.getProfileUrl());

            //접속자용 커넥션 생성
            OpenViduRole role = OpenViduRole.PUBLISHER;
            ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                    .type(ConnectionType.WEBRTC)
                    .role(role)
                    .data(jsonObject.toJSONString())
                    .build();

            System.out.println("1. " + connectionProperties.getData());

            System.out.println("TOKEN XXXXX");

            //토큰 2개 생성
            String tokenCamera = sessionCamera.createConnection(connectionProperties).getToken();
//            String tokenScreen = sessionScreen.createConnection(connectionProperties).getToken();

            System.out.println("TOKEN OOOOO");

            String[][] participants = roomWithParticipant.get(roomJoinReq.getRoomId());

            //방에 접속하는 첫 번째 사람이라면 해당 사람의 아이디를 방장 변수에 저장
            boolean checkHost = true;
            loop:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if (!participants[i][j].equals("")) {
                        checkHost = false;
                        break loop;
                    }
                }
            }
            //checkHost가 최종적으로 true라면 모든 칸이 ""이었으므로 내가 방장
            if (checkHost)
                roomWithSession.get(roomJoinReq.getRoomId()).setHost(loginId);

            //서버에서 랜덤으로 진영 순서를 배정한 후에 그 값을 반환할 것
            //roomWithParticipant 가져와서 빈자리 하나 배정
            //A진영의 첫번째 순서라면 A1을 반환
            String sideOrder = "";
            loop:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if (participants[i][j].equals("")) {
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

            List<String> emptySideOrderList = getEmptySideOrderList(participants);

            //갱신한 접속자목록 반영
            roomWithParticipant.replace(roomJoinReq.getRoomId(), participants);
            System.out.println("2. " + connectionProperties.getData());
            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomJoinRes.builder()
                    .roomId(roomJoinReq.getRoomId())
                    .tokenCamera(tokenCamera)
//                    .tokenScreen(tokenScreen)
                    .sideA(room.getSideA())
                    .sideB(room.getSideB())
                    .nickname(member.getNickname())
                    .userSideOrder(sideOrder)
                    .isRoomHost(checkHost)
                    .currentNumOfPeople((room.getCurNumOfPeople()))
                    .maxNumOfPeople(room.getMaxNumOfPeople())
                    .emptySideOrderList(emptySideOrderList)
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
    public RoomJoinRes joinRoom_select(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq) {
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

            Session sessionCamera = sessionRoom.getSessionCamera();
//            Session sessionScreen = sessionRoom.getSessionScreen();

            //방 정보 가져오기
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
            jsonObject.put("profileUrl", member.getProfileUrl());
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

            //토큰 2개 생성
            String tokenCamera = sessionCamera.createConnection(connectionProperties).getToken();
//            String tokenScreen = sessionScreen.createConnection(connectionProperties).getToken();

            System.out.println("TOKEN OOOOO");

            String[][] participants = roomWithParticipant.get(roomJoinReq.getRoomId());

            //방에 접속하는 첫 번째 사람이라면 해당 사람의 아이디를 방장 변수에 저장
            boolean checkHost = true;
            loop:
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if (!participants[i][j].equals("")) {
                        checkHost = false;
                        break loop;
                    }
                }
            }
            //checkHost가 최종적으로 true라면 모든 칸이 ""이었으므로 내가 방장
            if (checkHost)
                roomWithSession.get(roomJoinReq.getRoomId()).setHost(loginId);

            String sideOrder = "";
            //participants에서 사용자가 요청한 진영순서 칸에 loginId가 있으면 에러, 없으면 사용자를 저장
            if(roomJoinReq.getSide().equals("a")){
                if(participants[0][roomJoinReq.getOrder()-1].equals("")) {
                    participants[0][roomJoinReq.getOrder()-1] = member.getLoginId();
                    sideOrder+="a"+(roomJoinReq.getOrder());
                } else {
//                    System.out.println("이미 다른 사용자가 배정되었습니다.");
                    return RoomJoinRes.builder().userSideOrder("error").build();
                }

            } else if(roomJoinReq.getSide().equals("b")){
                if(participants[1][roomJoinReq.getOrder()-1].equals("")) {
                    participants[1][roomJoinReq.getOrder()-1] = member.getLoginId();
                    sideOrder+="b"+(roomJoinReq.getOrder());
                } else {
//                    System.out.println("이미 다른 사용자가 배정되었습니다.");
                    return RoomJoinRes.builder().userSideOrder("error").build();
                }
            }

            List<String> emptySideOrderList = getEmptySideOrderList(participants);

            //갱신한 접속자목록 반영
            roomWithParticipant.replace(roomJoinReq.getRoomId(), participants);
            System.out.println("2. " + connectionProperties.getData());
            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomJoinRes.builder()
                    .roomId(roomJoinReq.getRoomId())
                    .tokenCamera(tokenCamera)
//                    .tokenScreen(tokenScreen)
                    .debateTopic(room.getDebateTopic())
                    .sideA(room.getSideA())
                    .sideB(room.getSideB())
                    .nickname(member.getNickname())
                    .userSideOrder(sideOrder)
                    .isRoomHost(checkHost)
                    .currentNumOfPeople((room.getCurNumOfPeople()))
                    .maxNumOfPeople(room.getMaxNumOfPeople())
                    .emptySideOrderList(emptySideOrderList)
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
    public void deleteRoom(String roomId) {
        //session 삭제
        List<Session> sessionList = openVidu.getActiveSessions();
        for (int i = 0; i < sessionList.size(); i++) {
            if (sessionList.get(i).getSessionId().equals(roomId)) {
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
            if (roomList.get(i).getRoomId().equals(roomId)) {
                roomList.remove(i);
            }
        }

        //roomWithParticipant 삭제
        roomWithParticipant.remove(roomId);
    }

    //접속자가 방을 퇴장했을 때 back에서 관리하는 방 관련 Map에서 접속자를 삭제하기 위함
    @Override
    public void disconnectParticipant(String roomId, String loginId) {
        //방 객체를 찾아서 curNumOfPeople -1 해서 저장하기
        //roomWithSession
        Room room = roomWithSession.get(roomId).getRoom();
        room.setCurNumOfPeople(room.getCurNumOfPeople() - 1);

        //roomWithParticipant에서 세션 찾기
        String[][] participants = roomWithParticipant.get(roomId);

        //for문을 돌면서 해당 id를 찾아 ""으로 바꾸기
        boolean check = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < participants[0].length; j++) {
                if (participants[i][j].equals(loginId)) {
                    participants[i][j] = "";
                    break;
                }
                if (!participants[i][j].equals("")) {
                    check = true;
                }
            }
        }

        //if문으로 만약 배열의 모든 값들이 다 ""이면 roomWithParticipant, roomWithSession에서 해당 세션 정보 삭제
        if (!check) {
            roomWithParticipant.remove(roomId);
            roomWithSession.remove(roomId);
            //세션 닫기
            this.deleteRoom(roomId);
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
        switch (roomSetReq.getDebateFormat()) {
            //1:1토론
            case "Lincoln-Douglas":
                String[][] LDorder = {
                        {"A", "1", "Constructive", "6"},
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
                        {"A", "1", "Constructive", "9"},
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
                        {"A", "1", "Constructive", "6"},
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
    public String[][] validSideOrder(String roomId) {
        //오픈비두 세션 아이디로 RoomWithParticipant 찾기
        //해당 배열 반환
        return roomWithParticipant.get(roomId);
    }

    @Override
    public void sendSignal(RoomUpdateUserSideOrderReq roomUpdateUserSideOrderReq) {
        try {
            String roomId = roomUpdateUserSideOrderReq.getRoomId();

            URL url = new URL("https://i7a508.p.ssafy.io:8443/openvidu/api/signal");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString("OPENVIDUAPP:MY_SECRET".getBytes())); // header의 auth 정보
            conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            // 서버에 데이터 전달
            String[][] participants = roomWithParticipant.get(roomId);
//            String[][] participants = new String[2][3];


            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("userId", roomUpdateUserSideOrderReq.getLoginId());
            jsonObject.put("preSideOrder", roomUpdateUserSideOrderReq.getPreSideOrder());
            jsonObject.put("newSideOrder", roomUpdateUserSideOrderReq.getNewSideOrder());

            // room with participant 변경
            String preSideOrder = roomUpdateUserSideOrderReq.getPreSideOrder();
            String newSideOrder = roomUpdateUserSideOrderReq.getNewSideOrder();

            // 서로 위치 바꾸기
            System.out.println(preSideOrder.charAt(0));
            System.out.println(preSideOrder.charAt(0) - 'a');
            System.out.println((int) preSideOrder.charAt(0) - 'a');

            System.out.println(preSideOrder.charAt(1));
            System.out.println(preSideOrder.charAt(1) - '1');
            System.out.println((int) preSideOrder.charAt(1) - '1');

            System.out.println(roomWithParticipant.get(roomId)[(int) preSideOrder.charAt(0) - 'a'][(int) preSideOrder.charAt(1) - '1']);
            System.out.println(roomWithParticipant.get(roomId)[(int) newSideOrder.charAt(0) - 'a'][(int) newSideOrder.charAt(1) - '1']);

            roomWithParticipant.get(roomId)[preSideOrder.charAt(0) - 'a'][preSideOrder.charAt(1) - '1']
                    = swap(roomWithParticipant.get(roomId)[preSideOrder.charAt(0) - 'a'][preSideOrder.charAt(1) - '1']
                            = roomWithParticipant.get(roomId)[newSideOrder.charAt(0) - 'a'][newSideOrder.charAt(1) - '1']
                    , roomWithParticipant.get(roomId)[newSideOrder.charAt(0) - 'a'][newSideOrder.charAt(1) - '1']);

            JSONObject obj = new JSONObject();
            //세션은 2개이지만 카메라용 세션만 저장
            obj.put("sessionCamera", roomWithSession.get(roomId).getSessionCamera().getSessionId());
//            obj.put("to", al);
            obj.put("type", "UPDATE_SIDE_ORDER");
            obj.put("data", jsonObject.toJSONString());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(obj.toJSONString()); // 버퍼에 담기
//            System.out.println("obj : " + obj.toJSONString());
            bw.flush(); // 버퍼에 담긴 데이터 전달
            bw.close();

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            if (responseCode == 200) {
                System.out.println("success send signal");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDebateInfo(String roomId) {
        //roomId로 roomWithParticipant에서 접속자 배열 찾기
        String[][] participants = roomWithParticipant.get(roomId);

        //빈 문자열이 아니라 사용자 loginId가 들어있다면 memberService 호출
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < participants[0].length; j++) {
                if(!participants[i][j].equals(""))
                    memberService.changeDebateInfo(participants[i][j]);
            }
        }
    }

    @Override
    public int getPageNumber() {
        return roomList.size();
    }

    @Override
    public List<Room> getAllRoomInfo() {
        return roomList;
    }

    private String swap(String a, String b) {
        return b;
    }

    private List<String> getEmptySideOrderList(String[][] participants) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < participants[0].length; j++) {
                if (participants[i][j].equals("")) {
                    returnList.add(String.format("%c%d", 'a' + i, j + 1)); // j=0,1,2 -> a123 b123
                }
            }
        }
        return returnList;
    }

}

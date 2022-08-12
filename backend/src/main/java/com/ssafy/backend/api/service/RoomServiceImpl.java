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

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomServiceImpl implements RoomService {
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

        testMethod();
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
                .headCount(6)
                .openviduId("openViduId")
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
            for (int i = Math.max(0, roomList.size() - 1 - (pageInfo - 1) * 6); i > roomList.size() - 1 - (pageInfo - 1) * 6 - 6; i--) {
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
                .openviduId(String.valueOf(UUID.randomUUID()))
                .headCount(roomCreateReq.getHeadCount())
                .thumbnail(DEFAULT_URL)
                .debateTopic(roomCreateReq.getDebateTopic())
                .debateType(roomCreateReq.getDebateType())
                .password((roomCreateReq.getPassword()))
                .sideA(roomCreateReq.getSideA())
                .sideB(roomCreateReq.getSideB())
                .build();

        try {
//            SessionProperties properties = new SessionProperties.Builder().build();
            Session session = openVidu.createSession(new SessionProperties.Builder().build());

            //아직 방에 입장하지 않았으므로 Host는 빈 칸
            SessionRoom sessionRoom = new SessionRoom(session, room, "");
            roomWithSession.put(session.getSessionId(), sessionRoom);

            //roomWithSession 생성 - 인원수 길이만큼 배열 생성
            roomWithParticipant.put(room.getOpenviduId(), new String[2][(int) (room.getHeadCount()/2)]);

            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomCreateRes.builder()
                    .OpenviduId(session.getSessionId())
//                    .token(token)
//                    .nickname("nickname")
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
    public RoomJoinRes joinRoom(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq) {
        //session_id를 파라미터로 받아와서 roomJoinReq 만들기

        try {
            //세션 가져오기
            Session session = roomWithSession.get(roomJoinReq.getOpenviduId()).getSession();

            //JWT 토큰에서 사용자 정보 받아오기
            String memberToken = httpServletRequest.getHeader("access-token");
            String loginId = jwtUtil.getInfo(memberToken).getLoginId();

            Member member = memberService.getInfoByLoginId(loginId);

            //json
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("loginId", member.getLoginId());
            jsonObject.put("nickname", member.getNickname());
//            jsonObject.put("side", "side");
//            jsonObject.put("order", "order");
            jsonObject.put("profileName", member.getProfileName());
            jsonObject.put("profileUrl", member.getProfileUrl());

            //접속자용 커넥션 생성
            OpenViduRole role = OpenViduRole.PUBLISHER;
            ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                    .type(ConnectionType.WEBRTC)
                    .role(role)
                    .data(jsonObject.toJSONString())
                    .build();

            //토큰
            String token = session.createConnection(connectionProperties).getToken();

            String[][] participants = roomWithParticipant.get(roomJoinReq.getOpenviduId());

            //방에 접속하는 첫 번째 사람이라면 해당 사람의 아이디를 방장 변수에 저장
            boolean check = false;
            loop : for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if(participants[i][j] != null){
                        check = true;
                        break loop;
                    }
                }
            }
            if(!check)
                roomWithSession.get(roomJoinReq.getOpenviduId()).setHost(loginId);

            //서버에서 랜덤으로 진영 순서를 배정한 후에 그 값을 반환할 것
            //roomWithParticipant 가져와서 빈자리 하나 배정
            //A진영의 첫번째 순서라면 A1을 반환
            String sideOrder = "";
            loop : for (int i = 0; i < 2; i++) {
                for (int j = 0; j < participants[0].length; j++) {
                    if(participants[i][j] == null){
                        participants[i][j] = loginId;
                        if (i == 0)
                            sideOrder+="A";
                        else
                            sideOrder+="B";
                        sideOrder+=(j+1);
                        break loop;
                    }
                }
            }

            //갱신한 접속자목록 반영
            roomWithParticipant.replace(roomJoinReq.getOpenviduId(), participants);

            //세션 아이디와 토큰, 사용자 닉네임을 반환할 것
            return RoomJoinRes.builder()
                    .OpenviduId(session.getSessionId())
                    .token(token)
                    .nickname(member.getNickname())
                    .sideOrder(sideOrder)
                    .build();

        } catch (OpenViduJavaClientException e) {
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRoom(String OpenviduId) {
        List<Session> sessionList = openVidu.getActiveSessions();
        for (int i = 0; i < sessionList.size(); i++) {
            if (sessionList.get(i).getSessionId().equals(OpenviduId)) {
                try {
                    sessionList.get(i).close();
                    break;
                } catch (OpenViduJavaClientException e) {
                    throw new RuntimeException(e);
                } catch (OpenViduHttpException e) {
                    throw new RuntimeException(e);
                }
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
        SessionRoom sessionRoom = roomWithSession.get(roomSetReq.getOpenviduId());
        //토론 포맷 수정
        sessionRoom.getRoom().setDebateFormat(roomSetReq.getDebateFormat());

        //RoomWithMember 객체에서 찾아 토론 참여자 배열에 넣기
        String[][] participants = roomWithParticipant.get(roomSetReq.getOpenviduId());
        //A진영 참가자
        for (int a = 0; a < roomSetReq.getAParticipants().length; a++) {
            participants[0][a] = roomSetReq.getAParticipants()[a];
        }
        //B진영 참가자
        for (int b = 0; b < roomSetReq.getBParticipants().length; b++) {
            participants[0][b] = roomSetReq.getAParticipants()[b];
        }

        roomWithParticipant.replace(roomSetReq.getOpenviduId(), participants);

    }

    //토론 규칙
    @Override
    public RoomSetRes setDebateFormat(RoomSetReq roomSetReq) {
        //방 Dto의 debateFormat 수정
        roomWithSession.get(roomSetReq.getOpenviduId()).getRoom().setDebateFormat(roomSetReq.getDebateFormat());

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
        return new RoomSetRes(roomSetReq.getOpenviduId(), jsonObject);
    }

    //방 내부 비어있는 진영, 순서 반환
    @Override
    public String[][] validSideOrder(String OpenviduId) {

        //오픈비두 세션 아이디로 RoomWithParticipant 찾기
        //해당 배열 반환
        return roomWithParticipant.get(OpenviduId);
    }



}

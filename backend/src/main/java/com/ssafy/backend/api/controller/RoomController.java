package com.ssafy.backend.api.controller;

import com.ssafy.backend.dto.Room;
import io.openvidu.java.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/api/sessions")
public class RoomController {

//    // 오픈비두 객체
    private OpenVidu openVidu;

    // 오픈비두 세션을 저장할 Map
    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    // 오픈비두 서버 주소
    private String OPENVIDU_URL;
    // 오픈비두 서버와 공유
    private String OPENVIDU_SECRET;

    @Autowired
    public RoomController(@Value("MY_SECRET") String secret, @Value("https://localhost:4443/") String openviduUrl) {
        this.OPENVIDU_SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    // openvidu room for test

    // 1. 방 조회
    @GetMapping("/")
    public ResponseEntity<List<Room>> getAllRoom(){
        Room room1 = new Room("openvidu_ID", "TITLE_A", 6, "public", "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6");
        Room room2 = new Room("openvidu_ID", "TITLE_B", 6, "private", "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6");
        Room room3 = new Room("openvidu_ID", "TITLE_C", 6, "public", "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6");
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        list.add(room3);
        return ResponseEntity.ok(list);
    }

    // 2. 방 검색
    @GetMapping("/{room_title}")
    public ResponseEntity<Room> findRoomById(@PathVariable String room_title){
        Room room = new Room("openvidu_ID", room_title, 6, "public", "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6");
        return ResponseEntity.ok(room);
    }

    // 3. 방 생성
    @PostMapping("/")
    public ResponseEntity<Room> createRoom(){
        Room room = new Room("openvidu_ID", "TITLE_D", 6, "public", "https://us.123rf.com/450wm/isselee/isselee1312/isselee131200018/24155958-%EC%82%AC%EC%9E%90%EC%9D%98-%EC%84%9C-%ED%9D%B0%EC%83%89%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%90%9C-10-%EC%84%B8-%ED%91%9C%EB%B2%94-%EC%86%8D-%EB%A0%88%EC%98%A4-.jpg?ver=6");

        OpenVidu openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
        SessionProperties properties = new SessionProperties.Builder().build();
        try {
            Session session = openVidu.createSession(properties);
        } catch (OpenViduJavaClientException e) {
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(room);
    }

    // 4. 방 참가
    @GetMapping("/{session_id}/connection")
    public ResponseEntity joinRoom(@PathVariable String session_id){
//        List<Session> sessions = openVidu.getActiveSessions();
//        Session session = null;
//        for (Session s : sessions) {
//            if(s.getSessionId().equals(session_id))
//                session = s;
//        }
////        Connection conn = new Connection();
//
//        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
//                .type(ConnectionType.WEBRTC)
//                .role(OpenViduRole.PUBLISHER)
//                .data("user_data")
//                .build();
//        Connection connection = null;
//        try {
//            connection = session.createConnection(connectionProperties);
//        } catch (OpenViduJavaClientException e) {
//            throw new RuntimeException(e);
//        } catch (OpenViduHttpException e) {
//            throw new RuntimeException(e);
//        }
//        String token = connection.getToken(); // Send this string to the client side

        return new ResponseEntity<>(HttpStatus.OK);
    }



}

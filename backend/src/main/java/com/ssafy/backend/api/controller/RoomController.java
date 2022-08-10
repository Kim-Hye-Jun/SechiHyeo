package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.RoomService;
import com.ssafy.backend.dto.Room;
//import com.ssafy.backend.db.entity.Room;
import com.ssafy.backend.dto.SessionRoom;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import io.openvidu.java.client.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/sessions")
public class RoomController {

    @Autowired
    public RoomService roomService;

    // 1. 전체 방 조회
    @GetMapping("/{page_info}")
    public ResponseEntity<List<Room>> getAllRoom(@PathVariable("page_info") int pageInfo){
        List<Room> list = roomService.getRooms(pageInfo);
        return ResponseEntity.ok(list);
    }

    // 2. 키워드로 방 검색
    @GetMapping("/{page_info}/{keyword}")
    public ResponseEntity<List<Room>> getRoomByKeyword(@PathVariable("page_info") int pageInfo, @PathVariable("keyword") String keyword) {
        List<Room> list = roomService.getRoomsWithKeyword(pageInfo, keyword);
        return ResponseEntity.ok(list);
    }

    // 3. 방 생성
    @PostMapping("/")
    public ResponseEntity<RoomCreateRes> createRoom(RoomCreateReq roomCreateReq){
        RoomCreateRes roomCreateRes = roomService.createRoom(roomCreateReq);
        return ResponseEntity.ok(roomCreateRes);
    }

    // 4. 방 참가
    @GetMapping("/{session_id}/connection")
    public ResponseEntity<RoomJoinRes> joinRoom(@PathVariable String session_id){
        //질문!!!!!!!!!!!!
        //JWT로 회원정보 받아와서 RoomJoinReq 만들기
        RoomJoinReq roomJoinReq = RoomJoinReq.builder()
                .OpenviduId(session_id)
                .nickname("nickname")
                .side(1)
                .order(1)
                .profileName("profilename")
                .profileUrl("data:image/png;base64,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").build();

        RoomJoinRes roomJoinRes = roomService.joinRoom(roomJoinReq);

        return ResponseEntity.ok(roomJoinRes);
    }

    //방 설정 수정 요청

    //방 설정 수정

    //


}

package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.api.service.RoomService;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.dto.Room;
//import com.ssafy.backend.db.entity.Room;
import com.ssafy.backend.dto.SessionRoom;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.request.RoomSetReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import com.ssafy.backend.dto.response.RoomSearchRes;
import com.ssafy.backend.dto.response.RoomSetRes;
import io.openvidu.java.client.*;
import io.swagger.models.Response;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/sessions")
public class RoomController {

    @Autowired
    public RoomService roomService;

    @Autowired
    public JWTUtil jwtUtil;

    @Autowired
    public MemberService memberService;

    // 1. 전체 방 조회
    @GetMapping("/{page_info}")
    public ResponseEntity<List<RoomSearchRes>> getAllRoom(@PathVariable("page_info") int pageInfo){
        List<RoomSearchRes> list = roomService.getRooms(pageInfo);
        return ResponseEntity.ok(list);
    }

    // 2. 키워드로 방 검색
    @GetMapping("/{page_info}/{keyword}")
    public ResponseEntity<List<Room>> getRoomByKeyword(@PathVariable("page_info") int pageInfo, @PathVariable("keyword") String keyword) {
        List<Room> list = roomService.getRoomsWithKeyword(pageInfo, keyword);
        return ResponseEntity.ok(list);
    }

    // 3. 방 생성
    @PostMapping("")
    public ResponseEntity<RoomCreateRes> createRoom(RoomCreateReq roomCreateReq){
        RoomCreateRes roomCreateRes = roomService.createRoom(roomCreateReq);
        return ResponseEntity.ok(roomCreateRes);
    }

    // 4. 방 접속
    @GetMapping("/{openvidu_id}/connection")
    public ResponseEntity<RoomJoinRes> joinRoom(@PathVariable String openvidu_id, HttpServletRequest httpServletRequest){
        RoomJoinReq roomJoinReq = new RoomJoinReq(openvidu_id);
        RoomJoinRes roomJoinRes = roomService.joinRoom(httpServletRequest, roomJoinReq);
        return ResponseEntity.ok(roomJoinRes);
    }



    //5. 방 퇴장
    @GetMapping("/{openvidu_id}/disconnect")
    public ResponseEntity disconnect(HttpServletRequest httpServletRequest, @PathVariable String openvidu_id) throws Exception {
        //JWT 토큰에서 사용자 정보 받아오기
        String memberToken = httpServletRequest.getHeader("access-token");
        String loginId = jwtUtil.getInfo(memberToken).getLoginId();

        Member member = memberService.getInfoByLoginId(loginId);

        return new ResponseEntity(HttpStatus.OK);
    }


    //6. 진영, 순서 저장 및 토론규칙 반환
    @PostMapping("/settings")
    public ResponseEntity<RoomSetRes> setSideOrderRule(RoomSetReq roomSetReq){
        roomService.setSideOrder(roomSetReq);
        RoomSetRes roomSetRes = roomService.setDebateFormat(roomSetReq);
        return ResponseEntity.ok(roomSetRes);
    }


    //7. 비어있는 진영, 순서 배열 반환
    @GetMapping("/{openvidu_id}/empty")
    public ResponseEntity<String[][]> emptySideOrder(@PathVariable String openvidu_id){
        String[][] participants = roomService.validSideOrder(openvidu_id);
        return ResponseEntity.ok(participants);
    }

    //8. signal 전달
    @GetMapping("/{openvidu_id}/signal")
    public ResponseEntity sendSignal(@PathVariable String openvidu_id) {
        roomService.sendSignal(openvidu_id);
        return new ResponseEntity(HttpStatus.OK);
    }




}

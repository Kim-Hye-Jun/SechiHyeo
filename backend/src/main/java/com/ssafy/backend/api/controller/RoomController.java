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
import com.ssafy.backend.dto.request.RoomUpdateUserSideOrderReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import com.ssafy.backend.dto.response.RoomSearchRes;
import com.ssafy.backend.dto.response.RoomSetRes;
import io.openvidu.java.client.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/sessions")
@CrossOrigin("*")
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
    public ResponseEntity<RoomCreateRes> createRoom(@RequestBody RoomCreateReq roomCreateReq){
        RoomCreateRes roomCreateRes = roomService.createRoom(roomCreateReq);
        return ResponseEntity.ok(roomCreateRes);
    }

    // 4. 방 썸네일 파일 업로드
    @PostMapping("/{room_id}/thumbnail")
    public ResponseEntity createRoomThumbnail(@PathVariable String room_id, @RequestPart MultipartFile thumbnail) {
        roomService.uploadThumbnail(room_id, thumbnail);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 5. 자료 공유 관련 이미지 파일 주소 반환
    @PostMapping("/{room_id}/uploadProof")
    public ResponseEntity<String> uploadProof (@PathVariable String room_id, @RequestPart MultipartFile proof) {
        String proofUrl = roomService.uploadProof(room_id, proof);
        return ResponseEntity.ok(proofUrl);
    }


    // 6. 방 접속 - 랜덤
    @PostMapping("/connection_random")
    public ResponseEntity<RoomJoinRes> joinRoom_random (@RequestBody RoomJoinReq roomJoinReq, HttpServletRequest httpServletRequest){
//        RoomJoinReq roomJoinReq = new RoomJoinReq(room_id, side, order);
        RoomJoinRes roomJoinRes = roomService.joinRoom_random(httpServletRequest, roomJoinReq);
        return ResponseEntity.ok(roomJoinRes);
    }

    // 7. 방 접속 - 선택
    @PostMapping("/connection_select")
    public ResponseEntity<RoomJoinRes> joinRoom_select (@RequestBody RoomJoinReq roomJoinReq, HttpServletRequest httpServletRequest){
//        RoomJoinReq roomJoinReq = new RoomJoinReq(room_id, side, order);
        RoomJoinRes roomJoinRes = roomService.joinRoom_select(httpServletRequest, roomJoinReq);
        if(roomJoinRes.getUserSideOrder().equals("error"))
            return ResponseEntity.badRequest().body(roomJoinRes);
        else
            return ResponseEntity.ok(roomJoinRes);
    }



    // 8. 방 퇴장
    @GetMapping("/{room_id}/disconnect")
    public ResponseEntity disconnect(HttpServletRequest httpServletRequest, @PathVariable String room_id) throws Exception {
        //JWT 토큰에서 사용자 정보 받아오기
        String memberToken = httpServletRequest.getHeader("access-token");
        String loginId = jwtUtil.getInfo(memberToken).getLoginId();

//        Member member = memberService.getInfoByLoginId(loginId);

        roomService.disconnectParticipant(room_id, loginId);

        return new ResponseEntity(HttpStatus.OK);
    }


    // 9. 진영, 순서 저장 및 토론규칙 반환
    @PostMapping("/settings")
    public ResponseEntity<RoomSetRes> setSideOrderRule(RoomSetReq roomSetReq){
        roomService.setSideOrder(roomSetReq);
        RoomSetRes roomSetRes = roomService.setDebateFormat(roomSetReq);
        return ResponseEntity.ok(roomSetRes);
    }


    // 10. 비어있는 진영, 순서 배열 반환
    @GetMapping("/{room_id}/empty")
    public ResponseEntity<String[][]> emptySideOrder(@PathVariable String room_id){
        String[][] participants = roomService.validSideOrder(room_id);
        return ResponseEntity.ok(participants);
    }


    // 11. 진영, 순서 설정 변경
    @PutMapping("/sideOrder")
    public ResponseEntity updateUserSideOrder(@RequestBody RoomUpdateUserSideOrderReq roomUpdateUserSideOrderReq) {
        roomService.sendSignal(roomUpdateUserSideOrderReq);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 12. 토론 종료 후 사용자 정보 갱신
    @PutMapping("/{room_id}/end")
    public ResponseEntity updateDebateInfo(@PathVariable String room_id) {
        roomService.updateDebateInfo(room_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 13. 현재 방 개수 반환
    @GetMapping("/pageNum")
    public ResponseEntity<Integer> getPages() {
        return ResponseEntity.ok(roomService.getPageNumber());
    }

    // 14. 현재 방 전체 정보 반환
    @GetMapping("/roomInfo")
    public ResponseEntity<List<Room>> getAllRoomInfo() {
        return ResponseEntity.ok(roomService.getAllRoomInfo());
    }
}

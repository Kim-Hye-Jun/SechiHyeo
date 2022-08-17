package com.ssafy.backend.api.service;

//import com.ssafy.backend.db.entity.Room;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.dto.Room;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.request.RoomSetReq;
import com.ssafy.backend.dto.request.RoomUpdateUserSideOrderReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import com.ssafy.backend.dto.response.RoomSearchRes;
import com.ssafy.backend.dto.response.RoomSetRes;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoomService {
    //방 전체 리스트 , 검색어가 있을 경우 방 검색 결과 리스트
    List<RoomSearchRes> getRooms(int pageInfo);

    List<Room> getRoomsWithKeyword(int pageInfo, String keyword);

    //방 하나 조회
//    Room getRoom(String OpenviduId);

    //방 생성
    RoomCreateRes createRoom(RoomCreateReq roomCreateReq);

    //방 썸네일 업로드
    void uploadThumbnail(String roomId, MultipartFile thumbnail);

    //자료 파일 업로드
    String uploadProof(MultipartFile proof);

    //방 접속
    RoomJoinRes joinRoom_random(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq);

    RoomJoinRes joinRoom_select(HttpServletRequest httpServletRequest, RoomJoinReq roomJoinReq);

    //방 삭제
    void deleteRoom(String roomId);

//    //방장 권한 이동
//    void changeHost(Member prevMember, Member nextMember);

    // 접속자 방 퇴장
    void disconnectParticipant(String roomId, String loginId);

    //토론 진영 및 순서 설정
    void setSideOrder(RoomSetReq roomSetReq);

    //토론 규칙 저장 및 반환
    RoomSetRes setDebateFormat(RoomSetReq roomSetReq);

    String[][] validSideOrder(String roomId);

    void sendSignal(RoomUpdateUserSideOrderReq roomUpdateUserSideOrderReq);

    void updateDebateInfo(String roomId);

    int getPageNumber();
}

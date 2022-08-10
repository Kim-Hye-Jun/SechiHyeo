package com.ssafy.backend.api.service;

//import com.ssafy.backend.db.entity.Room;
import com.ssafy.backend.dto.Room;
import com.ssafy.backend.dto.request.RoomCreateReq;
import com.ssafy.backend.dto.request.RoomJoinReq;
import com.ssafy.backend.dto.response.RoomCreateRes;
import com.ssafy.backend.dto.response.RoomJoinRes;
import com.ssafy.backend.dto.response.RoomSearchRes;

import java.util.List;

public interface RoomService {
    //방 전체 리스트 , 검색어가 있을 경우 방 검색 결과 리스트
    List<RoomSearchRes> getRooms(int pageInfo);

    List<Room> getRoomsWithKeyword(int pageInfo, String keyword);

    //방 하나 조회
//    Room getRoom(String OpenviduId);

    //방 생성
    RoomCreateRes createRoom(RoomCreateReq roomCreateReq);

    //방 접속
    RoomJoinRes joinRoom(RoomJoinReq roomJoinReq);

    //방 삭제
    void deleteRoom(String OpenviduId);

}

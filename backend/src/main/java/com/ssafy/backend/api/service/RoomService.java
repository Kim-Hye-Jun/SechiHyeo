package com.ssafy.backend.api.service;

import com.ssafy.backend.db.entity.Room;

import java.util.List;

public interface RoomService {
    //방 전체 리스트 , 검색어가 있을 경우 방 검색 결과 리스트
    List<Room> getRooms(String keyword);

    //방 하나 조회
    Room getRoom(Long RoomNo);

    //
}

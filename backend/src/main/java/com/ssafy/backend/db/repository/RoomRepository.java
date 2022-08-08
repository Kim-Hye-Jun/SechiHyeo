package com.ssafy.backend.db.repository;

import com.ssafy.backend.db.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    //방 생성
    // Room 객체 생성 후 save

    //방 전체 조회
//    List<Room> findAll();

    //방 조회
//    Optional<Room> findById(Long RoomNo);

    //제목으로 방 검색
    List<Room> findByRoomTitle(String RoomTitle);

    //방 설정 수정
    //findById 하고 save

    //방 삭제
//    void delete(Room entity);
}

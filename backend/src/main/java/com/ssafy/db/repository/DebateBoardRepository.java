package com.ssafy.db.repository;

import com.ssafy.db.entity.DebateBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebateBoardRepository extends JpaRepository<DebateBoard,Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    //Optional<DebateBoard> findById(String board_no);

    //모집게시글 전체 조회
    //List<DebateBoard> findAll();

    //모집게시글 작성
    //이미 작성되있다 save()

    //모집게시글 수정
    //글을 부른후 다시 save()
    
    //모집게시글 삭제
    //delete()

    //모집게시글 조회
    Optional<DebateBoard> findById(Long board_no);
}
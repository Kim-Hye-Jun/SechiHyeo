package com.ssafy.db.repository;


import com.ssafy.db.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    Optional<Reply> findById(Long reply_no);

    //특정 게시글에 대한 댓글 조회
    List<Reply> findByBoardNo(Long boardNo);

    //특정 게시글에 대한 댓글 조회
    Reply findByReplyNo(Long replyNo);
    //댓글 등록
    //save()
    //댓글 수정
    //save()
    //댓글 삭제
    //delete
}

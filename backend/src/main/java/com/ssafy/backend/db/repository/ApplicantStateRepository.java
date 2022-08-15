package com.ssafy.backend.db.repository;

import com.ssafy.backend.db.entity.ApplicantState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantStateRepository extends JpaRepository<ApplicantState,Integer> {

    //내가 모집한 글, 따라서 debate_board의 member_no와 일치해야한다
    @Query(value = "SELECT DISTINCT a FROM ApplicantState a join a.debateBoard d where d.member.memberNo = :memberNo")
    List<ApplicantState> getCountOfRecruitingApplicant(int memberNo);

    //내가 신청한 글, 따라서 mememberNo가 일치하면 된다
    @Query(value = "SELECT DISTINCT a FROM ApplicantState a join a.member m where m.memberNo = :memberNo")
    List<ApplicantState> getCountOfApplyingApplicant(int memberNo);


    @Query(value = "SELECT DISTINCT COUNT(*) FROM ApplicantState a join a.debateBoard d where d.boardNo = :boardNo AND a.accept = 1")
    Long getCountOfCurrentApplicant(int boardNo);
}

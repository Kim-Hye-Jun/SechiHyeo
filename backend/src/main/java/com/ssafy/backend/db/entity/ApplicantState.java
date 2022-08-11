package com.ssafy.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "APPLICANT_STATE")
public class ApplicantState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicant_no")
    Long applicantNo = null;

    int side;
    int turn;
    // 0:거절 , 1:승인 , 2:대기
    int accept;

    //연관
    //게시판
    @ManyToOne
    @JoinColumn(name = "board_no")
    private DebateBoard debateBoard;
    
    //회원
    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
}

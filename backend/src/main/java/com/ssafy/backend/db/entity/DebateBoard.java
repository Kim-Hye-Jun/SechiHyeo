package com.ssafy.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "DEBATE_BOARD")
public class DebateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no", columnDefinition = "int unsigned")
    private int boardNo;

    @Column(name="board_title",nullable = false, columnDefinition = "varchar(100)")
    String boardTitle;

    @Column(name="debate_topic",nullable = false, columnDefinition = "varchar(100)")
    String debateTopic;

    @Column(name="board_content", columnDefinition = "varchar(300)")
    String boardContent;
    @Column(name="board_time")
    Date boardTime;

    @Column(name="debate_time")
    Date debateTime;
    @Column(name="max_applicant",nullable = false, columnDefinition = "int default 0")
    int maxApplicant;
    @Column(name="a_opinion",nullable = false, columnDefinition = "varchar(200)")
    String aOpinion;
    @Column(name="b_opinion",nullable = false, columnDefinition = "varchar(200)")
    String bOpinion;
    @Column(name="board_finished", nullable = false, columnDefinition = "boolean default false" )
    boolean boardFinished;

    //이 밑엔 연관관계 회원, 방 지정되어야한다
    //방은 db생성 안되서 없다

    //회원은 manytoone
    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
}

package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import ssafy.db.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class DebateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no")
    Long boardNo = null;

    @Column(name="board_title")
    String boardTitle;

    @Column(name="debate_topic")
    String debateTopic;

    @Column(name="board_content")
    String boardContent;
    @Column(name="board_time")
    Date boardTime;

    @Column(name="debate_time")
    Date debateTime;
    @Column(name="max_applicant")
    int maxApplicant;
    @Column(name="a_opinion")
    String aOpinion;
    @Column(name="b_opinion")
    String bOpinion;
    @Column(name="board_finished")
    boolean boardFinished;

    //이 밑엔 연관관계 회원, 방 지정되어야한다
    //방은 onetoone
    //회원은 manytoone
}

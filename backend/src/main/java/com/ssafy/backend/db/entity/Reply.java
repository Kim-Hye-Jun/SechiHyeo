package com.ssafy.backend.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Reply  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_no")
    Long replyNo = null;

    String context;
    Date date;
    int depth;
    boolean hidden;

    @Column(name="parent_no")
    int parentNo;
    
    //연관
    //게시판
    @ManyToOne
    @JoinColumn(name = "board_no")
    private DebateBoard debateBoard;
    
    //회원은 추후

}

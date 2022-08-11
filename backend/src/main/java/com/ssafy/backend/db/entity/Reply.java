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
@Table(name = "REPLY")
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
    long parentNo;
    
    //연관
    //게시판
    @ManyToOne
    @JoinColumn(name = "board_no")
    private DebateBoard debateBoard;
    
    //회원은 추후
    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
}

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
    @Column(name="reply_no", columnDefinition = "int unsigned")
    Long replyNo = null;

    @Column(name = "context",columnDefinition = "varchar(150)")
    String context;
    Date date;

    @Column(name = "depth", nullable = false, columnDefinition = "int unsigned default 0")
    int depth;

    @Column(name = "hidden", nullable = false, columnDefinition = "boolean default false")
    boolean hidden;

    @Column(name="parent_no",nullable = false, columnDefinition = "int unsigned default 0")
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

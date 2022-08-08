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
public class Room {
    //방 번호
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_no")
    private Long RoomNo;

    //오픈비두 방 세션 아이디
    @Column(name = "openvidu_id")
    private String OpenviduId;

    //방 제목
    @Column(name = "room_title")
    private String RoomTitle;

    //토론 주제
    @Column(name = "debate_topic")
    private String DebateTopic;

    //인원수(2~6명)
    @Column(name = "head_count")
    private int HeadCount;

    //방 잠금 여부 (private/public)
    @Column(name = "room_type")
    private String RoomType;

    //토론 종류(자유/아카데미)
    @Column(name = "debate_type")
    private String DebateType;

}

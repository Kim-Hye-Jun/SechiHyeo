package com.ssafy.backend.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class room {
    @Id
    @Column(name = "room_no")
    private int roomNo;

    @Column(name = "openvidu_id")
    private String openviduId;

    @Column(name = "room_title")
    private String roomTitle;

    @Column(name = "debate_topic")
    private String debateTopic;

    @Column(name = "head_count")
    private int headCount;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "debate_type")
    private String debateType;


}

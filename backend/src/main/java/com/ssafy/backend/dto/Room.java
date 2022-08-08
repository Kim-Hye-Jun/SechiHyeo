package com.ssafy.backend.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
//    private long roomNo;
    private String openviduId;
    private String roomTitle;
    private String debateTopic;
    private long headCount;
    private String roomType;
    private String debateType;
    private String thumbnail;

    //private일 경우 비밀번호
    private int password;

    private String sideA;
    private String sideB;
}

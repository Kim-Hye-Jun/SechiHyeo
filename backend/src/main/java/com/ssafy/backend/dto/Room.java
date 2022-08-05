package com.ssafy.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
//    private long roomNo;
    private String openviduId;
    private String roomTitle;
//    private String debateTopic;
    private long headCount;
    private String roomType;
//    private String debateType;
    private String thumbnail;
}

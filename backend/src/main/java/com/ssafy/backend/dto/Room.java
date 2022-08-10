package com.ssafy.backend.dto;

import com.ssafy.backend.dto.response.RoomSearchRes;
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
    private String password;

    private String sideA;
    private String sideB;

    public RoomSearchRes convertToRoomSearchRes(Room room) {
        return RoomSearchRes.builder()
                .roomTitle(room.getRoomTitle())
                .roomType(room.getRoomType())
                .openviduId(room.getOpenviduId())
                .headCount(room.getHeadCount())
                .thumbnail(room.getThumbnail())
                .build();
    }
}

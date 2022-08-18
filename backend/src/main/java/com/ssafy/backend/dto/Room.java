package com.ssafy.backend.dto;

import com.ssafy.backend.dto.response.RoomSearchRes;
import lombok.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    //    private long roomNo;
    private String roomId;
    private String roomTitle;
    private String debateTopic;
    private int curNumOfPeople;
    private int maxNumOfPeople;
    private String roomType;
    private String debateType;
    private String thumbnail;
    //썸네일 파일 이름
    private String thumbnailName;

    private ArrayList<String> proofName;

    //private일 경우 비밀번호
    private String password;

    //진영
    private String sideA;
    private String sideB;

    //진영에 속한 사람들?
    //발언 순서

    //규칙
    String debateFormat;


    public RoomSearchRes convertToRoomSearchRes(Room room) {
        return RoomSearchRes.builder()
                .roomTitle(room.getRoomTitle())
                .roomType(room.getRoomType())
                .roomId(room.getRoomId())
                .curNumOfPeople(room.getCurNumOfPeople())
                .maxNumOfPeople(room.getMaxNumOfPeople())
                .thumbnail(room.getThumbnail())
//                .sideOrderList(new ArrayList<SideOrderInfo>())
                .sideA(room.getSideA())
                .sideB(room.getSideB())
                .debateTopic(room.getDebateTopic())
                .build();
    }
}

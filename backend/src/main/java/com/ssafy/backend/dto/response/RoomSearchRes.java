package com.ssafy.backend.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomSearchRes {
    //세션 아이디, 방 제목, 방 인원수, 잠금여부, 방 썸네일
    private String roomId;
    private String roomTitle;
    private int curNumOfPeople;
    private int maxNumOfPeople;
    private String roomType;
    private String thumbnail;
}

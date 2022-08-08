package com.ssafy.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSearchRes {
    //세션 아이디, 방 제목, 방 인원수, 잠금여부, 방 썸네일
    private String openviduId;
    private String roomTitle;
    private long headCount;
    private String roomType;
    private String thumbnail;
}

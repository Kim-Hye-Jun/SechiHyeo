package com.ssafy.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSetReq {
    //오픈비두 세션 아이디, 토론 방식(링컨-더글라스 1:1, 칼포퍼 2:2, 세다 3:3), A진영 접속자 목록, B진영 접속자 목록
    String OpenviduId;
    String debateFormat;
    String[] AParticipants;
    String[] BParticipants;
}

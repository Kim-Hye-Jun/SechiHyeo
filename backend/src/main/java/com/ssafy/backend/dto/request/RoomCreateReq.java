package com.ssafy.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateReq {
    private String RoomTitle;
    private String DebateTopic;
    private int HeadCount;
    private String RoomType;
    private String DebateType;

    //private일 경우 비밀번호
    private int password;

    //진영
    private String sideA;
    private String sideB;

    //방장
    private String host;
}

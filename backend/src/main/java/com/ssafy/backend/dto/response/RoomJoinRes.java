package com.ssafy.backend.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomJoinRes {
    //세션아이디, 토큰, 닉네임(사용자정보), 방장 권한, 진영순서
    private String roomId;
    private String token;
    private String debateTopic;
    private String sideA;
    private String sideB;
    //    private String nickname;
    private int maxNumOfPeople;
    private boolean isHost;
    private String userSideOrder;
}

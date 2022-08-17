package com.ssafy.backend.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomJoinRes {
    //세션아이디, 토큰, 닉네임(사용자정보), 방장 권한, 진영순서
    private String roomId;
    private String tokenCamera;
//    private String tokenScreen;
//    private String debateTopic;
    private String sideA;
    private String sideB;
    private String nickname;
    private int currentNumOfPeople;
    private int maxNumOfPeople;
    private boolean isRoomHost;
    private String userSideOrder;
    private List<String> emptySideOrderList;
}

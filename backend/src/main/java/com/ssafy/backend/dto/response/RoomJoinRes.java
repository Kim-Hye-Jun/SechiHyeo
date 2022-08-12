package com.ssafy.backend.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomJoinRes {
    //세션아이디, 토큰, 닉네임(사용자정보), 방장 권한, 진영순서
    private String OpenviduId;
    private String token;
    private String nickname;
    private boolean isHost;
    private String sideOrder;
}

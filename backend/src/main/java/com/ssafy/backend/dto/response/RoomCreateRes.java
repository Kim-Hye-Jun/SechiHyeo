package com.ssafy.backend.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomCreateRes {
    //세션아이디, 토큰, 닉네임(사용자정보)
    private String roomId;
//    private String token;
//    private String nickname;
}

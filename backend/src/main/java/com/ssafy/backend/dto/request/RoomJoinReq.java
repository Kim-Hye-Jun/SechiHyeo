package com.ssafy.backend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomJoinReq {
    //세션아이디, 사용자정보(닉네임, 아이디->이것도 JWT로?), 진영정보, 순서
    private String roomId;
//    private String nickname;
    private String side;
    private int order;
//    private String profileName;
//    private String profileUrl;
}

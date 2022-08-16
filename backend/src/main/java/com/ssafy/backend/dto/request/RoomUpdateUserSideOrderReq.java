package com.ssafy.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateUserSideOrderReq {
    private String roomId;
//    private String loginId;
    private String preSideOrder;
    private String newSideOrder;
}

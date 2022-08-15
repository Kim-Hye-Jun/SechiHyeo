package com.ssafy.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSetRes {
    //오픈비두 세션 아이디, 토론 규칙
    String OpenviduId;
    JSONObject debateRule;
}

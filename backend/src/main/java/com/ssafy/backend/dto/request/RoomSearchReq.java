package com.ssafy.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSearchReq {
    //페이지 정보, 검색 키워드
    private int pageInfo;
    private String keyword;
}

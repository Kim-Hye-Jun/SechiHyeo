package com.ssafy.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateReq {
    private String roomTitle;
    private String debateTopic;
    private int headCount;
    private String roomType;
    private String debateType;

    //private일 경우 비밀번호
    private String password;

    //진영
    private String sideA;
    private String sideB;

    //방장
//    private String host;
}

package com.ssafy.backend.api.request.applicantState;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantStateRegiPostReq {
    int side;
    int order;
    long board_no;
}

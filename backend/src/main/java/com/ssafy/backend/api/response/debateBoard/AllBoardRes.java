package com.ssafy.backend.api.response.debateBoard;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("AllboardResponse")
public class AllBoardRes {
    long board_no;
    String board_title;
    int max_applicant;
    boolean board_finished;
}

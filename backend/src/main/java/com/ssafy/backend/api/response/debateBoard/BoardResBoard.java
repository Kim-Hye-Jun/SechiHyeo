package com.ssafy.backend.api.response.debateBoard;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@ApiModel("board in boardResponse")
public class BoardResBoard {
    String board_title;
    String debate_topic;
    String board_content;
    Date board_time;
    Date debate_time;
    int max_applicant;
    String a_opinion;
    String b_opinion ;
    boolean board_finished;
    String login_id;
    String nickname;

}

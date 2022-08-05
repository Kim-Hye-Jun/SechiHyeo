package com.ssafy.backend.api.request.debateBoard;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DebateBoardRegiPostReq {
    String board_title;
    String debate_topic;
    String board_content;
    Date debate_time;
    int max_applicant;
    String a_opinion;
    String b_opinion;
}

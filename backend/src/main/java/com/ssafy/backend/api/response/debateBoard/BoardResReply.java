package com.ssafy.backend.api.response.debateBoard;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@ApiModel("reply in Response")
public class BoardResReply {
    long reply_no;
    String context;
    Date date;
    int depth;
    boolean hidden;
    long board_no;
    long parent_no;
    String login_id;
    String nickname;
}

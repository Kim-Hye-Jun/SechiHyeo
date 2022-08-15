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
    int reply_no;
    String context;
    Date date;
    int depth;
    boolean hidden;
    int board_no;
    int parent_no;
    String login_id;
    String nickname;
}

package com.ssafy.backend.api.request.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyChangeReq {
    int reply_no;
    String context;
}

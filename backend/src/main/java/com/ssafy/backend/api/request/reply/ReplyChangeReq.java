package com.ssafy.backend.api.request.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyChangeReq {
    long reply_no;
    String context;
}

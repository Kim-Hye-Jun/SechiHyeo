package com.ssafy.api.request.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyRegiPostReq {
    int parent_no;
    String context;
    int depth;
}

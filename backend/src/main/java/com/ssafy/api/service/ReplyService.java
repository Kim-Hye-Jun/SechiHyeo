package com.ssafy.api.service;

import com.ssafy.api.request.reply.ReplyChangeReq;
import com.ssafy.api.request.reply.ReplyRegiPostReq;
import com.ssafy.db.entity.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> GetReplies(long boardNo);

    boolean regiReply(long board_no, ReplyRegiPostReq regiReq);

    boolean chageReply(ReplyChangeReq changeReq);

    boolean deleteReply(long reply_no);
}

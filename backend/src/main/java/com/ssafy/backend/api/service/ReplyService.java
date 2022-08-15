package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.reply.ReplyChangeReq;
import com.ssafy.backend.api.request.reply.ReplyRegiPostReq;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.entity.Reply;

import java.util.ArrayList;
import java.util.List;

public interface ReplyService {
    ArrayList<Reply> GetReplies(int boardNo);

    boolean regiReply(int board_no, ReplyRegiPostReq regiReq, Member member, DebateBoard debateBoard);

    boolean chageReply(ReplyChangeReq changeReq);

    boolean deleteReply(int reply_no);
}

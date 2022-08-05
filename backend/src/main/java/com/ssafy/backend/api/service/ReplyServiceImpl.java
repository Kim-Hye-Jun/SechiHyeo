package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.reply.ReplyChangeReq;
import com.ssafy.backend.api.request.reply.ReplyRegiPostReq;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Reply;
import com.ssafy.backend.db.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements  ReplyService{
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    DebateBoardService debateBoardService;

    @Override
    public List<Reply> GetReplies(long boardNo) {
        return replyRepository.findByBoardNo(boardNo);
    }

    @Override
    public boolean regiReply(long board_no, ReplyRegiPostReq regiReq) {
        DebateBoard debateBoard=debateBoardService.getBoard(board_no);
        if(debateBoard==null) return false;

        Reply reply=new Reply();
        reply.setContext(regiReq.getContext());
        reply.setDate(new Date());
        reply.setDepth(regiReq.getDepth());
        reply.setHidden(false);
        reply.setParentNo(regiReq.getParent_no());
        reply.setDebateBoard(debateBoard);
        
        //회원 처리

        if(replyRepository.save(reply)==null)return false;
        return true;
    }

    @Override
    public boolean chageReply(ReplyChangeReq changeReq) {
        Reply reply=replyRepository.findByReplyNo(changeReq.getReply_no());

        if(reply==null)return false;

        reply.setContext(changeReq.getContext());
        if(replyRepository.save(reply)==null)return false;
        else  return true;
    }

    @Override
    public boolean deleteReply(long reply_no) {
        Reply reply= replyRepository.findByReplyNo(reply_no);
        if(reply==null) return false;

        replyRepository.delete(reply);
        return true;
    }
}

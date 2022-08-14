package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.reply.ReplyChangeReq;
import com.ssafy.backend.api.request.reply.ReplyRegiPostReq;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.entity.Reply;
import com.ssafy.backend.db.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements  ReplyService{
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    DebateBoardService debateBoardService;

    @Override
    public ArrayList<Reply> GetReplies(int boardNo) {
        return replyRepository.findByDebateBoardBoardNo(boardNo);
    }

    @Transactional
    @Override
    public boolean regiReply(int board_no, ReplyRegiPostReq regiReq, Member member,DebateBoard debateBoard) {
        Reply reply=new Reply();
        reply.setContext(regiReq.getContext());
        reply.setDate(new Date());
        reply.setDepth(regiReq.getDepth());
        reply.setHidden(false);
        reply.setParentNo(regiReq.getParent_no());
        reply.setDebateBoard(debateBoard);

        //회원 처리
        reply.setMember(member);

        if(replyRepository.save(reply)==null)return false;
        return true;
    }

    @Transactional
    @Override
    public boolean chageReply(ReplyChangeReq changeReq) {
        Reply reply=replyRepository.findByReplyNo(changeReq.getReply_no());

        if(reply==null)return false;

        reply.setContext(changeReq.getContext());
        if(replyRepository.save(reply)==null)return false;
        else  return true;
    }

    @Transactional
    @Override
    public boolean deleteReply(int reply_no) {
        Reply reply= replyRepository.findByReplyNo(reply_no);
        if(reply==null) return false;

        replyRepository.delete(reply);
        return true;
    }
}

package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.debateBoard.DebateBoardRegiPostReq;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.repository.DebateBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("debateBoardService")
public class DebateBoardServiceImpl implements DebateBoardService{
    @Autowired
    DebateBoardRepository debateBoardRepository;

    @Override
    public List<DebateBoard> getBoards() {
        return (List<DebateBoard>) debateBoardRepository.findAll();
    }

    @Override
    public DebateBoard getBoard(long board_no) {

        return debateBoardRepository.findByBoardNo(board_no);
    }

    @Transactional
    @Override
    public boolean regiBoard(DebateBoardRegiPostReq regiReq,Member member) {
        DebateBoard debateBoard=new DebateBoard();
        debateBoard.setDebateTopic(regiReq.getDebate_topic());
        debateBoard.setBoardContent(regiReq.getBoard_content());
        debateBoard.setBoardTime(new Date());
        debateBoard.setDebateTime(regiReq.getDebate_time());
        debateBoard.setMaxApplicant(regiReq.getMax_applicant());
        debateBoard.setAOpinion(regiReq.getA_opinion());
        debateBoard.setBOpinion(regiReq.getB_opinion());
        debateBoard.setBoardFinished(false);

        //회원번호
        debateBoard.setMember(member);
        //방번호

        if(debateBoardRepository.save(debateBoard)==null)return false;
        else return true;
    }

    @Transactional
    @Override
    public boolean deleteBoard(long board_no) {
        DebateBoard debateBoard=getBoard(board_no);

        if(debateBoard==null) return false;

        debateBoardRepository.delete(debateBoard);
        return true;
    }

    @Transactional
    @Override
    public boolean changeBoard(long board_no, DebateBoardRegiPostReq regiReq) {
        DebateBoard debateBoard=getBoard(board_no);
        debateBoard.setDebateTopic(regiReq.getDebate_topic());
        debateBoard.setBoardContent(regiReq.getBoard_content());
        debateBoard.setBoardTime(new Date());
        debateBoard.setDebateTime(regiReq.getDebate_time());
        debateBoard.setMaxApplicant(regiReq.getMax_applicant());
        debateBoard.setAOpinion(regiReq.getA_opinion());
        debateBoard.setBOpinion(regiReq.getB_opinion());
        debateBoard.setBoardFinished(false);

        //회원번호

        //방번호

        if(debateBoardRepository.save(debateBoard)==null) return false;
        return true;
    }
}

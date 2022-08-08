package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.applicantState.ApplicantStateRegiPostReq;
import com.ssafy.backend.db.entity.ApplicantState;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.repository.ApplicantStateRepository;
import com.ssafy.backend.db.repository.DebateBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicantServiceImpl implements  ApplicantService{

    @Autowired
    ApplicantStateRepository applicantStateRepository;

    @Autowired
    DebateBoardRepository debateBoardRepository;
    @Override
    public boolean regiApplicant(ApplicantStateRegiPostReq regiReq, Member member) {
        ApplicantState as=new ApplicantState();

        as.setMember(member);
        as.setSide(regiReq.getSide());
        as.setTurn(regiReq.getOrder());
        as.setAccept(2);

        DebateBoard debateBoard=debateBoardRepository.findByBoardNo(regiReq.getBoard_no());
        if(debateBoard==null) return false;

        as.setDebateBoard(debateBoard);

        if(applicantStateRepository.save(as)==null)return false;
        else return true;
    }

    @Override
    public boolean changeApplicant(ApplicantStateRegiPostReq regiReq, Member member, long apply_no) {
        ApplicantState as=applicantStateRepository.getReferenceById(apply_no);

        if(as==null)return false;

        as.setMember(member);
        as.setSide(regiReq.getSide());
        as.setTurn(regiReq.getOrder());
        as.setAccept(2);

        DebateBoard debateBoard=debateBoardRepository.findByBoardNo(regiReq.getBoard_no());
        if(debateBoard==null) return false;

        as.setDebateBoard(debateBoard);

        if(applicantStateRepository.save(as)==null)return false;
        else return true;
    }

    @Override
    public boolean deleteApplicant(long apply_no) {
        ApplicantState as=applicantStateRepository.getReferenceById(apply_no);

        if(as==null)return false;
        applicantStateRepository.delete(as);
        return true;
    }
}

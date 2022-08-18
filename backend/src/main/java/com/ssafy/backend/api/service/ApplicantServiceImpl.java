package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.applicantState.ApplicantStateRegiPostReq;
import com.ssafy.backend.db.entity.ApplicantState;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.repository.ApplicantStateRepository;
import com.ssafy.backend.db.repository.DebateBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("applicantService")
public class ApplicantServiceImpl implements  ApplicantService{

    @Autowired
    ApplicantStateRepository applicantStateRepository;

    @Autowired
    DebateBoardRepository debateBoardRepository;

    @Transactional
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

    @Transactional
    @Override
    public boolean changeApplicant(ApplicantStateRegiPostReq regiReq, Member member, int apply_no) {
        ApplicantState as=applicantStateRepository.getReferenceById(apply_no);

        if(as==null)return false;

        as.setMember(member);
        as.setSide(regiReq.getSide());
        as.setTurn(regiReq.getOrder());
        as.setAccept(regiReq.getAccept());

        DebateBoard debateBoard=debateBoardRepository.findByBoardNo(regiReq.getBoard_no());
        if(debateBoard==null) return false;

        as.setDebateBoard(debateBoard);

        if(applicantStateRepository.save(as)==null)return false;
        else return true;
    }

    @Transactional
    @Override
    public boolean deleteApplicant(int apply_no) {
        ApplicantState as=applicantStateRepository.getReferenceById(apply_no);

        if(as==null)return false;
        applicantStateRepository.delete(as);
        return true;
    }

    //내가 모집한 게시판의 applicant_state 정보
    @Override
    public List<ApplicantState> getRecruitingApplicant(int memberNo) {
        List<ApplicantState> list=applicantStateRepository.getCountOfRecruitingApplicant(memberNo);
        return list;
    }

    //내가 신청한 게시판의 applicant_state 정보
    @Override
    public List<ApplicantState> getApplyingApplicant(int memberNo) {
        List<ApplicantState> list = applicantStateRepository.getCountOfApplyingApplicant(memberNo);
        return list;
    }

    @Override
    public List<ApplicantState> getApplicantState(int boardNo) {
        List<ApplicantState> list = applicantStateRepository.findByDebateBoardBoardNo(boardNo);
        return list;
    }

    //boardNo로 찾은 보드에 현재 승인된 인원수 count
    @Override
    public long countCurrentApplicantByBoardNo(int boardNo) {
        long cnt=applicantStateRepository.getCountOfCurrentApplicant(boardNo);
        return cnt;
    }


}

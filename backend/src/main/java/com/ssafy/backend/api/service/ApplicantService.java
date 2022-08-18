package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.applicantState.ApplicantStateRegiPostReq;
import com.ssafy.backend.db.entity.ApplicantState;
import com.ssafy.backend.db.entity.Member;

import java.util.List;

public interface ApplicantService {
    boolean regiApplicant(ApplicantStateRegiPostReq regiReq, Member member);

    boolean changeApplicant(ApplicantStateRegiPostReq regiReq, Member member, int apply_no);

    boolean deleteApplicant(int apply_no);

    List<ApplicantState> getRecruitingApplicant(int memberNo);

    long countCurrentApplicantByBoardNo(int boardNo);

    List<ApplicantState> getApplyingApplicant(int memberNo);

    List<ApplicantState> getApplicantState(int boardNo);
}

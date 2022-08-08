package com.ssafy.backend.api.service;

import com.ssafy.backend.api.request.applicantState.ApplicantStateRegiPostReq;
import com.ssafy.backend.db.entity.Member;

public interface ApplicantService {
    boolean regiApplicant(ApplicantStateRegiPostReq regiReq, Member member);

    boolean changeApplicant(ApplicantStateRegiPostReq regiReq, Member member, long apply_no);

    boolean deleteApplicant(long apply_no);
}

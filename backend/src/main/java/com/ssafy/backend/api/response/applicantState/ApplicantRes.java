package com.ssafy.backend.api.response.applicantState;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantRes {
    String nickname;
    int applicant_no;
}

package com.ssafy.backend.api.response.applicantState;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantStateRes {
    String debate_topic;
    int max_applicant;
    int current_applicant;
    boolean board_finished;
    String nickname;
    int accept;
    int board_no;
}

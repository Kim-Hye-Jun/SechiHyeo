package com.ssafy.backend.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_no", columnDefinition = "int unsigned")
    private int memberNo;

    @Column(name = "login_id", nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String loginId;

    @Column(name = "login_password", nullable = false, columnDefinition = "varchar(200)")
    private String loginPassword;

    @Column(name = "phone_number", nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    private String nickname;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String email;

    @Column(columnDefinition = "varchar(100)")
    private String introduce;

    @Column(name = "profile_name", columnDefinition = "varchar(200)")
    private String profileName;

    @Column(name = "profile_url", columnDefinition = "varchar(200)")
    private String profileUrl;

    @Column(nullable = false, columnDefinition = "int unsigned default 0")
    private int exp;

    @Column(name = "debate_number", nullable = false, columnDefinition = "int unsigned default 0")
    private int debateNumber;

//    @Column(name = "record_win", nullable = false, columnDefinition = "int unsigned default 0")
//    private int recordWin;
//
//    @Column(name = "record_draw", nullable = false, columnDefinition = "int unsigned default 0")
//    private int recordDraw;
//
//    @Column(name = "record_lose", nullable = false, columnDefinition = "int unsigned default 0")
//    private int recordLose;
//
//    @Column(name = "debate_time", nullable = false, columnDefinition = "int unsigned default 0")
//    private int debateTime;

    @Builder
    public Member(int memberNo, String loginId, String loginPassword, String phoneNumber, String nickname, String email, String introduce, String profileName, String profileUrl, int exp, int debateNumber) {
        this.memberNo = memberNo;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.email = email;
        this.introduce = introduce;
        this.profileName = profileName;
        this.profileUrl = profileUrl;
        this.exp = exp;
        this.debateNumber = debateNumber;
    }
}

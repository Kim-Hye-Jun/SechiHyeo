package com.ssafy.sch.entity;

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
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String nickname;
    private String email;
    private String introduce;

    @Builder
    public Member(String loginId, String loginPassword, String phoneNumber, String nickname, String email, String introduce) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.email = email;
        this.introduce = introduce;
    }
}

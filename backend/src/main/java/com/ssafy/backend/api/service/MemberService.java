package com.ssafy.backend.api.service;

import com.ssafy.backend.db.entity.Member;

public interface MemberService {
	//아이디 중복 체크
	Long idDuplicateCheck(String loginId);
	//이메일 중복 체크
	Long emailDuplicateCheck(String email);
	//휴대폰번호 중복 체크
	Long PhoneDuplicateCheck(String phoneNumber);
	//회원가입
	void join(Member member) throws Exception;

	//로그인
	int login(String loginId, String loginPassword) throws Exception;
	//회원정보 수정
	void changeMemberInfo(Member newMember, String loginId);
	//아이디 찾기
	String findLoginId(String email) throws Exception;
	//비밀번호 재설정
	void changeLoginPassword(String loginId, String newLoginPassword) throws Exception;
	//회원 탈퇴
	int signOut(String userId) throws Exception;
	//아이디로 회원 불러오기
	Member getInfoByLoginId(String userId) throws Exception;
}

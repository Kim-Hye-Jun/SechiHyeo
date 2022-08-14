package com.ssafy.backend.api.service;

import com.ssafy.backend.db.entity.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
	//아이디 중복 체크
	Long idDuplicateCheck(String loginId);
	//이메일 중복 체크
	Long emailDuplicateCheck(String email);
	//휴대폰번호 중복 체크
	Long phoneDuplicateCheck(String phoneNumber);
	//휴대폰번호 중복 체크
	Long nicknameDuplicateCheck(String nickname);
	//회원가입
	void join(Member member) throws Exception;

	//로그인
	int login(String loginId, String loginPassword) throws Exception;
	//회원정보 수정
	void changeMemberInfo(Member editedMember, String loginId);
	//프로필 업로드
	void changeProfileImage(Member member, MultipartFile profileImage);
	//비밀번호 재설정
	void changeLoginPassword(String loginId, String newLoginPassword) throws Exception;
	//회원 탈퇴
	int signOut(String loginId) throws Exception;
	//아이디로 회원 불러오기
	Member getInfoByLoginId(String loginId) throws Exception;
}

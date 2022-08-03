package com.ssafy.sch.service;

import com.ssafy.sch.entity.Member;
import com.ssafy.sch.repository.MemberRepository;
import com.ssafy.sch.util.SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //아이디 중복 체크
    //1이면 중복으로 아이디 사용 불가, 아니라면 사용 가능
    @Override
    public int idDuplicateCheck(String loginId) {
        // 추후 구현
        return 0;
    }

    //이메일 중복 체크
    //1이면 중복으로 이메일 사용 불가, 아니라면 사용 가능
    @Override
    public int emailDuplicateCheck(String email) {
        // 추후 구현
        return 0;
    }

    //회원가입
    @Transactional
    @Override
    public void join(Member member) throws Exception {
        System.out.println(member.toString());
        //비밀번호 암호화해서 저장
        member.setLoginPassword( new SHA256().getHash(member.getLoginPassword()) );
        memberRepository.save(member);
    }

    //로그인
    @Transactional
    @Override
    public int login(String loginId, String loginPassword) throws Exception {
        loginPassword = new SHA256().getHash(loginPassword);
        //존재하지 않는 아이디로 로그인 하거나
        //아이디는 존재하지만 비밀번호가 틀릴 경우 로그인 실패
        if(memberRepository.findByLoginId(loginId) == null
                || !memberRepository.findByLoginId(loginId).getLoginPassword().equals(loginPassword))
            return 0;
        return 1;
    }

    //회원 정보 수정
    @Transactional
    @Override
    public void changeMemberInfo(Member newMember, String loginId) {
        //추후 구현
    }

    //아이디 찾기
    @Override
    public String findLoginId(String email) throws Exception {
        //추후 구현
        return null;
    }

    //비밀번호 재설정
    @Transactional
    @Override
    public void changeLoginPassword(String loginId, String newLoginPassword) throws Exception {
        //추후 구현
    }

    //회원탈퇴
    @Transactional
    @Override
    public int signOut(String userId) throws Exception {
        //추후 구현
        return 0;
    }

    //userid로 id찾기
    @Override
    public Member selectOneByLoginId(String userId) throws Exception {
        //추후 구현
        return null;
    }
}

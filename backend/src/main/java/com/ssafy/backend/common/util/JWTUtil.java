package com.ssafy.backend.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * 로그인을 위한 토큰을 설정하는 클래스 입니다
*/
@Component
public class JWTUtil {
	@Autowired
	private MemberRepository memberRepository;
	
	private static String SALT = "A508";
	
	//토큰 유효시간 1일
	private long tokenValidTime = 24 * 60 * 60 * 1000L;
	
	//JWT 토큰 생성(User 객체를 담아 전송)
	public String createToken(String claimId) throws Exception {
		Date now = new Date();
		Member member = memberRepository.findByLoginId(claimId);
		//멤버 번호와 비밀번호 제외
		member.setMemberNo(-1);
		member.setLoginPassword("");
		//토큰에 정보 싣기
		return Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT")
				.claim("member", member) //토큰에 담기는 데이터
				.setExpiration(new Date(now.getTime() + tokenValidTime)) //토큰 유효시간 설정
				.setIssuedAt(new Date(now.getTime())) // 발급 시간 기록
				.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
				.setSubject("memberToken") //토큰 제목
				.compact();
	}
	
	
	// 토큰에 담긴 정보를 가져오기
	public Member getInfo(String token) throws Exception {
		Jws<Claims> claims = null;
		Member member = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes())
					.parseClaimsJws(token); // SALT를 사용하여 복호화
			ObjectMapper obj = new ObjectMapper();
			member = obj.convertValue(claims.getBody().get("member"), Member.class);
			
		} catch(Exception e) {
			throw new Exception();
		}

		return member;
	}
	
	// Interceptor에서 토큰 유효성을 검증하기 위한 메서드
	public void checkValid(String token) {
		Jwts.parser().setSigningKey(SALT.getBytes()).parseClaimsJws(token);
	}

}

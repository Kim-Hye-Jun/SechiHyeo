package com.ssafy.sch.controller;

import com.ssafy.sch.entity.Member;
import com.ssafy.sch.service.MemberService;
import com.ssafy.sch.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MemberService memberService;

    //로그인과 합칠 방법 생각해보기!****
    @GetMapping("/getMember") // 토큰에 담겨있는 사용자 정보 리턴, 토큰이 필요한 경로에 사용
    public ResponseEntity<Object> getMember(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER_AUTH);
            Member member = jwtUtil.getInfo(token);

            return new ResponseEntity<Object>(member, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> join(Member member) throws Exception {
        System.out.println(member);
        memberService.join(member);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(Member member) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        System.out.println(memberService.login(member.getLoginId(), member.getLoginPassword()));
        try {
            //member 정보를 이용하여 데이터베이스 확인
            //존재하면 토큰을 생성해서 결과에 넣어 반환
            if (memberService.login(member.getLoginId(), member.getLoginPassword()) == 1) {
                result.put("access-token", jwtUtil.createToken(member.getLoginId()));
                result.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                result.put("message", FAIL);
                status = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(result, status);
    }

}

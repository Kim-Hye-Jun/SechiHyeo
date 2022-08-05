package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
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
public class RegisterController {
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

    //아이디 중복 체크
    @GetMapping("/user-id/{login_id}")
    public ResponseEntity<String> idDuplicateCheck(@PathVariable String login_id) throws Exception{
        //DB에서 아이디로 검색했을 때 값이 나오면 중복된 아이디
        if(memberService.idDuplicateCheck(login_id)==1) {
            //throw new DuplicateMemberException("중복된 아이디 입니다.");
            return new ResponseEntity<String>(FAIL, HttpStatus.CONFLICT);
        }
        //아무것도 찾을 수 없다면 중복 검사 통과
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
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

package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
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

    //로그인
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "입력된 아이디와 비밀번호를 확인하여 로그인을 진행합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "로그인 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, Object>> login(Member member) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        try {
            //member 정보를 이용하여 데이터베이스 확인
            //존재하면 토큰을 생성해서 결과에 넣어 반환
            if (memberService.login(member.getLoginId(), member.getLoginPassword()) == 1) {
                result.put("access-token", jwtUtil.createToken(member.getLoginId()));
                result.put("message", SUCCESS);
                status = HttpStatus.OK;
            } else {
                result.put("message", "아이디 또는 비밀번호가 맞지 않습니다.");
                status = HttpStatus.UNAUTHORIZED;
            }
        }catch (Exception e) {
            result.put("message", "서버가 응답하지 않습니다.");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    // 토큰에 담겨있는 사용자 정보 리턴, 토큰이 필요한 경로에 사용
    @GetMapping("/getMember")
    @ApiOperation(value = "멤버 정보 조회", notes = "헤더에 보내진 jwt 토큰을 복호화하여, 토큰에 담긴 멤버 정보를 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "권한이 없습니다.")
    })
    public ResponseEntity<Object> getMember(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER_AUTH);
            Member member = jwtUtil.getInfo(token);

            return new ResponseEntity<Object>(member, HttpStatus.OK);
        } catch(Exception e) {
            //토큰이 유효하지 않은 경우
            String message = "권한이 없습니다.";

            return new ResponseEntity<Object>(message, HttpStatus.UNAUTHORIZED);
        }
    }

    //회원탈퇴
    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "헤더에 보내진 jwt 토큰을 복호화하여, 멤버의 탈퇴 처리를 진행합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근")
    })
    public ResponseEntity<String> deleteMember(HttpServletRequest request) throws Exception{
        String token = request.getHeader(HEADER_AUTH);
        String loginId = jwtUtil.getInfo(token).getLoginId();
        if(memberService.signOut(loginId) == 1) {
            return new ResponseEntity<String>("탈퇴 처리 되었습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<String>("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
    }

}

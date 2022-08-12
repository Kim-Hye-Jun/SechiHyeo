package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.db.entity.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/register", produces="application/json;charset=UTF-8")
@AllArgsConstructor
@CrossOrigin("*")
public class RegisterController {
    private static final String REDUPLICATION = "reduplication";

    @Autowired
    private MemberService memberService;

    //아이디 중복 체크
    @GetMapping("/user-id/{login_id}")
    @ApiOperation(value = "아이디 중복 체크", notes = "현재 회원가입하려고 하는 id({login_id})를 다른 사용자가 이용중인지 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 아이디 입니다."),
            @ApiResponse(code = 409, message = "이미 사용되고 있는 아이디 입니다."),
    })
    public ResponseEntity<Map<String, Object>> idDuplicateCheck(@PathVariable String login_id) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        //DB에서 아이디로 검색했을 때 값이 나오면 중복된 아이디
        if(memberService.idDuplicateCheck(login_id)==1) {
            result.put(REDUPLICATION, true);
            result.put("message", "이미 사용되고 있는 아이디 입니다.");
            status = HttpStatus.CONFLICT;
        }
        //아무것도 찾을 수 없다면 중복 검사 통과
        else {
            result.put(REDUPLICATION, false);
            result.put("message", "사용 가능한 아이디입니다.");
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "이메일 중복 체크", notes = "현재 회원가입하려고 하는 email({email})을 다른 사용자가 이용중인지 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 이메일 입니다."),
            @ApiResponse(code = 409, message = "이미 사용되고 있는 이메일 입니다."),
    })
    public ResponseEntity<Map<String, Object>> emailDuplicateCheck(@PathVariable String email) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        //DB에서 이메일로 검색했을 때 값이 나오면 중복된 이메일
        if(memberService.emailDuplicateCheck(email)==1) {
            result.put(REDUPLICATION, true);
            result.put("message", "이미 사용되고 있는 이메일 입니다.");
            status = HttpStatus.CONFLICT;
        }
        //아무것도 찾을 수 없다면 중복 검사 통과
        else {
            result.put(REDUPLICATION, false);
            result.put("message", "사용 가능한 이메일 입니다.");
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    @GetMapping("/phone-number/{phone_number}")
    @ApiOperation(value = "휴대폰번호 중복 체크", notes = "현재 회원가입하려고 하는 번호({phone_number})를 다른 사용자가 이용중인지 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 휴대폰번호 입니다."),
            @ApiResponse(code = 409, message = "이미 사용되고 있는 휴대폰번호 입니다."),
    })
    public ResponseEntity<Map<String, Object>> phoneDuplicateCheck(@PathVariable String phone_number) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        //DB에서 휴대폰번호로 검색했을 때 값이 나오면 중복된 이메일
        if(memberService.phoneDuplicateCheck(phone_number)==1) {
            result.put(REDUPLICATION, true);
            result.put("message", "이미 사용되고 있는 휴대폰번호 입니다.");
            status = HttpStatus.CONFLICT;
        }
        //아무것도 찾을 수 없다면 중복 검사 통과
        else {
            result.put(REDUPLICATION, false);
            result.put("message", "사용 가능한 휴대폰번호 입니다.");
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    @GetMapping("/nickname/{nickname}")
    @ApiOperation(value = "닉네임 중복체크", notes = "현재 회원가입하려고 하는 닉네임({nickname})를 다른 사용자가 이용중인지 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 닉네임 입니다."),
            @ApiResponse(code = 409, message = "이미 사용되고 있는 닉네임 입니다."),
    })
    public ResponseEntity<Map<String, Object>> nicknameDuplicateCheck(@PathVariable String nickname) throws Exception{
        HttpStatus status = null;

        HashMap<String, Object> result = new HashMap<>();
        //DB에서 휴대폰번호로 검색했을 때 값이 나오면 중복된 이메일
        if(memberService.nicknameDuplicateCheck(nickname)==1) {
            result.put(REDUPLICATION, true);
            result.put("message", "이미 사용되고 있는 닉네임 입니다.");
            status = HttpStatus.CONFLICT;
        }
        //아무것도 찾을 수 없다면 중복 검사 통과
        else {
            result.put(REDUPLICATION, false);
            result.put("message", "사용 가능한 닉네임 입니다.");
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String,Object>>(result, status);
    }

    //회원가입
    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "회원가입을 통해 멤버 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "000님, 환영합니다.")
    })
    public ResponseEntity<String> join(Member member) throws Exception {
        System.out.println(member);
        memberService.join(member);
        return new ResponseEntity<String>(member.getNickname() + "님, 환영합니다!", HttpStatus.OK);
    }

}

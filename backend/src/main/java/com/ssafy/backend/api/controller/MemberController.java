package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.common.util.SHA256;
import com.ssafy.backend.db.entity.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/member")
@AllArgsConstructor
@CrossOrigin("*")
@Log4j2
public class MemberController {
    private static final String HEADER_AUTH = "access-token";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Value("${part4.upload.path}")
    private static String uploadPath;


    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ServletContext servletContext;

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
    @GetMapping("/profile")
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

    //회원 정보 수정 (닉네임, 이메일, 휴대폰번호, 한 줄 소개)
    @PutMapping("/introduce")
    @ApiOperation(value = "회원 정보 수정", notes = "헤더에 보내진 jwt 토큰과 수정정보를 이용해, 멤버의 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    public ResponseEntity<Map<String, Object>> updateMember(HttpServletRequest request, Member member) throws Exception{
        String token = request.getHeader(HEADER_AUTH);
        String loginId = jwtUtil.getInfo(token).getLoginId();

        memberService.changeMemberInfo(member, loginId);

        HttpStatus status = null;
        HashMap<String, Object> result = new HashMap<>();

        result.put("access-token", jwtUtil.createToken(member.getLoginId()));
        result.put("message", SUCCESS);
        status = HttpStatus.OK;

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    //프로필 이미지 업로드
    @PutMapping("/profile-image")
    @ApiOperation(value = "프로필 이미지 수정(업로드)", notes = "헤더에 보내진 jwt 토큰과 업로드된 파일 정보를 이용해, 프로필 이미지를 수정합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    public ResponseEntity<Map<String, Object>> uploadProfileImage(HttpServletRequest request, @RequestPart MultipartFile profileImage){
        HttpStatus status = null;
        HashMap<String, Object> result = new HashMap<>();
        if(profileImage.getSize() != 0) {
            try {
                //파일 업로드 경로 및 파일명
                String uploadPath = servletContext.getRealPath("/profile");
                String fileName = profileImage.getOriginalFilename();
                String saveName = UUID.randomUUID() + "_" + fileName; // UUID로 저장(파일명 중복 방지)

                //파일객체 생성 및 업로드
                File file = new File(uploadPath, saveName);
                if(!new File(uploadPath).exists())
                    new File(uploadPath).mkdirs();
                profileImage.transferTo(file);

                //기존 프로필 삭제 및 업로드 프로필 db 저장
                String token = request.getHeader(HEADER_AUTH);
                Member member = jwtUtil.getInfo(token);
                File deleteFile = new File(uploadPath, member.getProfileName());
                System.out.println(member.getProfileName());
                if(deleteFile.exists()) deleteFile.delete();

                //프로필 이미지 정보 db 저장
                String loginID = member.getLoginId();
                memberService.changeProfileImage(loginID, saveName, file.getCanonicalPath());

                //토큰 재발급
                result.put("access-token", jwtUtil.createToken(member.getLoginId()));
                result.put("message", SUCCESS);
                status = HttpStatus.OK;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
            return new ResponseEntity<Map<String, Object>>(result, status);
        }
        
        //업로드된 파일이 없는 경우
        result.put("message", FAIL);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    //비밀번호 재설정 자격 검증(기존 비밀번호 일치여부 확인)
    @PostMapping("/auth-pw")
    @ApiOperation(value = "비밀번호 변경 자격 확인", notes = "헤더에 보내진 jwt 토큰과 비밀번호로, 멤버 정보를 대조하여 본인여부를 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공('success')"),
            @ApiResponse(code = 401, message = "권한 없음('fail')"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    public ResponseEntity<String> updateAuth(HttpServletRequest request, String loginPassword) throws Exception {
        String token = request.getHeader(HEADER_AUTH);
        String loginId = jwtUtil.getInfo(token).getLoginId();
        Member member = memberService.getInfoByLoginId(loginId);

        if(!member.getLoginPassword().equals(new SHA256().getHash(loginPassword)))
            return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
        else
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    //비밀번호 재설정
    @PutMapping("/password")
    @ApiOperation(value = "비밀번호 변경",
            notes = "헤더에 보내진 jwt 토큰과 수정정보를 이용해, 멤버의 정보를 수정합니다. 수정이 완료되면 로그아웃 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
            @ApiResponse(code = 500, message = "서버에러")
    })
    public ResponseEntity<String> changePw(HttpServletRequest request, String loginPassword) throws Exception{
        String token = request.getHeader(HEADER_AUTH);
        String loginId = jwtUtil.getInfo(token).getLoginId();
        System.out.println(loginPassword);
        memberService.changeLoginPassword(loginId, loginPassword);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
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

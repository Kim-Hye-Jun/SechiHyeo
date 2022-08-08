package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.request.applicantState.ApplicantStateRegiPostReq;
import com.ssafy.backend.api.request.debateBoard.DebateBoardRegiPostReq;
import com.ssafy.backend.api.service.ApplicantService;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "토론 신청 API",tags = {"Applicant State"})
@RestController
@RequestMapping("/debate-apply")
@CrossOrigin("*")
public class ApplicantStateController {
    private static final String HEADER_AUTH = "access-token";

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    ApplicantService applicantService;


    @PostMapping()
    @ApiOperation(value = "토론 참여 신청", notes = "<strong>토론 참여</strong>를 신청한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> registerApplicantState(HttpServletRequest request, @RequestBody ApplicantStateRegiPostReq regiReq){
        try {
            String token = request.getHeader(HEADER_AUTH);
            Member member = jwtUtil.getInfo(token);
            if(applicantService.regiApplicant(regiReq,member)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
            else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
        } catch(Exception e) {
            //토큰이 유효하지 않은 경우
            String message = "권한이 없습니다.";

            return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));
        }
    }

    @PutMapping ("/{apply_no}")
    @ApiOperation(value = "토론 참여 수정", notes = "<strong>토론 참여</strong>를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> changeApplicantState(HttpServletRequest request, @RequestBody ApplicantStateRegiPostReq regiReq,@RequestParam long apply_no){
        try {
            String token = request.getHeader(HEADER_AUTH);
            Member member = jwtUtil.getInfo(token);
            if(applicantService.changeApplicant(regiReq,member,apply_no)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
            else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
        } catch(Exception e) {
            //토큰이 유효하지 않은 경우
            String message = "권한이 없습니다.";

            return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));
        }
    }

    @DeleteMapping("/{apply_no}")
    @ApiOperation(value = "토론 참여 삭제", notes = "<strong>토론 참여</strong>를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> deleteApplicantState(HttpServletRequest request,@RequestParam long apply_no){
        if(applicantService.deleteApplicant(apply_no)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
    }


}

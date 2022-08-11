package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.request.reply.ReplyChangeReq;
import com.ssafy.backend.api.request.reply.ReplyRegiPostReq;
import com.ssafy.backend.api.service.ReplyService;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "댓글 API",tags = {"Reply"})
@RestController
@RequestMapping("/debate-reply")
@CrossOrigin("*")
public class ReplyController {
    private static final String HEADER_AUTH = "access-token";
    //서비스
    @Autowired
    ReplyService replyService;

    @Autowired
    JWTUtil jwtUtil;
    //매핑
    
    //댓글 등록
    @PostMapping
    @ApiOperation(value = "댓글 등록", notes = "<strong>댓글</strong>을 parent_no,depth에 맞춰서 context를 등록합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> regiReply(HttpServletRequest request,@PathVariable long board_no, @RequestBody ReplyRegiPostReq regiReq){
        try {
            String token = request.getHeader(HEADER_AUTH);
            Member member = jwtUtil.getInfo(token);

            if(replyService.regiReply(board_no,regiReq,member)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
            else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
        } catch(Exception e) {
            //토큰이 유효하지 않은 경우
            String message = "권한이 없습니다.";

            return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));
        }


    }
    
    //댓글 수정
    @PutMapping
    @ApiOperation(value = "댓글 수정", notes = "<strong>댓글</strong>을 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> putReply(@RequestBody ReplyChangeReq changeReq) {

        if(replyService.chageReply(changeReq)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }
    //댓글 삭제
    @DeleteMapping()
    @ApiOperation(value = "댓글 삭제", notes = "<strong>댓글</strong>을 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> deleteReply(@PathVariable long reply_no){

        if(replyService.deleteReply(reply_no)){return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
    }
    
}

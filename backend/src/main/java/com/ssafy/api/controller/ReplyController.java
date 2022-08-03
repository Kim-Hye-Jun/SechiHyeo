package com.ssafy.api.controller;

import com.ssafy.api.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.db.entity.BaseEntity;

@Api(value = "댓글 API",tags = {"Reply"})
@RestController
@RequestMapping("/debate-reply")
public class ReplyController {
    
    //서비스
    @Autowired
    ReplyService replyService;
    
    //매핑
    
    //댓글 등록
    @PostMapping
    @ApiOperation(value = "댓글 등록", notes = "<strong>댓글</strong>을 parent_no,depth에 맞춰서 context를 등록합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseEntity> addReply(){

        return ResponseEntity.status(200).body(null);
    }
    
    //댓글 수정
    @PutMapping
    @ApiOperation(value = "댓글 수정", notes = "<strong>댓글</strong>을 수정합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseEntity> putReply() {

        return ResponseEntity.status(200).body(null);

    }
    //댓글 삭제
    @DeleteMapping()
    @ApiOperation(value = "댓글 삭제", notes = "<strong>댓글</strong>을 삭제합니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseEntity> deleteReply(){

        return ResponseEntity.status(200).body(null);
    }
    
}

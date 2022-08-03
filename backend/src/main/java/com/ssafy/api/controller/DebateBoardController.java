package com.ssafy.api.controller;

import com.ssafy.api.response.debateBoard.boardRes;
import com.ssafy.api.service.DebateBoardService;
import com.ssafy.db.entity.DebateBoard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.common.model.response.BaseResponseBody;

import java.util.List;

@Api(value = "모집게시판 API", tags={"DebateBoard"})
@RestController
@RequestMapping("/debate-board")
public class DebateBoardController {

    //서비스
    @Autowired
    DebateBoardService debateBoardService;

    //매핑

    @GetMapping()
    @ApiOperation(value = "모집 게시글 전체 조회", notes = "<strong>모집 게시글</strong>을 전체 조회해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<List<DebateBoard>> getBoards(){

        List<DebateBoard> debateBoards=debateBoardService.getBoards();

        //실패=리스트 비어있음
        if(debateBoards==null){
            return ResponseEntity.status(400).body(null);
        }else{
            return ResponseEntity.status(200).body(debateBoards);
        }
    }

    @GetMapping("/{board_no}")
    @ApiOperation(value = "모집 게시글 조회", notes = "<strong>모집 게시글</strong>을 board_no에 맞춰서 조회해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<boardRes> getBoard(@PathVariable int board_no){

        return ResponseEntity.status(200).body(null);
    }
    
    @PostMapping()
    @ApiOperation(value = "모집 게시글 작성", notes = "<strong>모집 게시글</strong>을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> registerBoard(){

        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{board_no}")
    @ApiOperation(value = "모집 게시글 전체 조회", notes = "<strong>모집 게시글</strong>을 전체 조회해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    ResponseEntity<? extends BaseResponseBody> deleteBoard(){

        return ResponseEntity.status(200).body(null);
    }
}

package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.request.debateBoard.DebateBoardRegiPostReq;
import com.ssafy.backend.api.response.debateBoard.BoardRes;
import com.ssafy.backend.api.service.DebateBoardService;
import com.ssafy.backend.api.service.ReplyService;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.db.entity.DebateBoard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "모집게시판 API", tags={"DebateBoard"})
@RestController
@RequestMapping("/debate-board")
public class DebateBoardController {

    //서비스
    @Autowired
    DebateBoardService debateBoardService;

    @Autowired
    ReplyService replyService;
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
    public ResponseEntity<BoardRes> getBoard(@PathVariable long board_no){
        BoardRes boardRes=new BoardRes();
        //boardservice에서 보드를 board_no에 맞춰서 가져온다
        //없다면 400리턴
        //있다면 댓글 목록 가져온후 리턴
        boardRes.setBoard(debateBoardService.getBoard(board_no));
        if(boardRes.getBoard()==null){
            return ResponseEntity.status(400).body(null);
        }

        //board_no에 따른 댓글 가져오기
        boardRes.setReplies(replyService.GetReplies(board_no));
        
        return ResponseEntity.status(200).body(null);
    }
    
    @PostMapping()
    @ApiOperation(value = "모집 게시글 작성", notes = "<strong>모집 게시글</strong>을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> registerBoard(@RequestBody DebateBoardRegiPostReq regiReq){
        DebateBoard debateBoard=new DebateBoard();
        debateBoard.setDebateTopic(regiReq.getDebate_topic());
        debateBoard.setBoardContent(regiReq.getBoard_content());
        debateBoard.setBoardTime(new Date());
        debateBoard.setDebateTime(regiReq.getDebate_time());
        debateBoard.setMaxApplicant(regiReq.getMax_applicant());
        debateBoard.setAOpinion(regiReq.getA_opinion());
        debateBoard.setBOpinion(regiReq.getB_opinion());
        debateBoard.setBoardFinished(false);
        
        //회원번호

        //방번호

        if(debateBoardService.regiBoard(debateBoard)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }

    @PutMapping ()
    @ApiOperation(value = "모집 게시글 수정", notes = "<strong>모집 게시글</strong>을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> changeBoard(@PathVariable long board_no,@RequestBody DebateBoardRegiPostReq regiReq){


        if(debateBoardService.changeBoard(board_no,regiReq)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }
    
    @DeleteMapping("/{board_no}")
    @ApiOperation(value = "모집 게시글 삭젠", notes = "<strong>모집 게시글</strong>을 삭제해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    ResponseEntity<? extends BaseResponseBody> deleteBoard(@PathVariable long board_no){

        if(debateBoardService.deleteBoard(board_no)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else { return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }
}

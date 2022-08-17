package com.ssafy.backend.api.controller;

import com.ssafy.backend.api.request.debateBoard.DebateBoardRegiPostReq;
import com.ssafy.backend.api.response.debateBoard.AllBoardRes;
import com.ssafy.backend.api.response.debateBoard.BoardRes;
import com.ssafy.backend.api.response.debateBoard.BoardResBoard;
import com.ssafy.backend.api.response.debateBoard.BoardResReply;
import com.ssafy.backend.api.service.DebateBoardService;
import com.ssafy.backend.api.service.MemberService;
import com.ssafy.backend.api.service.ReplyService;
import com.ssafy.backend.common.model.response.BaseResponseBody;
import com.ssafy.backend.common.util.JWTUtil;
import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Member;
import com.ssafy.backend.db.entity.Reply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "모집게시판 API", tags={"DebateBoard"})
@RestController
@RequestMapping("/debate-board")
@CrossOrigin("*")
public class DebateBoardController {
    private static final String HEADER_AUTH = "access-token";

    @Autowired
    private JWTUtil jwtUtil;
    //서비스
    @Autowired
    DebateBoardService debateBoardService;

    @Autowired
    MemberService memberService;
    @Autowired
    ReplyService replyService;
    //매핑

    @GetMapping()
    @ApiOperation(value = "모집 게시글 전체 조회", notes = "<strong>모집 게시글</strong>을 전체 조회해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<List<AllBoardRes>> getBoards(){

        List<DebateBoard> debateBoards=debateBoardService.getBoards();

        //실패=리스트 비어있음
        if(debateBoards==null){
            return ResponseEntity.status(400).body(null);
        }

        ArrayList<AllBoardRes> list=new ArrayList<>();

        for (DebateBoard db: debateBoards
             ) {
            list.add(AllBoardRes.builder().board_finished(db.isBoardFinished()).board_no(db.getBoardNo()).board_title(db.getBoardTitle()).max_applicant(db.getMaxApplicant()).build());
        }


        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/{board_no}")
    @ApiOperation(value = "모집 게시글 조회", notes = "<strong>모집 게시글</strong>을 board_no에 맞춰서 조회해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<BoardRes> getBoard(@PathVariable int board_no){
        BoardRes boardRes=new BoardRes();
        //boardservice에서 보드를 board_no에 맞춰서 가져온다
        //없다면 400리턴
        //있다면 댓글 목록 가져온후 리턴
        DebateBoard debateBoard=debateBoardService.getBoard(board_no);
        boardRes.setBoard(BoardResBoard.builder()
                .board_no(debateBoard.getBoardNo())
                .board_title(debateBoard.getBoardTitle())
                .debate_topic(debateBoard.getDebateTopic())
                        .board_content(debateBoard.getBoardContent())
                        .board_time(debateBoard.getBoardTime())
                        .debate_time(debateBoard.getDebateTime())
                        .max_applicant(debateBoard.getMaxApplicant())
                        .a_opinion(debateBoard.getAOpinion())
                        .b_opinion(debateBoard.getBOpinion())
                        .board_finished(debateBoard.isBoardFinished())
                        .login_id(debateBoard.getMember().getLoginId())
                        .nickname(debateBoard.getMember().getNickname())
                .build());

        if(boardRes.getBoard()==null){
            return ResponseEntity.status(400).body(null);
        }

        //board_no에 따른 댓글 가져오기
        ArrayList<Reply> replies=replyService.GetReplies(board_no);
        ArrayList<BoardResReply> list=new ArrayList<>();

        for (Reply reply:replies
             ) {
            list.add(BoardResReply.builder()
                            .reply_no(reply.getReplyNo())
                            .context(reply.getContext())
                            .date(reply.getDate())
                            .depth(reply.getDepth())
                            .hidden(reply.isHidden())
                            .board_no(reply.getDebateBoard().getBoardNo())
                            .parent_no(reply.getParentNo())
                            .login_id(reply.getMember().getLoginId())
                            .nickname(reply.getMember().getNickname())
                    .build());
        }

        boardRes.setReplies(list);
        
        return ResponseEntity.status(200).body(boardRes);
    }
    
    @PostMapping()
    @ApiOperation(value = "모집 게시글 작성", notes = "<strong>모집 게시글</strong>을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> registerBoard(HttpServletRequest request,@RequestBody DebateBoardRegiPostReq regiReq){
        try {
            String token = request.getHeader(HEADER_AUTH);
            String loginID=jwtUtil.getInfo(token).getLoginId();
            Member member = memberService.getInfoByLoginId(loginID);
            if(debateBoardService.regiBoard(regiReq,member)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
            else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}
        } catch(Exception e) {
            //토큰이 유효하지 않은 경우
            String message = "권한이 없습니다.";

            return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));
        }



    }

    @PutMapping ("/{board_no}")
    @ApiOperation(value = "모집 게시글 수정", notes = "<strong>모집 게시글</strong>을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    public ResponseEntity<? extends BaseResponseBody> changeBoard(@PathVariable int board_no,@RequestBody DebateBoardRegiPostReq regiReq){


        if(debateBoardService.changeBoard(board_no,regiReq)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else {return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }
    
    @DeleteMapping("/{board_no}")
    @ApiOperation(value = "모집 게시글 삭제", notes = "<strong>모집 게시글</strong>을 삭제해준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
    })
    ResponseEntity<? extends BaseResponseBody> deleteBoard(@PathVariable int board_no){

        if(debateBoardService.deleteBoard(board_no)) {return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));}
        else { return ResponseEntity.status(400).body(BaseResponseBody.of(400,"잘못된 접근입니다"));}

    }
}

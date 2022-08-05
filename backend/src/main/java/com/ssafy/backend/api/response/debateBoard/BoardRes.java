package com.ssafy.backend.api.response.debateBoard;

import com.ssafy.backend.db.entity.DebateBoard;
import com.ssafy.backend.db.entity.Reply;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("boardResponse")
public class BoardRes {
    DebateBoard board;
    List<Reply> replies;


}

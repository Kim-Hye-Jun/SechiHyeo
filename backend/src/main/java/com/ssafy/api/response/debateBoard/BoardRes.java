package com.ssafy.api.response.debateBoard;

import com.ssafy.db.entity.DebateBoard;
import com.ssafy.db.entity.Reply;
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

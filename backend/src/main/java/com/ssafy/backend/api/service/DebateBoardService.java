package com.ssafy.backend.api.service;


import com.ssafy.backend.api.request.debateBoard.DebateBoardRegiPostReq;
import com.ssafy.backend.db.entity.DebateBoard;

import java.util.List;

public interface DebateBoardService {
    List<DebateBoard> getBoards();
    DebateBoard getBoard(long board_no);

    boolean regiBoard(DebateBoard debateBoard);

    boolean deleteBoard(long board_no);

    boolean changeBoard(long board_no, DebateBoardRegiPostReq regiReq);
}

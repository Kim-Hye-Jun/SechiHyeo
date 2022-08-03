package com.ssafy.api.service;

import com.ssafy.db.entity.DebateBoard;
import com.ssafy.db.repository.DebateBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("debateBoardService")
public class DebateBoardServiceImpl implements DebateBoardService{
    @Autowired
    DebateBoardRepository debateBoardRepository;


    @Override
    public List<DebateBoard> getBoards() {
        return (List<DebateBoard>) debateBoardRepository.findAll();
    }
}

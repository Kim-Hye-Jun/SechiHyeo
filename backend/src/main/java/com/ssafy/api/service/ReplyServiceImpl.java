package com.ssafy.api.service;

import com.ssafy.db.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyServiceImpl implements  ReplyService{
    @Autowired
    ReplyRepository replyRepository;

}

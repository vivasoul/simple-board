package com.odth.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("replyService")
public class ReplyService {

    private final ReplyMapper replyMapper;

    public List<ReplyVO> getReply(int brdNo){

        return replyMapper.selectReply(brdNo);
    }

    public int insertReply(ReplyVO vo) {

        return replyMapper.insertReply(vo);
    }

    public int updateReply(ReplyVO vo) {

        return replyMapper.updateReply(vo);
    }

    public int deleteReply(ReplyVO vo) {

        return replyMapper.deleteReply(vo);
    }
}

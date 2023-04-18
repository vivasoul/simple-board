package com.odth.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service("replyService")
public class ReplyService {
    private final String DEFAULT_PASSWORD = "0000";

    private final ReplyMapper replyMapper;

    public List<ReplyVO> getReply(int brdNo){

        return replyMapper.selectReply(brdNo);
    }

    public int insertReply(ReplyVO vo) {
        if(StringUtils.isEmpty(vo.getPasswd())) {
             vo.setPasswd(DEFAULT_PASSWORD);
        }
        return replyMapper.insertReply(vo);
    }

    public int updateReply(ReplyVO vo) {

        return replyMapper.updateReply(vo);
    }

    public int deleteReply(ReplyVO vo) {

        return replyMapper.deleteReply(vo);
    }

    public boolean checkPassword(ReplyVO vo) {

        return replyMapper.checkPassword(vo);
    }
}

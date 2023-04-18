package com.odth.reply;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    List<ReplyVO> selectReply(int brdNo);

    int insertReply(ReplyVO vo);

    int updateReply(ReplyVO vo);

    int deleteReply(ReplyVO vo);

    boolean checkPassword(ReplyVO vo);
}

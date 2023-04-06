package com.odth.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selectBoard();

    BoardVO selectBoardDetail(int brdNo);

    int insertBoard(BoardVO vo);

    int updateBoard(BoardVO vo);

    int deleteBoard(int brdNo);
}

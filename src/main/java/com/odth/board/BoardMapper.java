package com.odth.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selectBoard();

    List<BoardVO> selectBoardByCategory(int catNo);

    BoardVO selectBoardDetail(int brdNo);

    int increaseView(int brdNo);

    List<Integer> selectBoardCategory(int brdNo);

    int insertBoard(BoardVO vo);

    int updateBoard(BoardVO vo);

    int deleteBoard(int brdNo);

    int getInsertedBrdNo();

    int mergeBoardCategory(BoardVO vo);

    int deleteUnusedCategory(BoardVO vo);

    int deleteBoardCategory(int brdNo);
}

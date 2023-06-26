package com.odth.board;

import com.odth.board.vo.BoardAttachVO;
import com.odth.board.vo.BoardSearchVO;
import com.odth.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> selectBoard(BoardSearchVO searchVO);

    int selectBoardMaxPage(BoardSearchVO searchVO);

    List<BoardVO> selectBestBoard(BoardSearchVO searchVO);
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

    List<BoardAttachVO> selectBoardImages(int brdNo);
    int mergeBoardImages(BoardVO vo);

    boolean checkPassword(BoardVO vo);
}

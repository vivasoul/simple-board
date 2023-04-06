package com.odth.board;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("boardService")
public class BoardService {

    private final BoardMapper boardMapper;

    public List<BoardVO> getBoard() {

        return boardMapper.selectBoard();
    }

    public BoardVO getBoardDetail(int brdNo) {
        return boardMapper.selectBoardDetail(brdNo);
    }

    public int insertBoard(BoardVO vo) {

        return boardMapper.insertBoard(vo);
    }

    public int updateBoard(BoardVO vo) {

        return boardMapper.updateBoard(vo);
    }

    public int deleteBoard(int brdNo) {

        return boardMapper.deleteBoard(brdNo);
    }
}

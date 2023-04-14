package com.odth.board;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("boardService")
public class BoardService {

    private final BoardMapper boardMapper;

    public List<BoardVO> getBoard(Integer catNo) {
        if(catNo != null) {
            return boardMapper.selectBoardByCategory(catNo);
        } else {
            return boardMapper.selectBoard();
        }
    }

    public BoardVO getBoardDetail(int brdNo) {
        BoardVO board = boardMapper.selectBoardDetail(brdNo);
        List<Integer> catNos = boardMapper.selectBoardCategory(brdNo);
        board.setCatNos(catNos);

        return board;
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

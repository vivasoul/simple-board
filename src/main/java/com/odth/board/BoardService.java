package com.odth.board;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
        boardMapper.increaseView(brdNo);

        return board;
    }

    @Transactional
    public int insertBoard(BoardVO vo) {

        int res = boardMapper.insertBoard(vo);
        int brdNo = boardMapper.getInsertedBrdNo();
        vo.setBrdNo(brdNo);

        if(!CollectionUtils.isEmpty(vo.getCatNos())) {
            res += boardMapper.mergeBoardCategory(vo);
        }
        if(!CollectionUtils.isEmpty(vo.getFiles())) {
            res += boardMapper.mergeBoardImages(vo);
        }

        return res;
    }

    @Transactional
    public int updateBoard(BoardVO vo) {
        int res = boardMapper.updateBoard(vo);

        if(!CollectionUtils.isEmpty(vo.getCatNos())) {
            res += boardMapper.mergeBoardCategory(vo);
            res += boardMapper.deleteUnusedCategory(vo);
        }

        return res;
    }

    @Transactional
    public int deleteBoard(int brdNo) {

        int res = boardMapper.deleteBoardCategory(brdNo);
        res = boardMapper.deleteBoard(brdNo);

        return res;
    }
}

package com.odth.board;

import com.odth.board.vo.BoardAttachVO;
import com.odth.board.vo.BoardListVO;
import com.odth.board.vo.BoardSearchVO;
import com.odth.board.vo.BoardVO;
import com.odth.file.FileService;
import com.odth.file.FileVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service("boardService")
public class BoardService {

    private final FileService fileService;

    private final BoardMapper boardMapper;

    @Value("${odth.board.default-pass}")
    private String DEFAULT_PASS;

    private int ROW_PER_PAGE = 15;

    @Transactional
    public BoardListVO getBoard(BoardSearchVO searchVO) {
        BoardListVO result = new BoardListVO();

        searchVO.setRowPerPage(ROW_PER_PAGE);
        searchVO.setOffset(getOffSet(searchVO));

        List<BoardVO> boards = boardMapper.selectBoard(searchVO);
        int maxPage = boardMapper.selectBoardMaxPage(searchVO);

        result.setBoards(boards);
        result.setPage(searchVO.getCurPage(), maxPage);

        return result;
    }

    public BoardVO getBoardDetail(int brdNo) {
        BoardVO board = boardMapper.selectBoardDetail(brdNo);
        List<Integer> catNos = boardMapper.selectBoardCategory(brdNo);
        board.setCatNos(catNos);
        List<BoardAttachVO> files = boardMapper.selectBoardImages(brdNo);
        board.setFiles(files);

        boardMapper.increaseView(brdNo);

        return board;
    }

    @Transactional
    public int insertBoard(BoardVO vo) {
        /* get thumbnail image-id before insert */
        int thumbId = getThumbId(vo.getFiles());
        vo.setThumbId(thumbId);

        if(StringUtils.isEmpty(vo.getPasswd())) {
            vo.setPasswd(DEFAULT_PASS);
        }

        int res = boardMapper.insertBoard(vo);
        int brdNo = boardMapper.getInsertedBrdNo();
        vo.setBrdNo(brdNo);

        res += insertRelationalParts(vo, true);

        return res;
    }

    @Transactional
    public int updateBoard(BoardVO vo) {
        /* get thumbnail image-id before update */
        int thumbId = getThumbId(vo.getFiles());
        vo.setThumbId(thumbId);

        int res = boardMapper.updateBoard(vo);

        res += insertRelationalParts(vo, false);

        return res;
    }

    @Transactional
    public int deleteBoard(int brdNo) {

        //int res = boardMapper.deleteBoardCategory(brdNo);
        int res = boardMapper.deleteBoard(brdNo);

        return res;
    }

    private int insertRelationalParts(BoardVO vo, boolean isNew) {
        int res = 0;
        if(!CollectionUtils.isEmpty(vo.getCatNos())) {
            res += boardMapper.mergeBoardCategory(vo);

            if(!isNew) res += boardMapper.deleteUnusedCategory(vo);
        }

        if(!CollectionUtils.isEmpty(vo.getFiles())) {
            res += boardMapper.mergeBoardImages(vo);
        }
        return res;
    }

    private int getThumbId(List<BoardAttachVO> files) {
        int thumbId = 0;
        try {
            if(files != null) {
                for (BoardAttachVO attachVO : files) {
                    boolean isThumbnail = "Y".equals(attachVO.getThumbYn());
                    if (isThumbnail) {
                        thumbId = attachVO.getThumbId();
                        if (thumbId < 1) {
                            FileVO thumbVO = fileService.getThumbnail(attachVO.getFileId());
                            thumbId = thumbVO.getFileId();
                        }
                        break;
                    }
                }
            }
        } catch(IOException ie) {
            ie.printStackTrace();
        }
        return thumbId;
    }

    public boolean checkPassword(BoardVO vo) {

        return boardMapper.checkPassword(vo);
    }

    private int getOffSet(BoardSearchVO searchVO) {
        int curPage = searchVO.getCurPage();
        int rowPerPage = searchVO.getRowPerPage();
        if(curPage > 0) {
            return (curPage - 1) * rowPerPage;
        } else {
            return 0;
        }
    }
}

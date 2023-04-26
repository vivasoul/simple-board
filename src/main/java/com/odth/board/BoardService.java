package com.odth.board;

import com.odth.file.FileVO;
import com.odth.file.UploadService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service("boardService")
public class BoardService {

    private final UploadService uploadService;

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

        int res = boardMapper.deleteBoardCategory(brdNo);
        res = boardMapper.deleteBoard(brdNo);

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
                            FileVO thumbVO = uploadService.getThumbnail(attachVO.getFileId());
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
}

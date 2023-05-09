package com.odth.board;

import com.odth.board.vo.BoardListVO;
import com.odth.board.vo.BoardSearchVO;
import com.odth.board.vo.BoardVO;
import com.odth.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public BoardListVO getBoard(@ModelAttribute BoardSearchVO searchVO) {

        return boardService.getBoard(searchVO);
    }

    @GetMapping("/{brdNo}")
    public BoardVO getBoardDetail(@PathVariable int brdNo) {

        return boardService.getBoardDetail(brdNo);
    }

    @PostMapping
    public int insertBoard(@RequestBody BoardVO vo, HttpServletRequest req) {

        String userIp = HttpUtils.getRequestIP(req);
        vo.setRegIp(userIp);

        return boardService.insertBoard(vo);
    }

    @PutMapping("/{brdNo}")
    public int updateBoard(@RequestBody BoardVO vo) {

        return boardService.updateBoard(vo);
    }

    @DeleteMapping("/{brdNo}")
    public int deleteBoard(@PathVariable int brdNo) {

        return boardService.deleteBoard(brdNo);
    }

    @PostMapping("/{brdNo}/check-pass")
    public boolean checkBoard(@PathVariable int brdNo, @RequestBody BoardVO vo) {

        return boardService.checkPassword(vo);
    }
}

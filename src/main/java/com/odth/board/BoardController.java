package com.odth.board;

import com.odth.board.vo.BoardListVO;
import com.odth.board.vo.BoardSearchVO;
import com.odth.board.vo.BoardVO;
import com.odth.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public BoardVO getBoardDetail(@PathVariable int brdNo, HttpSession session) {
        boolean alreadyRead = checkBoardsInSession(brdNo, session);

        return boardService.getBoardDetail(brdNo, alreadyRead);
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

    private boolean checkBoardsInSession(int brdNo, HttpSession session) {
        boolean alreayRead = false;
        Set<Integer> viewCahce = null;

        if(session.getAttribute("viewCache") == null) {
            viewCahce = new HashSet<Integer>();
            session.setAttribute("viewCache", viewCahce);
        } else {
            viewCahce = (Set<Integer>) session.getAttribute("viewCache");
        }

        if(viewCahce.contains(brdNo)) {
            alreayRead = true;
        } else {
            viewCahce.add(brdNo);
        }

        return alreayRead;
    }
}

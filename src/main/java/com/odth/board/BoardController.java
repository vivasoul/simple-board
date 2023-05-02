package com.odth.board;

import com.odth.reply.ReplyVO;
import com.odth.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardVO> getBoard(@RequestParam(value = "catNo",required = false) Integer catNo) {

        return boardService.getBoard(catNo);
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

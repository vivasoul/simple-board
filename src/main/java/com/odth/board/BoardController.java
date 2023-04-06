package com.odth.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardVO> getBoard() {

        return boardService.getBoard();
    }

    @GetMapping("/{brdNo}")
    public BoardVO getBoardDetail(@PathVariable int brdNo) {

        return boardService.getBoardDetail(brdNo);
    }

    @PostMapping
    public int insertBoard(@RequestBody BoardVO vo) {

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
}

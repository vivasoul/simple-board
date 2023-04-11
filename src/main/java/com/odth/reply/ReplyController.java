package com.odth.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board/{brdNo}/reply")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping
    public List<ReplyVO> getReply(@PathVariable int brdNo){

        return replyService.getReply(brdNo);
    }

    @PostMapping
    public int insertReply(@PathVariable int brdNo, @RequestBody ReplyVO vo) {

        return replyService.insertReply(vo);
    }

    @PutMapping("/{replNo}")
    public int updateReply(@PathVariable int brdNo, @RequestBody ReplyVO vo) {

        return replyService.updateReply(vo);
    }

    @DeleteMapping("/{replNo}")
    public int deleteReply(@PathVariable int brdNo, @PathVariable int replNo) {
        ReplyVO vo = new ReplyVO();
        vo.setBrdNo(brdNo);
        vo.setReplNo(replNo);
        return replyService.deleteReply(vo);
    }
}
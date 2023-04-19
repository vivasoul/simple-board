package com.odth.reply;

import com.odth.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public int insertReply(@PathVariable int brdNo, @RequestBody ReplyVO vo, HttpServletRequest req) {

        String userIp = HttpUtils.getRequestIP(req);
        vo.setRegIp(userIp);

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

    @PostMapping("/{replNo}/check-pass")
    public boolean checkReply(@PathVariable int brdNo, @RequestBody ReplyVO vo) {

        return replyService.checkPassword(vo);
    }
}

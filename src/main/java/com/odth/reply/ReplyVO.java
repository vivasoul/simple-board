package com.odth.reply;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
    private int brdNo;
    private int replNo;
    private String content;
    private String passwd;
    private String regIp;
    private String timeElapsed;
    private String regDt;
    private String updDt;
}

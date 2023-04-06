package com.odth.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
    private int brdNo;
    private String title;
    private String content;
    private String regDt;
    private String updDt;
    private int totCnt;
}

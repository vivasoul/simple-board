package com.odth.board;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardVO {
    private int brdNo;
    private String title;
    private String content;
    private List<Integer> catNos;
    private String catNostr;
    private String regIp;
    private String timeElapsed;
    private int viewCnt;
    private String regDt;
    private String updDt;
    private int totCnt;
    private int replyCnt;
}

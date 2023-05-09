package com.odth.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardAttachVO {
    private int fileId;
    private int thumbId;
    private String downPath;
    private String thumbYn;
}

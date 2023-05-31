package com.odth.board.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchVO extends BoardVO{
    private int catNo;
    private int curPage;
    private String mode;
    private String text;
    private int offset;
    private int rowPerPage;
}

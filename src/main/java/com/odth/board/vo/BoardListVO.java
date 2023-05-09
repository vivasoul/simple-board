package com.odth.board.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardListVO {
    private List<BoardVO> boards;
    private PageVO page;

    public void setPage(int curPage, int maxPage){
        page = new PageVO();
        page.setCurPage(curPage);
        page.setMaxPage(maxPage);
    }

    @Getter
    @Setter
    static class PageVO {
        private int curPage;
        private int maxPage;
    }
}



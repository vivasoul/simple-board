package com.odth.common.listener;

import com.odth.common.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
public class DailyVisitListener implements HttpSessionListener {

    private CommonMapper commonMapper;

    public DailyVisitListener(CommonMapper commonMapper) {
        super();
        this.commonMapper = commonMapper;
    }

    public void sessionCreated(HttpSessionEvent se){
        log.info("session created ☆ "+se.getSession().getId());
        commonMapper.updateDailyVisit();
    }
}

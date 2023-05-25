package com.odth.common.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    int updateDailyVisit();
}

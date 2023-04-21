package com.odth.file;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void insertFile(FileVO vo);
}

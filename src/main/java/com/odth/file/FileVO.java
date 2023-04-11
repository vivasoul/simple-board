package com.odth.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileVO {

    private String fileId;
    private String filePath;
    private String fileNm;
    private String downFilePath;
    private String downFileNm;
    private long fileSize;
    private String fileExt;
    private String updDt;
    private String sessionId;

}

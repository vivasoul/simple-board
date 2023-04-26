package com.odth.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileVO {
    private String fileId;
    private String filePath;
    private String downNm;
    private String downPath;
    private long fileSize;
    private String fileExt;
    private String updDt;
    private String sessionId;

}

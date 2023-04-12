package com.odth.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service("uploadService")
public class UploadService {

    @Value("${upload.temp.up-path}")
    private String UPLOAD_TEMP_ROOT;

    @Value("${upload.temp.down-path}")
    private String DOWNLOAD_TEMP_ROOT;

    @Value("${upload.ops.up-path}")
    private String UPLOAD_ROOT;

    @Value("${upload.ops.down-path}")
    private String DOWNLOAD_ROOT;

    public List<FileVO> uploadFiles(MultipartFile[] files) throws IllegalStateException, IOException{
        List<FileVO> fileList = new ArrayList<>();

        for(MultipartFile mf : files) {
           FileVO vo = uploadFile(mf);
           fileList.add(vo);
        }

        return fileList;
    }

    private FileVO uploadFile(MultipartFile mFile) throws IllegalStateException, IOException {
        return uploadFile(UPLOAD_ROOT, DOWNLOAD_ROOT, mFile);
    }

    private FileVO uploadTempFile(MultipartFile mFile) throws IllegalStateException, IOException {
        return uploadFile(UPLOAD_TEMP_ROOT, DOWNLOAD_TEMP_ROOT, mFile);
    }

    private FileVO uploadFile(String uploadRoot, String downloadRoot, MultipartFile mFile) throws IllegalStateException, IOException {
        String subPath = generateSubPath();
        String uploadPath = uploadRoot + File.separator + subPath;
        String downloadPath = downloadRoot + "/" + subPath.replace(File.separator, "/");

        String extension = "";
        String downloadNm = mFile.getOriginalFilename(); // file_name for download

        int i = downloadNm.lastIndexOf('.');
        if (i >= 0) {
            extension = downloadNm.substring(i+1);
        }
        String fileNm = UUID.randomUUID().toString()+"."+extension; // file_name for upload

        File updDir = new File(uploadPath);
        if(!updDir.exists()) updDir.mkdirs();
        System.out.println("##################"+uploadPath);

        File file = new File(uploadPath+File.separator+fileNm);
        mFile.transferTo(file); // 파일을 위에 지정 경로로 업로드

/*        BufferedImage bimg = ImageIO.read(file);
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();*/

        FileVO vo = new FileVO();
        vo.setFileNm(fileNm);
        vo.setFilePath(subPath);
        vo.setFileExt(extension);
        vo.setFileSize(file.length());
        vo.setDownFilePath(downloadPath+"/"+fileNm);
        vo.setDownFileNm(downloadNm);

        return vo;
    }

    private String generateSubPath() {
        Calendar c = Calendar.getInstance();
        int yyyy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH) + 1;

        return yyyy+File.separator+mm;
    }
}

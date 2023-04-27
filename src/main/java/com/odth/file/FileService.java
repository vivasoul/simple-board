package com.odth.file;

import com.odth.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service("fileService")
@RequiredArgsConstructor
public class FileService {

    @Value("${upload.temp.up-path}")
    private String UPLOAD_TEMP_ROOT;

    @Value("${upload.temp.down-path}")
    private String DOWNLOAD_TEMP_ROOT;

    @Value("${upload.ops.up-path}")
    private String UPLOAD_ROOT;

    @Value("${upload.ops.down-path}")
    private String DOWNLOAD_ROOT;

    private final FileMapper fileMapper;

    public List<FileVO> uploadFiles(MultipartFile[] files) throws IllegalStateException, IOException{
        List<FileVO> fileList = new ArrayList<>();

        for(MultipartFile mf : files) {
           FileVO vo = uploadFile(mf);
           fileList.add(vo);
        }

        return fileList;
    }

    @Transactional
    public void insertFileInfo(List<FileVO> list) {
        for(FileVO vo : list) {
            fileMapper.insertFile(vo);
        }
    }

    @Transactional
    public void deleteFile(FileVO vo) throws IOException {
        int fileId = vo.getFileId();
        String filePath = fileMapper.selectFilePath(fileId);
        deleteUploadedFile(filePath);
        fileMapper.deleteBoardAttach(fileId);
        fileMapper.deleteFile(fileId);

    }

    public FileVO getThumbnail(int srcFileId) throws IOException {
        String srcFilePath = fileMapper.selectFilePath(srcFileId);

        if(StringUtils.isEmpty(srcFilePath)) {
            throw new IOException("원본 파일이 존재하지 않습니다: "+srcFilePath);
        } else {
            return getThumbnail(srcFileId, srcFilePath);
        }
    }

    public FileVO getThumbnail(int srcFileId, String srcFilePath) throws IOException {
        File srcFile = new File(srcFilePath);
        FileVO thumbVO = null;
        if(srcFile.exists()) {
            thumbVO = generateThumbnail(srcFile);
            fileMapper.insertFile(thumbVO);
            if(srcFileId > 0) {
                FileVO oriVO = new FileVO();
                oriVO.setFileId(srcFileId);
                oriVO.setThumbId(thumbVO.getFileId());
                fileMapper.updateThumbId(oriVO);
            }
            return thumbVO;
        } else {
            return null;
        }
    }

    private FileVO uploadFile(MultipartFile mFile) throws IllegalStateException, IOException {
        return uploadFile(UPLOAD_ROOT, DOWNLOAD_ROOT, mFile);
    }

    private FileVO uploadTempFile(MultipartFile mFile) throws IllegalStateException, IOException {
        return uploadFile(UPLOAD_TEMP_ROOT, DOWNLOAD_TEMP_ROOT, mFile);
    }

    private FileVO uploadFile(String uploadRoot, String downloadRoot, MultipartFile mFile) throws IllegalStateException, IOException {
        String subPath = generateSubPath(null);
        String uploadPath = uploadRoot + File.separator + subPath;
        String downloadPath = downloadRoot + "/" + subPath.replace(File.separator, "/");

        String extension = "";
        String downloadNm = mFile.getOriginalFilename(); // file_name for download

        int i = downloadNm.lastIndexOf('.');
        if (i >= 0) {
            extension = downloadNm.substring(i+1);
        }
        String fileNm = genenrateFileName(extension); // file_name for upload

        File updDir = new File(uploadPath);
        if(!updDir.exists()) updDir.mkdirs();

        File file = new File(uploadPath+File.separator+fileNm);
        mFile.transferTo(file); // 파일을 위에 지정 경로로 업로드

/*        BufferedImage bimg = ImageIO.read(file);
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();*/

        FileVO vo = new FileVO();
        vo.setFilePath(file.getAbsolutePath());
        vo.setDownNm(downloadNm);
        vo.setDownPath(downloadPath+"/"+fileNm);
        vo.setFileExt(extension);
        vo.setFileSize(file.length());

        return vo;
    }

    private FileVO generateThumbnail(File originalFile) throws IOException {

        return generateThumbnail(UPLOAD_ROOT, DOWNLOAD_ROOT, originalFile);
    }

    private FileVO generateThumbnail(String uploadRoot, String downloadRoot, File originalFile) throws IOException {
        String subPath = generateSubPath("thumbs");
        String uploadPath = uploadRoot + File.separator + subPath;
        String downloadPath = downloadRoot + "/" + subPath.replace(File.separator, "/");

        String extension = "PNG";
        String fileNm = genenrateFileName(extension); // file_name for thumbnail

        File updDir = new File(uploadPath);
        if(!updDir.exists()) updDir.mkdirs();
        File file = new File(uploadPath+File.separator+fileNm);

        BufferedImage image = ImageUtils.resizeImage(originalFile);
        ImageIO.write(image, extension, file);

        FileVO vo = new FileVO();
        vo.setFilePath(file.getAbsolutePath());
        vo.setDownNm(fileNm);
        vo.setDownPath(downloadPath+"/"+fileNm);
        vo.setFileExt(extension);
        vo.setFileSize(file.length());

        return vo;
    }

    private String generateSubPath(String subfixPath) {
        Calendar c = Calendar.getInstance();
        int yyyy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH) + 1;
        StringBuilder subPath = new StringBuilder();

        subPath.append(yyyy)
               .append(File.separator)
               .append(mm);

        if(!StringUtils.isEmpty(subfixPath)) {
            subPath.append(File.separator)
                   .append(subfixPath);
        }

        return subPath.toString();
    }

    private void deleteUploadedFile(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        } else {
            throw new IOException("삭제할 파일이 존재하지 않습니다: "+filePath);
        }
    }

    private String genenrateFileName(String extension) {

        return UUID.randomUUID() +"."+extension;
    }
}

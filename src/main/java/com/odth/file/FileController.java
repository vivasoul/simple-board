package com.odth.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files/upload")
    @ResponseBody
    public List<FileVO> uploadFiles(@RequestPart MultipartFile[] files) throws Exception{
        List<FileVO> list = null;

        if(files != null) {
            list = fileService.uploadFiles(files);
            fileService.insertFileInfo(list);
        }

        return list;
    }

    @DeleteMapping("/files/{fileId}")
    @ResponseBody
    public FileVO deleteFile(@PathVariable int fileId) throws IOException {
        FileVO vo = new FileVO();
        vo.setFileId(fileId);
        vo.setDelYn("Y");
        fileService.deleteFile(vo);
        return vo;
    }
}

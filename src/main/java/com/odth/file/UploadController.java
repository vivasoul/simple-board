package com.odth.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/files/upload")
    @ResponseBody
    public List<FileVO> uploadCommon(@RequestParam MultipartFile[] files) throws Exception{
        List<FileVO> list = null;

        if(files != null) {
            list = uploadService.uploadFiles(files);
        }

        return list;
    }
}

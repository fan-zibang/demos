package com.fanzibang.oss.controller;

import com.fanzibang.oss.template.PictureFileUpload;
import com.fanzibang.oss.template.VideoFileUpload;
import com.fanzibang.oss.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private PictureFileUpload pictureFileUpload;

    @Resource
    private VideoFileUpload videoFileUpload;

    // 上传单个分片
    @PostMapping("/upload")
    public ResultVO upload(
            @RequestPart("file") MultipartFile file, String type) {
        Boolean success = false;
        if ("video".equals(type)) {
            success = videoFileUpload.upload(file, "video/%s");
        } else if ("picture".equals(type)) {
            success = pictureFileUpload.upload(file, "picture/%s");
        }
        return ResultVO.success(success);
    }



}

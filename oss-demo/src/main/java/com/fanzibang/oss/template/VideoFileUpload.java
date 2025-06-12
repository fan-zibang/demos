package com.fanzibang.oss.template;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class VideoFileUpload extends FileUploadTemplate {

    @Override
    protected void valid(Object inputSource) {

    }

    @Override
    protected String generateFileName(String name) {
        return "";
    }

    @Override
    protected String getOriginFilename(Object inputSource) {
        return "";
    }

    @Override
    protected void processFile(Object inputSource, File file) throws Exception {

    }

}

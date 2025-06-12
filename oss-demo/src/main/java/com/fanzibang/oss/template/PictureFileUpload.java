package com.fanzibang.oss.template;

import cn.hutool.core.io.FileUtil;
import com.fanzibang.oss.util.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PictureFileUpload extends FileUploadTemplate {

    private final List<String> ALLOW_FILE_LIST = Arrays.asList("jpeg", "png", "jpg");

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");


    @Override
    protected void valid(Object inputSource) {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        if (Objects.isNull(multipartFile)) {
            throw new RuntimeException("文件不能为空");
        }
        long fileSize = multipartFile.getSize();
        if (fileSize > 1024 * 1024 * 2) {
            throw new RuntimeException("文件大小不能超过 2MB");
        }
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        if (!ALLOW_FILE_LIST.contains(fileSuffix)) {
            throw new RuntimeException("仅支持上传 jpeg、png、jpg 格式");
        }
    }

    @Override
    protected String generateFileName(String filename) {
        return String.format("%s_%s.%s", LocalDate.now().format(format), RandomUtils.generate(8),
                FileUtil.getSuffix(filename));
    }

    @Override
    protected String getOriginFilename(Object inputSource) {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        return multipartFile.getOriginalFilename();
    }

    @Override
    protected void processFile(Object inputSource, File file) throws Exception {
        MultipartFile multipartFile = (MultipartFile) inputSource;
        multipartFile.transferTo(file);
    }

}

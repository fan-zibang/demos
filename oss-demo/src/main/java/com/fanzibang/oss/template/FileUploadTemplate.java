package com.fanzibang.oss.template;

import com.fanzibang.oss.service.OssAdapterService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.File;


@Slf4j
public abstract class FileUploadTemplate {

    @Resource
    private OssAdapterService ossAdapterService;

    public Boolean upload(Object inputSource, String path) {

        valid(inputSource);

        String fileName = generateFileName(getOriginFilename(inputSource));

        String uploadPath = String.format("/%s/%s", path, fileName);

        File file = null;
        try {
            file = File.createTempFile(uploadPath, null);
            processFile(inputSource, file);
            String requestId = ossAdapterService.putObject(uploadPath, file);
            return requestId != null && !requestId.isEmpty();
        } catch (Exception e) {
            log.error("文件上传到对象存储失败", e);
            throw new RuntimeException("上传失败");
        } finally {
            // 6. 临时文件清理
            this.deleteTempFile(file);
        }

    }

    protected abstract void valid(Object inputSource);

    protected abstract String generateFileName(String name);

    protected abstract String getOriginFilename(Object inputSource);

    protected abstract void processFile(Object inputSource, File file) throws Exception;

    public void deleteTempFile(File file) {
        if (file == null) {
            return;
        }
        // 删除临时文件
        boolean deleted = file.delete();
        if (!deleted) {
            log.error("file delete error, filepath = {}", file.getAbsolutePath());
        }
    }

}

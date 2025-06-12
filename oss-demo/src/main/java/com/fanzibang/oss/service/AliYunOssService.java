package com.fanzibang.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.fanzibang.oss.config.AliYunOssConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

@Service
public class AliYunOssService implements OssAdapterService {

    @Resource
    private AliYunOssConfiguration aliYunOssConfiguration;

    @Resource
    private OSS aliOssClient;

    @Override
    public String putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliYunOssConfiguration.getBucket(),
                key, file);
        PutObjectResult result = aliOssClient.putObject(putObjectRequest);
        return result.getRequestId();
    }

    @Override
    public InputStream getObject(String key) {
        OSSObject object = aliOssClient.getObject(aliYunOssConfiguration.getBucket(), key);
        return object.getObjectContent();
    }

    @Override
    public void deleteObject(String key) {
        aliOssClient.deleteObject(aliYunOssConfiguration.getBucket(), key);
    }

}

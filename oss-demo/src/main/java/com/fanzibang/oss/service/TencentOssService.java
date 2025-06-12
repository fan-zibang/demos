package com.fanzibang.oss.service;

import com.fanzibang.oss.config.TencentOssConfiguration;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

@Service
public class TencentOssService implements OssAdapterService {

    @Resource
    private TencentOssConfiguration cosClientConfig;

    @Resource
    private COSClient cosClient;

    @Override
    public String putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key,
                file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return putObjectResult.getRequestId();
    }

    @Override
    public InputStream getObject(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        COSObject cosObject = cosClient.getObject(getObjectRequest);
        return cosObject.getObjectContent();
    }

    @Override
    public void deleteObject(String key) {
        cosClient.deleteObject(cosClientConfig.getBucket(), key);
    }

}

package com.fanzibang.oss.config;

import com.fanzibang.oss.service.AliYunOssService;
import com.fanzibang.oss.service.OssAdapterService;
import com.fanzibang.oss.service.TencentOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssAdapterConfiguration {

    @Value("${oss.adapter-type}")
    private String adapterType;

    @Bean
    public OssAdapterService ossAdapterService() {
        if ("aliyun".equals(adapterType)) {
            return new AliYunOssService();
        } else if ("tencent".equals(adapterType)) {
            return new TencentOssService();
        } else {
            throw new IllegalArgumentException("未找到对应的文件存储处理器");
        }
    }

}

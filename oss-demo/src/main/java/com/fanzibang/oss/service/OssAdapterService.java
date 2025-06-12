package com.fanzibang.oss.service;

import java.io.File;
import java.io.InputStream;

public interface OssAdapterService {

    String putObject(String key, File file);

    InputStream getObject(String key);

    void deleteObject(String key);


}

package com.jack.framework.util.network.interceptor;

/**
 * @Author: JACK-GU
 * @Date: 2017-07-24
 * @E-Mail: 528489389@qq.com
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}

package com.jackgu.androidframework.eventAction;

/**
 * @Author: JACK-GU
 * @Date: 2018/1/12
 * @E-Mail: 528489389@qq.com
 * <p>
 * 下载转发进度的
 */

public class DownloadFileMessageEvent {
    public final long bytesRead;
    public final long contentLength;
    public final boolean done;


    public DownloadFileMessageEvent(long bytesRead, long contentLength, boolean done) {
        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
        this.done = done;
    }
}

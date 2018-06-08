package com.jack.framework.view.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.jack.framework.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 下载文件的通知显示
 *
 * @Author: JACK-GU
 * @Date: 2018/1/18
 * @E-Mail: 528489389@qq.com
 */

public class DownLoadNotificationUtil {

    /**
     * 下载文件的时候展示
     *
     * @param activity      activity
     * @param title         状态栏的title
     * @param bytesRead     当前进度
     * @param contentLength 文件长度
     * @param fail          下载失败
     * @param res           图标
     * @Author: JACK-GU
     * @Date: 2018/1/18
     * @E-Mail: 528489389@qq.com
     */
    public static void show(Activity activity, String title, int res, long bytesRead, long
            contentLength, boolean fail) {
        NotificationManager mNotifyMgr =
                (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(activity, 0, new Intent(activity,
                activity.getClass()), 0);

        RemoteViews remoteViews = new RemoteViews(activity.getPackageName(), R.layout
                .layout_download_notification);
        remoteViews.setTextViewText(R.id.title, title);
        if (!fail) {
            remoteViews.setTextViewText(R.id.percentage, "下载失败");
            remoteViews.setTextViewText(R.id.content, "下载失败");
            remoteViews.setTextColor(R.id.percentage, activity.getResources().getColor(R.color
                    .red));
            remoteViews.setTextColor(R.id.content, activity.getResources().getColor(R.color.red));

            remoteViews.setProgressBar(R.id.progressBar, 1, 0, false);
        } else {
            if (contentLength == 0) {
                contentLength = 1;
            }
            double a = ((double) bytesRead / (double) contentLength);
            remoteViews.setProgressBar(R.id.progressBar, 100, (int) (a * 100), false);
            a = 100 * a;
            remoteViews.setTextViewText(R.id.percentage, String.format("%.2f", a) + "%");
            remoteViews.setTextViewText(R.id.content, bytesRead == contentLength ? "下载完成" :
                    getString(bytesRead) + "/" + getString(contentLength));
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(activity)
                        .setContentIntent(contentIntent)
                        .setCustomContentView(remoteViews)
                        .setSmallIcon(res);

        mNotifyMgr.notify(1000, mBuilder.build());
    }


    public static String getString(long value) {
        //传入的bytes
        long n = 1024;
        String m = "B";

        if (value >= 1024 * 1024) {
            //MB
            m = "MB";
            n = 1024 * 1024;
        } else if (value >= 1024) {
            //MB
            m = "KB";
            n = 1024;
        }


        String s = String.format("%.2f", ((double) value / (double) n)) + m;

        return s;
    }
}

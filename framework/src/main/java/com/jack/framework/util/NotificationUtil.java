package com.jack.framework.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 通知栏
 *
 * @Author: JACK-GU
 * @Date: 2018/1/18
 * @E-Mail: 528489389@qq.com
 */
public class NotificationUtil {

    public static void show(Activity activity,String title,String content,int res) {
        NotificationManager mNotifyMgr =
                (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(activity, 0, new Intent(activity,
                activity.getClass()), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(activity)
                        .setSmallIcon(res)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setContentIntent(contentIntent);

        mNotifyMgr.notify(1000, mBuilder.build());
    }
}

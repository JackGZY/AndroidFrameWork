package com.jack.framework.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 计时器,集成TextView
 * pureSeconds模式为：9999<p/>
 * 不然是：01天00时01分29秒，没有的不显示如：01分29秒，你也可以自己定义
 * 设置onRunListener，可以拿到天时分秒,也不会设置格式，必须在会掉里面设置,pureSeconds模式不会掉
 *
 * @Author: JACK-GU
 * @Date: 2018/4/26 13:43
 * @E-Mail: 528489389@qq.com
 */

public class TimerTextView extends androidx.appcompat.widget.AppCompatTextView {
    public static final int WHAT_SECOND = 0;
    public static final int WHAT_FINISH = 1;
    private Context mContext;
    private Timer timer;
    private long seconds; //单位秒
    private long finalSeconds;
    private boolean isRun = false;
    private String secondFormat;
    private OnFinishListener mOnFinishListener;
    private OnRunListener onRunListener;
    private boolean pureSeconds = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_SECOND) {
                set((Long) msg.obj);
            } else if (msg.what == WHAT_FINISH) {
                //主线程回调，不然子线程被清楚后，回调会报错
                isRun = false;
                if (mOnFinishListener != null) {
                    mOnFinishListener.onFinish();
                }
            }
        }
    };

    /**
     * 设置时间显示的样式,不设置为存计时（01天12或者01）,可以分段定义用%号隔开，
     * 最多两段，比如
     * 这个是计时器的时间，这里可以自己定义类型，比如：
     * 1. 重新发送(%)     重新发送（12）<p/>
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setSecondFormat(String secondFormat) {
        this.secondFormat = secondFormat;
    }

    /**
     * 设置计时回调
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setOnRunListener(OnRunListener onRunListener) {
        this.onRunListener = onRunListener;
    }

    /**
     * 设置是否为秒显示，即使超过60
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setPureSeconds(boolean pureSeconds) {
        this.pureSeconds = pureSeconds;
    }

    /**
     * 设置计时时间，单位秒
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * 获取状态，计时中还是计时完成
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public boolean isRun() {
        return isRun;
    }

    /**
     * 设置时间
     *
     * @param seconds 计时的秒数
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void setSeconds(long seconds) {
        if (!isRun) {
            this.seconds = seconds;
            this.finalSeconds =seconds;
            //设置字体
            //set(seconds);
        }
    }

    /**
     * 计时设置
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void set(long seconds) {
        //如果是存秒数
        if (pureSeconds) {
            if (secondFormat != null) {
                String strS[] = secondFormat.split("%");
                String format = strS[0] + "%02d";
                if (strS.length >= 2) {
                    format = format + strS[1];
                }
                setText(String.format(format, seconds));
            } else {
                setText(String.format("%d", seconds));
            }
        } else {
            //计算
            int day = (int) (seconds / (24 * 3600));
            int hour = (int) ((seconds - day * 24 * 3600) / 3600);
            int minute = (int) ((seconds - day * 24 * 3600 - hour * 3600) / 60);
            int second = (int) (seconds - (day * 24 * 3600 + hour * 3600 + minute * 60));

            boolean isUse = true;
            if (onRunListener != null) {
                isUse = onRunListener.onRun(day, hour, minute, second);
            }
            if (isUse) {
                //格式化
                String format = "";
                if (day > 0) {
                    //如果有天数
                    format = day < 10 ? "%02d天" : "%d天";
                }

                if (hour > 0 || format.contains("天")) {
                    //如果有天数，或者小时数大于0，那么就显示
                    format = format + "%02d时";
                }

                if (minute > 0 || format.contains("时")) {
                    //如果有时，说明分必须显示
                    format = format + "%02d分";
                }

                //秒是必须显示的
                format = format + "%02d秒";

                if (secondFormat != null) {
                    String strS[] = secondFormat.split("%");
                    format = strS[0] + format;
                    if (strS.length >= 2) {
                        format = format + strS[1];
                    }
                }

                if (format.contains("%d天") || format.contains("%02d天")) {
                    setText(String.format(format, day, hour, minute, second));
                } else if (format.contains("%02d时")) {
                    setText(String.format(format, hour, minute, second));
                } else if (format.contains("%02d分")) {
                    setText(String.format(format, minute, second));
                } else if (format.contains("%02d秒")) {
                    setText(String.format(format, second));
                }
            }
        }
    }


    public TimerTextView(Context context) {
        super(context);
        init(context);
    }

    public TimerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void init(Context context) {
        mContext = context;
        isRun = false;
        pureSeconds = false;
    }

    /**
     * 开始计时，这个之前记得设置计时时间
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public void startTimer(OnFinishListener finishListener) {
        mOnFinishListener = finishListener;
        this.seconds = finalSeconds;
        if (isRun) {
            //如果已经启动就什么也不做
            return;
        }
        isRun= true;
        set(this.seconds);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //减一秒
                seconds--;

                //判断,不要为0，所以为一后不会设置格式，用户自己处理
                if (seconds <= 0) {
                    //已经完成计时
                    handler.sendEmptyMessage(WHAT_FINISH);
                    timer.cancel();
                } else {
                    //设置
                    Message message = new Message();
                    message.what = WHAT_SECOND;
                    message.obj = seconds;
                    handler.sendMessage(message);
                }
            }
        }, 1000, 1000);//每一秒执行一次
    }

    public void cancel(){
        try {
            timer.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            timer =null;
        }
    }


    public interface OnFinishListener {
        void onFinish();
    }

    public interface OnRunListener {
        /**
         * 返回计时的具体
         *
         * @return false 不适用默认的覆盖显示格式，如果返回true就覆盖
         * @Author: JACK-GU
         * @E-Mail: 528489389@qq.com
         */
        boolean onRun(int day, int hour, int minute, int seconds);
    }


}

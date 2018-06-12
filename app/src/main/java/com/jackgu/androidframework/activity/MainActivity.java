package com.jackgu.androidframework.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import com.jack.framework.base.BaseTitleActivity;
import com.jack.framework.config.AppConfig;
import com.jack.framework.enums.GlideScaleType;
import com.jack.framework.eventAction.DownloadFileMessageEvent;
import com.jack.framework.util.DownLoadFileUtil;
import com.jack.framework.util.GlideUtil;
import com.jack.framework.util.LoggerUtil;
import com.jack.framework.util.ToastUtil;
import com.jack.framework.util.compress.CompressUtil;
import com.jack.framework.util.db.GreenDaoUtil;
import com.jack.framework.util.network.DefaultSubscriber;
import com.jack.framework.util.network.repository.DefaultRepository;
import com.jack.framework.view.ButtonHaveSelect;
import com.jack.framework.view.TimerTextView;
import com.jack.framework.view.dialog.MessageDialog;
import com.jack.framework.view.notification.DownLoadNotificationUtil;
import com.jackgu.androidframework.BuildConfig;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.entity.Test;
import com.jackgu.androidframework.retrofit.service.TestService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import rx.Subscriber;

public class MainActivity extends BaseTitleActivity {
    @BindView(R.id.imageView1)
    protected ImageView imageView1;
    @BindView(R.id.imageView2)
    protected ImageView imageView2;
    @BindView(R.id.imageView3)
    protected ImageView imageView3;
    @BindView(R.id.imageView4)
    protected ImageView imageView4;
    @BindView(R.id.imageView5)
    protected ImageView imageView5;
    @BindView(R.id.imageView6)
    protected ImageView imageView6;
    @BindView(R.id.imageView7)
    protected ImageView imageView7;
    @BindView(R.id.imageView8)
    protected ImageView imageView8;
    @BindView(R.id.imageView9)
    protected ImageView imageView9;
    @BindView(R.id.imageView10)
    protected ImageView imageView10;
    @BindView(R.id.myButton)
    ButtonHaveSelect myButton;
    @BindView(R.id.buttonHaveSelect)
    ButtonHaveSelect buttonHaveSelect;
    @BindView(R.id.buttonHaveSelect1)
    ButtonHaveSelect buttonHaveSelect1;
    @BindView(R.id.buttonHaveSelect2)
    ButtonHaveSelect buttonHaveSelect2;
    @BindView(R.id.buttonHaveSelect3)
    ButtonHaveSelect buttonHaveSelect3;
    @BindView(R.id.buttonHaveSelect4)
    ButtonHaveSelect buttonHaveSelect4;
    @BindView(R.id.timerTextView)
    TimerTextView timerTextView;


    private static final String PATH = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528439091467&di=cdeb7a7cf624c5d2fe6ad7c24b33e0da&imgtype=0&src=http%3A%2F%2Ftvax2.sinaimg.cn%2Fcrop.187.136.962.962.180%2F0070QoFGly8fm5zc7n1ymj310r0zk11q.jpg";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //请求权限
        checkPermission((success, strings) ->

        {
            if (success) {
                //创建文件
                File file = new File(AppConfig.DATA_BASE_FILE);
                if (!file.exists()) {
                    file.mkdirs();
                }

                //初始化数据库
                GreenDaoUtil.init();
            } else {
                if (!strings.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //创建文件
                    File file = new File(AppConfig.DATA_BASE_FILE);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    //初始化数据库
                    GreenDaoUtil.init();
                }
            }
        });


        setBackVisibility(true);
        setTitle("测试的标题这里是主页");

        /***** 伟大的分割线 ***** 计时器 ***** start *****/
        // timerTextView.setSecondFormat("重新发送(%)");
        timerTextView.setSeconds(2 * 24 * 3600 - 1 - 23 * 3600 - 58 * 60);
        timerTextView.setOnRunListener((day, hour, minute, seconds) -> {
            String s = "%d-%d:%d:%d";
            timerTextView.setText(String.format(s, day, hour, minute, seconds));

            return true;
        });
        timerTextView.startTimer(() -> {
            timerTextView.setText(R.string.send);
            showLongToast("计时完成");
        });
        /***** 伟大的分割线 ***** 计时器 ***** end *****/


        buttonHaveSelect1.setOnClickListener(v ->

                turnActivity(DrawerLayoutActivity.class, false,
                        null));

        myButton.setOnClickListener(v ->

        {
//            List<SelectDialog.SelectItem> selectItems = new ArrayList<>();
//            selectItems.add(new SelectDialog.SelectItem("测试按钮1"));
//            selectItems.add(new SelectDialog.SelectItem("测试按钮2", getResources().getColor(R.color
//                    .theme)));
//            selectItems.add(new SelectDialog.SelectItem("测试按钮3", getResources().getColor(R.color
//                    .red), R.mipmap.ic_launcher_round));
//            selectItems.add(new SelectDialog.SelectItem("测试按钮4", getResources().getColor(R.color
//                    .red), R.mipmap.ic_launcher_round, Gravity.LEFT));
//            selectItems.add(new SelectDialog.SelectItem("测试按钮5", getResources().getColor(R.color
//                    .red), R.mipmap.ic_launcher_round, Gravity.RIGHT));
//
//            SelectDialog selectDialog = new SelectDialog(this, selectItems, true);
//            selectDialog.show();
//            selectDialog.setTitle("测试的标题");
//            selectDialog.setContent("*测试的提示类容，在这里如果有危险操作我们可以提醒用户");
//            selectDialog.setOnItemClick(index -> ToastUtil.showShortMessage("" + index));

            MessageDialog messageDialog = new MessageDialog(mContext, MessageDialog
                    .TYPE_THREE_BUTTON);
            messageDialog.show();
            messageDialog.setButtonTexts("取消1", "好的1", "确定1");
        });


        buttonHaveSelect2.setOnClickListener(v -> {
//            showProgressDialog("测试的消息很长的那种消息哦，超过屏幕一半了的！");
//
//            //三秒后消失
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new TimerTask() {
//                        @Override
//                        public void run() {
//                            closeProgressDialog();
//                        }
//                    });
//                }
//            }, 3000);

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        });

        buttonHaveSelect3.setOnClickListener(v ->

        {
            turnActivity(FragmentTestActivity.class, false, null);
        });


        buttonHaveSelect4.setOnClickListener(v ->

        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri = null;
            File file = new File(AppConfig.DATA_BASE_FILE + "/1.jpg");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                uri = Uri.fromFile(file);
            } else {
                String s = BuildConfig.APPLICATION_ID + ".fileprovider";
                uri = FileProvider.getUriForFile(mContext, s, file);
                //添加权限
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 1);
        });

        buttonHaveSelect.setOnClickListener(v ->

        {
            //这里是需要文件的全路径
            DownLoadFileUtil.downLoadFile
                    ("http://101.204.240.105:50203/bizmodules/templates/app/lt" +
                            ".apk", AppConfig.BASE_FILE, new DownLoadFileUtil
                            .FileDownLoadCallBack() {
                        @Override
                        public void callBack(String msg, boolean success) {
                            DownLoadNotificationUtil.show(mContext, "软件更新测试", R.mipmap
                                    .ic_launcher_round, 1, 1, success);
                        }

                        @Override
                        public void callBack(long bytesRead, long contentLength, boolean done) {
                            LoggerUtil.e("bytesRead= " + bytesRead + ",contentLength= " +
                                    contentLength);
                            DownLoadNotificationUtil.show(mContext, "软件更新测试", R.mipmap
                                    .ic_launcher_round, bytesRead, contentLength, true);
                        }
                    });
        });

        GlideUtil.load(PATH, imageView1);

        GlideUtil.load(PATH, imageView2, GlideScaleType.centerCrop);

        GlideUtil.load(PATH, imageView3, GlideScaleType.fitCenter);

        GlideUtil.loadCircle(PATH, imageView4, GlideScaleType.centerCrop);

        GlideUtil.loadCircle(PATH, imageView5);

        GlideUtil.loadRound(PATH, imageView6);

        GlideUtil.loadRound(PATH, imageView7, GlideScaleType.centerCrop);

        GlideUtil.loadRound("", imageView8);


        DefaultRepository.getInstance().

                submit(TestService.class, Test.class, "get", new
                        HashMap<>()).

                subscribe(new DefaultSubscriber<Test>() {
                    @Override
                    public void _onNext(Test entity) {
                        LoggerUtil.e(entity.toString());
                    }

                    @Override
                    public void _onError(String msg) {
                        LoggerUtil.e(msg);
                    }
                });
    }

    @Override
    protected void viewDrawFinished() {
        super.viewDrawFinished();
        LoggerUtil.e("绘制页面完成回调");
        LoggerUtil.e("Width:" + myButton.getWidth() + ",Height:" + myButton.getHeight());
    }

    //这里用eventbus订阅进度,一定要切换到主线程，如果有UI方面的操作
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(DownloadFileMessageEvent downloadFileMessageEvent) {
        LoggerUtil.e("bytesRead:" + downloadFileMessageEvent.bytesRead + ",contentLength:" +
                downloadFileMessageEvent.contentLength + "," + "done=" + downloadFileMessageEvent
                .done);
        if (downloadFileMessageEvent.done)
            ToastUtil.showLongMessage("下载完成!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            File file = new File(AppConfig.DATA_BASE_FILE + "/1.jpg");
            CompressUtil.compress(file.getAbsolutePath(), true).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LoggerUtil.e("压缩失败");
                }

                @Override
                public void onNext(String s) {
                    LoggerUtil.e("压缩成功，地址：" + s);
                }
            });
        }
    }
}

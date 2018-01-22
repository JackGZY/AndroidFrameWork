package com.jackgu.androidframework.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jackgu.androidframework.R;
import com.jackgu.androidframework.base.BaseTitleActivity;
import com.jackgu.androidframework.config.AppConfig;
import com.jackgu.androidframework.entity.Test;
import com.jackgu.androidframework.enums.GlideScaleType;
import com.jackgu.androidframework.eventAction.DownloadFileMessageEvent;
import com.jackgu.androidframework.util.DownLoadFileUtil;
import com.jackgu.androidframework.util.GlideUtil;
import com.jackgu.androidframework.util.LoggerUtil;
import com.jackgu.androidframework.util.StringUtils;
import com.jackgu.androidframework.util.ToastUtil;
import com.jackgu.androidframework.util.db.GreenDaoUtil;
import com.jackgu.androidframework.util.network.DefaultSubscriber;
import com.jackgu.androidframework.util.network.repository.DefaultRepository;
import com.jackgu.androidframework.util.network.service.TestService;
import com.jackgu.androidframework.view.ButtonHaveSelect;
import com.jackgu.androidframework.view.dialog.SelectDialog;
import com.jackgu.androidframework.view.notification.DownLoadNotificationUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.buttonHaveSelect2)
    ButtonHaveSelect buttonHaveSelect2;

    private static final String PATH = "https://timgsa.baidu" +
            ".com/timg?image&quality=80&size=b9999_10000&sec=1515732084256&di" +
            "=3e7468d937f2c3c26ac0c959f59784e6&imgtype=0&src=http%3A%2F%2Fwww.bumimi" +
            ".com%2Fuploads%2Fvod%2F2017-11-13%2F5a09508e6d43d.jpg";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        //请求权限
        checkPermission((success, strings) -> {
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
        title.setText("测试的标题这里是主页");


        buttonHaveSelect1.setOnClickListener(v -> turnActivity(DrawerLayoutActivity.class, false,
                null));

        myButton.setOnClickListener(v -> {
            List<SelectDialog.SelectItem> selectItems = new ArrayList<>();
            selectItems.add(new SelectDialog.SelectItem("测试按钮1"));
            selectItems.add(new SelectDialog.SelectItem("测试按钮2", getResources().getColor(R.color
                    .theme)));
            selectItems.add(new SelectDialog.SelectItem("测试按钮3", getResources().getColor(R.color
                    .red), R.mipmap.ic_launcher_round));
            selectItems.add(new SelectDialog.SelectItem("测试按钮4", getResources().getColor(R.color
                    .red), R.mipmap.ic_launcher_round, Gravity.LEFT));
            selectItems.add(new SelectDialog.SelectItem("测试按钮5", getResources().getColor(R.color
                    .red), R.mipmap.ic_launcher_round, Gravity.RIGHT));

            SelectDialog selectDialog = new SelectDialog(this, selectItems, true);
            selectDialog.show();
            selectDialog.setTitle("测试的标题");
            selectDialog.setContent("*测试的提示类容，在这里如果有危险操作我们可以提醒用户");
            selectDialog.setOnItemClick(index -> ToastUtil.showShortMessage("" + index));
        });


        buttonHaveSelect2.setOnClickListener(v -> {

        });

        buttonHaveSelect.setOnClickListener(v -> {
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


        DefaultRepository.getInstance().submit(TestService.class, Test.class,
                "get", new HashMap<>()).subscribe(new DefaultSubscriber<Test>() {
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
                downloadFileMessageEvent.contentLength + "," +
                "done=" + downloadFileMessageEvent.done);
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
}

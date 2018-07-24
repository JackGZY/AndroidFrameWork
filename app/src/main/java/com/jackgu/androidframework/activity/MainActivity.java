package com.jackgu.androidframework.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.framework.base.BaseTitleActivity;
import com.jack.framework.config.AppConfig;
import com.jack.framework.enums.GlideType;
import com.jack.framework.enums.RxLifeEvent;
import com.jack.framework.eventAction.DownloadFileMessageEvent;
import com.jack.framework.util.GlideUtil;
import com.jack.framework.util.LoggerUtil;
import com.jack.framework.util.ToastUtil;
import com.jack.framework.util.UriUtil;
import com.jack.framework.util.compress.CompressUtil;
import com.jack.framework.util.network.DefaultSubscriber;
import com.jack.framework.util.network.repository.MyJsonRepository;
import com.jack.framework.util.network.repository.MyRepository;
import com.jack.framework.view.ButtonHaveSelect;
import com.jack.framework.view.TimerTextView;
import com.jack.framework.view.dialog.SelectDialog;
import com.jackgu.androidframework.R;
import com.jackgu.androidframework.entity.TestEntity;
import com.jackgu.androidframework.retrofit.service.TestService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
    @BindView(R.id.buttonHaveSelect5)
    ButtonHaveSelect buttonHaveSelect5;
    @BindView(R.id.timerTextView)
    TimerTextView timerTextView;
    @BindView(R.id.textView1)
    TextView textView1;


    private static final String PATH = "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=9fc27aa361224f4a4899751339f69044/b3b7d0a20cf431ade6d64fbc4736acaf2edd982a.jpg";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //请求权限
        checkPermission((success, strings) ->
        {
//            if (success) {
//                //创建文件
//                File file = new File(AppConfig.DATA_BASE_FILE);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//
//                //初始化数据库
//                //GreenDaoUtil.init();
//            } else {
//                if (!strings.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                    //创建文件
//                    File file = new File(AppConfig.DATA_BASE_FILE);
//                    if (!file.exists()) {
//                        file.mkdirs();
//                    }
//
//                    //初始化数据库
//                   // GreenDaoUtil.init();
//                }
//            }
        });

      //  setBackVisibility(false);
        setTitle("测试的标题这里是主页123231231231231212312312");
        setBackOnClickListener(v -> {

        });

        addRightButton(R.drawable.menu_add, v -> {
            ToastUtil.showShortMessage("add");
        });

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
                        null)
        );

        myButton.setOnClickListener(v ->

        {
            turnActivity(TestActivity.class, false, null);
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

//            MessageDialog messageDialog = new MessageDialog(mContext, MessageDialog
//                    .TYPE_THREE_BUTTON);
//            messageDialog.show();
//            messageDialog.setButtonTexts("取消1", "好的1", "确定1");
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

//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("*/*");
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            startActivityForResult(intent, 1);
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

        buttonHaveSelect3.setOnClickListener(v -> {
            turnActivity(FragmentTestActivity.class, false, null);
        });


        buttonHaveSelect4.setOnClickListener(v ->

        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(AppConfig.DATA_BASE_FILE + "/1.jpg");
            Uri uri = UriUtil.getUriFromFile(file, intent);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 1);
        });

        buttonHaveSelect.setOnClickListener(v ->

        {

            turnActivity(CoordinatorLayoutActivity.class, false, null);
            //这里是需要文件的全路径
//            DownLoadFileUtil.downLoadFile
//                    ("http://101.204.240.105:50203/bizmodules/templates/app/lt" +
//                            ".apk", AppConfig.BASE_FILE, new DownLoadFileUtil
//                            .FileDownLoadCallBack() {
//                        @Override
//                        public void callBack(String msg, boolean success) {
//                            DownLoadNotificationUtil.show(mContext, "软件更新测试", R.mipmap
//                                    .ic_launcher_round, 1, 1, success);
//                        }
//
//                        @Override
//                        public void callBack(long bytesRead, long contentLength, boolean done) {
//                            LoggerUtil.e("bytesRead= " + bytesRead + ",contentLength= " +
//                                    contentLength);
//                            DownLoadNotificationUtil.show(mContext, "软件更新测试", R.mipmap
//                                    .ic_launcher_round, bytesRead, contentLength, true);
//                        }
//                    });

        });

        GlideUtil.load(PATH, imageView1);

        GlideUtil.load(PATH, imageView2, GlideType.centerCrop);

        GlideUtil.load(PATH, imageView3, GlideType.fitCenter);

        GlideUtil.loadCircle(PATH, imageView4);
        GlideUtil.loadCircle("", imageView7, (requestOptions, requestBuilder) -> {
            //我们在这里修改,加载失败显示的是空
            requestOptions.placeholder(AppConfig.IMAGE_EMPTY);
            requestOptions.error(AppConfig.IMAGE_EMPTY);
            requestOptions.fallback(AppConfig.IMAGE_EMPTY);
        });

        GlideUtil.loadRound(PATH, imageView5, 30);

        GlideUtil.loadRound("", imageView6, 30);
        GlideUtil.loadRound(null, imageView8, 30);


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", 1);
        hashMap.put("page", 1);
        MyRepository<List<TestEntity>> myRepository = new MyRepository<>();
        myRepository.get(TestService.class, hashMap)
                .compose(bindUntilEvent(RxLifeEvent.DESTROY))
                .subscribe(new DefaultSubscriber<List<TestEntity>>() {
                    @Override
                    public void _onNext(List<TestEntity> entity) {
                        for (TestEntity testEntity : entity) {
                            LoggerUtil.e(testEntity.toString());
                        }
                    }

                    @Override
                    public void _onError(String msg) {
                        LoggerUtil.e(msg);
                    }
                });


        MyJsonRepository<TestEntity> myJsonObjectRepository =
                new MyJsonRepository<>(TestEntity.class);
        myJsonObjectRepository.postReturnArray(hashMap)
                .compose(bindUntilEvent(RxLifeEvent.DESTROY))
                .subscribe(new DefaultSubscriber<List<TestEntity>>() {
                    @Override
                    public void _onNext(List<TestEntity> entity) {
                        for (TestEntity testEntity : entity) {
                            LoggerUtil.e(testEntity.toString());
                        }
                    }

                    @Override
                    public void _onError(String msg) {
                        LoggerUtil.e(msg);
                    }
                });


//        RetrofitHelper.getInstance().createService(TestService.class).getTest(hashMap)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            String s = response.body().string();
//                            LoggerUtil.e(s);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });

        buttonHaveSelect5.setOnClickListener(v -> {
            String url = "https://github.com/traex/RippleEffect/blob" +
                    "/master/demo.gif?raw=true";
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder().url(url).build();

            okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {

                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response)
                        throws IOException {
                    response.body().byteStream();
                }
            });
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
        timerTextView.cancel();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
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
            CompressUtil.compress(file.getAbsolutePath(), true).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    LoggerUtil.e("压缩成功，地址：" + s);
                }

                @Override
                public void onError(Throwable e) {
                    LoggerUtil.e("压缩失败");
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}

# Android快速开发框架
# 1. 简介
	本框架是基于以下框架进行封装集成的几套快速开发框架，对常用的控件、Activity、工具类进行了一些封装
### 下面是本项目使用的一些开源框架

     名称      |     地址     |     用途
------------- | -------------|------------
Glide         | [Github](https://github.com/bumptech/glide)   |   图片加载
Gson          | [Github](https://github.com/google/gson)      |   数据解析
Logger        | [Github](https://github.com/orhanobut/logger) |   日志输出
Butterknife   | [Github](https://github.com/JakeWharton/butterknife) | 控件绑定
RXJava | [Github](https://github.com/ReactiveX/RxJava) | 用于线程切换，网络请求等待
RXAndroid | [Github](https://github.com/ReactiveX/RxAndroid) | 用于线程切换，网络请求等待
Retrofit2 | [Github](https://github.com/square/retrofit)| 网络请求
GreenDao | [Github](https://github.com/greenrobot/greenDAO) | 数据库
Eventbus | [Github](https://github.com/greenrobot/EventBus) | 取代广播，进行通信
BaseRecyclerViewAdapterHelper | [Github](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) | 通用RecyclerView适配器
SmartRefreshLayout | [Github](https://github.com/scwang90/SmartRefreshLayout) | 下拉刷新和上拉加载更多

## 2. 项目特色
* 封装了Activity可以快速设置头部，且支持自定义，可以结合RxAndroid实现生命周期的网络请求，以及其他一些常用的功能；
* Fragment也进行了和Activity的封装；
* 监听页面绘制完成，提供回调，为卡顿等问题提供解决方案；
* 对Android 8.0的文件权限进行了适配；
* 对Android 6.0以后的动态权限进行了封装，只需要在Activity调用即可；
* 按钮选择器类，只有包裹在View外层就可以实现按下去的效果，不用辛苦的写选择器；
* 下拉刷新，上拉加载更多帮助类封装，更加的简单实现，降低了与View的耦合度的同时也方便后期的修改；
* 消息提示对话框、加载中对话框、选择项对话框封装，纯Java代码，方便复用；
* 基于Retrofit2进行了再次封装，更加方便使用；
* 对文件到MultipartBody、 MultipartBody.Part进行了封装，传文件更加的方便；
* 对Retrofit2文件下载进行了封装，可以监听进度；
* 对Glide进行了封装，加载图片更加快捷灵活；
* 其他常用的工具类封装；

## 3. 使用
### 1. 
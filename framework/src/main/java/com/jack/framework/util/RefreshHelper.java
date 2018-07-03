package com.jack.framework.util;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jack.framework.R;
import com.jack.framework.view.WrapContentGridLayoutManager;
import com.jack.framework.view.WrapContentLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 刷新的帮助类，需要传入SmartRefreshLayout和RecyclerView，设置总的数量，自动判断有没有更多
 * <p>
 * 如果不需要刷新功能，可以不设置SmartRefreshLayout，传入null
 * </p>
 * <p>
 * <strong>建议（其实是必须这么用）：</strong>
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;1. 将有无更多等逻辑交由本类处理，页面不需要次有实体数组，不需要持有adapter，全部由本类保管
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;2. 设置total(setTotal方法)，保证加载更多逻辑的正确使用
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;3. 建议分页的时候，通过本类获取当前的page(page的开始下标，默认是0，可设置PAGE_START来修改)
 * ，降低RecyclerView与activity的耦合度
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;4. 建议使用RefreshHelper的setOnRefreshAndLoadMoreListener
 * 来设置监听，而不是直接设置SmartRefreshLayout的监听，因为这里面已经做了page的处理
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;5. RecyclerView不用设置manger，本类会设置，两个构造方法，传入了number的为grid，但是间距需要自己设置
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;6. 传入BaseQuickAdapter的实例的时候，只需要构造传入layoutRes就可以了
 * </p>
 * <p>
 * <p>
 * <strong>其他（个人习惯）：</strong>
 * </p>
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;
 * 设置监听事件和创建本类的实例可使用动态模板（前提是自己加了的）来进行，名字分别为：setOnRefreshAndLoadMoreListener和refreshHelper
 * </p>
 *
 * @Author: JACK-GU
 * @Date: 2018/3/30 10:03
 * @E-Mail: 528489389@qq.com
 */
public class RefreshHelper<T> {
    private static final int PAGE_START = 1;//第一页的下标
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private BaseQuickAdapter baseQuickAdapter;
    private List<T> dataList;
    private View emptyView;//空的时候显示的view
    private int total = 0; //总共的条数，用来判断是不是需要加载更多还是加载完成
    private OnRefreshAndLoadMoreListener onRefreshAndLoadMoreListener;
    private int page = PAGE_START;//页数

    /**
     * 获取adapter
     *
     * @Author: JACK-GU
     * @Date: 2018/4/9 15:26
     * @E-Mail: 528489389@qq.com
     */
    public BaseQuickAdapter getBaseQuickAdapter() {
        return baseQuickAdapter;
    }

    /**
     * 通过该方法自动获取当前的页数，前提是onRefreshAndLoadMoreListener这里面调用
     */
    public int getPage() {
        return page;
    }

    public OnRefreshAndLoadMoreListener getOnRefreshAndLoadMoreListener() {
        return onRefreshAndLoadMoreListener;
    }

    /**
     * 设置监听
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 14:01
     * @E-Mail: 528489389@qq.com
     */
    public void setOnRefreshAndLoadMoreListener(OnRefreshAndLoadMoreListener
                                                        onRefreshAndLoadMoreListener) {
        this.onRefreshAndLoadMoreListener = onRefreshAndLoadMoreListener;
    }

    public int getTotal() {
        return total;
    }

    /**
     * 设置总的数量
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * list类型的
     *
     * @param baseQuickAdapter   适配器
     * @param recyclerView       列表
     * @param context            上下文
     * @param smartRefreshLayout 刷新组件,可以不传
     * @Author: JACK-GU
     * @Date: 2018/3/30 10:42
     * @E-Mail: 528489389@qq.com
     */
    public RefreshHelper(SmartRefreshLayout smartRefreshLayout, RecyclerView recyclerView,
                         BaseQuickAdapter baseQuickAdapter, Context context) {
        init(smartRefreshLayout, recyclerView, baseQuickAdapter, context, -1);
    }


    /**
     * grid类型的
     *
     * @param baseQuickAdapter   适配器
     * @param recyclerView       列表
     * @param smartRefreshLayout 刷新组件,可以不传
     * @param number             一行的个数
     * @param context            上下文
     * @Author: JACK-GU
     * @Date: 2018/3/30 10:42
     * @E-Mail: 528489389@qq.com
     */
    public RefreshHelper(SmartRefreshLayout smartRefreshLayout, RecyclerView recyclerView,
                         BaseQuickAdapter baseQuickAdapter, Context context, int number) {
        init(smartRefreshLayout, recyclerView, baseQuickAdapter, context, number);
    }

    /**
     * 初始化
     *
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    private void init(SmartRefreshLayout smartRefreshLayout, RecyclerView recyclerView,
                      BaseQuickAdapter baseQuickAdapter, Context context, int number) {
        this.smartRefreshLayout = smartRefreshLayout;
        this.recyclerView = recyclerView;
        this.baseQuickAdapter = baseQuickAdapter;

        this.dataList = new ArrayList<>();
        this.baseQuickAdapter.setNewData(this.dataList);

        if (number <= 0) {
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new WrapContentGridLayoutManager(context,
                    number, GridLayoutManager.VERTICAL, false));
        }


        baseQuickAdapter.bindToRecyclerView(recyclerView);
        //创建空的view
        emptyView = LayoutInflater.from(context).inflate(R.layout.layout_empty, null);
        baseQuickAdapter.setEmptyView(emptyView);

        if (smartRefreshLayout != null) {
            //设置不开启自动加载多多
            smartRefreshLayout.setEnableAutoLoadMore(false);
            //默认不开启加载更多，只有当刷新后手动开启
            smartRefreshLayout.setEnableLoadMore(false);
            //关闭不满一页关闭上拉
            smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);

            smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
                //刷新，变为0;
                this.page = PAGE_START;

                if (onRefreshAndLoadMoreListener != null) {
                    onRefreshAndLoadMoreListener.onRefresh(refreshLayout);
                }
            });

            smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
                //否则++
                this.page++;

                if (onRefreshAndLoadMoreListener != null) {
                    onRefreshAndLoadMoreListener.onLoadMore(refreshLayout);
                }
            });
        }
    }

    /**
     * 添加一条数据
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 10:27
     * @E-Mail: 528489389@qq.com
     */
    public void add(T t) {
        /****** 伟大的分割线 ****** 避免加载更多数据的时候，item的间隔不对 ****** start ******/
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int n = 0;
        //添加前的长度
        int oldSize = dataList.size();

        if (layoutManager instanceof GridLayoutManager) {
            n = ((GridLayoutManager) layoutManager).getSpanCount();
            //计算最后一排的个数
            int m = oldSize % n;
            n = m == 0 ? n : m;
        } else if (layoutManager instanceof LinearLayoutManager) {
            n = 1;
        }


        baseQuickAdapter.addData(t);
        if (oldSize - n >= 0) {
            baseQuickAdapter.notifyItemRangeChanged(oldSize - n, n);
        }
        /****** 伟大的分割线 ****** 避免加载更多数据的时候，item的间隔不对 ****** end ******/

        //加入新的数据，判断一下是不是满了
        haveLoad();
    }

    /**
     * 添加多条数据
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 10:27
     * @E-Mail: 528489389@qq.com
     */
    public void add(List<T> dataS) {
        /****** 伟大的分割线 ****** 避免加载更多数据的时候，item的间隔不对 ****** start ******/
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int n = 0;
        //添加前的长度
        int oldSize = dataList.size();

        if (layoutManager instanceof GridLayoutManager) {
            n = ((GridLayoutManager) layoutManager).getSpanCount();
            //计算最后一排的个数
            int m = oldSize % n;
            n = m == 0 ? n : m;
        } else if (layoutManager instanceof LinearLayoutManager) {
            n = 1;
        }


        baseQuickAdapter.addData(dataS);
        if (oldSize - n >= 0) {
            baseQuickAdapter.notifyItemRangeChanged(oldSize - n, n);
        }
        /****** 伟大的分割线 ****** 避免加载更多数据的时候，item的间隔不对 ****** end ******/
        //加入新的数据，判断一下是不是满了
        haveLoad();
    }

    /**
     * 适合刷新的时候调用,不会修改Adapter的list引用
     *
     * @param dataS 新的数据
     * @Author: JACK-GU
     * @Date: 2018/3/30 11:19
     * @E-Mail: 528489389@qq.com
     */
    public void reSet(List<T> dataS) {
        baseQuickAdapter.replaceData(dataS);

        //加入新的数据，判断一下是不是满了
        haveLoad();
    }

    public T getData(int position) {
        return dataList.get(position);
    }


    /**
     * 移除一个数据
     *
     * @param index 下标
     * @Author: JACK-GU
     * @Date: 2018/3/30 10:30
     * @E-Mail: 528489389@qq.com
     */
    public void removeData(int index) {
        baseQuickAdapter.remove(index);

        //加入新的数据，判断一下是不是满了
        haveLoad();
    }

    /**
     * 设置监听
     *
     * @param listener 监听器
     * @return void
     * @Author: JACK-GU
     * @Date: 2018/4/27 10:01
     * @E-Mail: 528489389@qq.com
     */
    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener listener) {
        baseQuickAdapter.setOnItemClickListener(listener);
    }


    /**
     * 没有更多数据了
     *
     * @param loadAll 是否已经加载全部数据
     * @Author: JACK-GU
     * @Date: 2018/3/30 11:04
     * @E-Mail: 528489389@qq.com
     */
    public void loadAll(boolean loadAll) {
        if (smartRefreshLayout != null)
            smartRefreshLayout.setNoMoreData(loadAll);
    }


    /**
     * 判断是不是还有数据
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 11:13
     * @E-Mail: 528489389@qq.com
     */
    public void haveLoad() {
        if (smartRefreshLayout != null)
            //开启上拉
            smartRefreshLayout.setEnableLoadMore(true);

        loadAll(total <= this.dataList.size());
    }

    /**
     * 完成就刷新
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 11:42
     * @E-Mail: 528489389@qq.com
     */
    public void finishRefresh() {
        if (smartRefreshLayout != null)
            smartRefreshLayout.finishRefresh();
    }


    /**
     * 完成加载更多
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 11:42
     * @E-Mail: 528489389@qq.com
     */
    public void finishLoadMore() {
        if (smartRefreshLayout != null)
            smartRefreshLayout.finishLoadMore();
    }


    /**
     * 自动的调用下拉的东西
     *
     * @Author: JACK-GU
     * @Date: 2018/3/30 14:12
     * @E-Mail: 528489389@qq.com
     */
    public void autoRefresh() {
        if (smartRefreshLayout != null)
            smartRefreshLayout.autoRefresh();
    }

    public interface OnRefreshAndLoadMoreListener {
        void onRefresh(RefreshLayout refreshLayout);

        void onLoadMore(RefreshLayout refreshLayout);
    }

}

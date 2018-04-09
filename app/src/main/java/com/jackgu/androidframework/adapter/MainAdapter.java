package com.jackgu.androidframework.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackgu.androidframework.R;

import java.util.List;


/**
 * 主页测试的适配器
 *
 * @Author: JACK-GU
 * @Date: 2018/3/30 10:34
 * @E-Mail: 528489389@qq.com
 */
public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MainAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public MainAdapter(@Nullable List<String> data) {
        super(data);
    }

    public MainAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.textView1, item);
    }
}

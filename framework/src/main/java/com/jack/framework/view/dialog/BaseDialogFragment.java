package com.jack.framework.view.dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 基础的，防止显示的时候报错
 *
 * @Author: JACK-GU
 * @Date: 2018-09-05 12:49
 * @E-Mail: 528489389@qq.com
 */
public class BaseDialogFragment extends DialogFragment {
    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        try {
            return super.show(transaction, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

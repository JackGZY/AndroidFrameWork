package com.jack.framework.util;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


/**
 * grid的ItemDecoration计算
 *
 * @Author: JACK-GU
 * @Date: 2018/3/5 10:17
 * @E-Mail: 528489389@qq.com
 */

public class ItemDecorationUtil {
    /**
     * 会在第二个开始每个上面加,适合list
     *
     * @param outRect
     * @param view
     * @param parent  RecyclerView
     * @param space   间距dp
     * @Author: JACK-GU
     * @Date: 2018/3/5 10:18
     * @E-Mail: 528489389@qq.com
     */
    public static void topAuto(Rect outRect, View view, RecyclerView parent, int
            space) {
        int childPosition = parent.getChildAdapterPosition(view);
        if (childPosition > 0) {
            outRect.top = space;
        }
    }


    /**
     * 右边和上边为设定的值，下边和左边会计算选择自动添加,适合gird
     *
     * @param outRect
     * @param view
     * @param parent  RecyclerView
     * @param space   间距dp
     * @Author: JACK-GU
     * @Date: 2018/3/5 10:18
     * @E-Mail: 528489389@qq.com
     */
    public static void leftAndBottomAuto(Rect outRect, View view, RecyclerView parent, int
            space) {
        outRect.top = space;
        outRect.right = space;
        /**
         * 计算底部
         * */
        int childPosition = parent.getChildAdapterPosition(view);
        //计算，如果是最好一排的，需要设置底部的
        GridLayoutManager gridLayoutManager = (GridLayoutManager)
                parent.getLayoutManager();
        //得到行数
        int flag = parent.getAdapter().getItemCount() / gridLayoutManager.getSpanCount();
        if (flag == 0) {
            //只有一行的话
            outRect.bottom = space;

            /**
             *
             * 计算左边的
             * */
            if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 1) {
                outRect.left = space;
            }

            return;
        }
        //得到余数
        int flag1 = parent.getAdapter().getItemCount() % gridLayoutManager.getSpanCount();

        if (flag1 == 0) {
            //说明就说最后一行的
            if (childPosition >= ((flag - 1) * gridLayoutManager.getSpanCount())) {
                //需要底部
                outRect.bottom = space;
            }
        } else {
            //说明是余数
            if (childPosition >= (flag * gridLayoutManager.getSpanCount())) {
                //需要底部
                outRect.bottom = space;
            }
        }

        /**
         * 计算左边的
         * */
        if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 1) {
            outRect.left = space;
        }
    }

    /**
     * 右边和上边为设定的值，下边和左边会计算选择自动添加,适合gird
     *
     * @param outRect
     * @param view
     * @param parent  RecyclerView
     * @param space   间距dp
     * @param offset  这个是有多少个头部,设置了头部不受影响
     * @Author: JACK-GU
     * @Date: 2018/3/5 10:18
     * @E-Mail: 528489389@qq.com
     */
    public static void leftAndBottomButHeadNotEffect(Rect outRect, View view, RecyclerView
            parent, int space, int offset) {
        /**
         * 计算底部
         * */
        int childPosition = parent.getChildAdapterPosition(view) - offset;
        if (childPosition < 0) {//头部的是负数，因为减去了个数的
            return;
        }

        outRect.top = space;
        outRect.right = space;
        //计算，如果是最好一排的，需要设置底部的
        GridLayoutManager gridLayoutManager = (GridLayoutManager)
                parent.getLayoutManager();
        //得到行数
        int flag = parent.getAdapter().getItemCount() / gridLayoutManager.getSpanCount();
        if (flag == 0) {
            //只有一行的话
            outRect.bottom = space;

            /**
             *
             * 计算左边的
             * */
            if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 1) {
                outRect.left = space;
            }

            return;
        }
        //得到余数
        int flag1 = parent.getAdapter().getItemCount() % gridLayoutManager.getSpanCount();

        if (flag1 == 0) {
            //说明就说最后一行的
            if (childPosition >= ((flag - 1) * gridLayoutManager.getSpanCount())) {
                //需要底部
                outRect.bottom = space;
            }
        } else {
            //说明是余数
            if (childPosition >= (flag * gridLayoutManager.getSpanCount())) {
                //需要底部
                outRect.bottom = space;
            }
        }

        /**
         * 计算左边的
         * */
        if ((childPosition + 1) % gridLayoutManager.getSpanCount() == 1) {
            outRect.left = space;
        }
    }
}

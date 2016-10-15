package com.forezp.banya.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration  {


    private int left;
    private int right;
    private int top;
    private int bottom;
    public SpacesItemDecoration(int space) {
        this.left=space;
        this.right=space;
        this.top=space;
        this.bottom=space;

    }

    public SpacesItemDecoration(int left, int right, int top, int bottom) {
        this.left=left;
        this.right=right;
        this.top=top;
        this.bottom=bottom;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=left;
        outRect.right=right;
        outRect.bottom=bottom;
        outRect.top=top;

    }
}

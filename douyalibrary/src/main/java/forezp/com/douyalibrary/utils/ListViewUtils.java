package forezp.com.douyalibrary.utils;



import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 *
 * 重新设置listview .grideview 的高度等
 * Created by b508a on 2015/12/28.
 */
public class ListViewUtils {


    //根据item设置listview高度
    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.height += 5;//if without this statement,the listview will be a little short
        listView.setLayoutParams(params);
    }
    //根据item设置listview高度
    public static void setListViewHeight(ListView listView, int n) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < n; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (n - 1));
        //          params.height += 5;//if without this statement,the listview will be a little short
        listView.setLayoutParams(params);
    }
    //根据item设置listview高度
    @SuppressLint("NewApi") public static void setGridViewHeight(Context context, GridView listView, int n) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < (listAdapter.getCount()+n-1)/n; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int spacing= ScreenUtils.dipToPx(context, 10);
        params.height = totalHeight+spacing*((listAdapter.getCount()-1)/n)+listView.getPaddingBottom()+listView.getPaddingTop();
        params.height += 5;//if without this statement,the listview will be a little short
        listView.setLayoutParams(params);
    }
    //根据item设置listview高度
    @SuppressLint("NewApi") public static void setGridViewWidth(Context context, GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredWidth();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.width = totalHeight+listView.getPaddingLeft()+listView.getPaddingRight();
        params.width += 5;//if without this statement,the listview will be a little short
        listView.setLayoutParams(params);
    }
    //根据item设置listview高度
    @SuppressLint("NewApi") public static int getGridViewHeight(GridView listView, int n) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < (listAdapter.getCount()+1)/n; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight+5;
    }
    //根据item设置listview高度
    @SuppressLint("NewApi") public static int getGridViewWidth(GridView listView, int n) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i <listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredWidth();
        }
        return totalHeight+5;
    }
    //根据item设置listview高度
    @SuppressLint("NewApi") public static void setViewHeight(ViewPager listView, int height) {


        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height;
        params.height += 5;//if without this statement,the listview will be a little short
        listView.setLayoutParams(params);
    }



    /**
     * 在Scrollview 下的ListView全部展开的方法
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null)
            return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 本来是getCount-1 ,因为设置了paddingtop 和paddingButton
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() + 1));
        listView.setLayoutParams(params);
    }
}

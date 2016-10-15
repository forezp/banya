package com.forezp.banya.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public abstract class EasyRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_BODY = 1;
    private static final int TYPE_FOOT = 2;

    private View mHeaderView;
    private View mFooterView;

    private List<T> mDatas = new ArrayList<>();

    private OnItemClickListener mItemClickListener;

    private OnItemLongClickListener mItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        //  notifyItemInserted(getFooterPosition());
        notifyDataSetChanged();
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }


    public EasyRecyclerViewAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public EasyRecyclerViewAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        if (mFooterView == null && mHeaderView == null) {
            return TYPE_BODY;
        }
        if (mHeaderView != null && position == 0) {
            return TYPE_HEAD;
        }
        if (mFooterView != null && mHeaderView == null && position == mDatas.size()) {
            return TYPE_FOOT;
        }
        if (mFooterView != null && mHeaderView != null && position == mDatas.size() + 1) {
            return TYPE_FOOT;
        }
        return TYPE_BODY;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new EasyViewHolder(mHeaderView);
        }
        if (viewType == TYPE_FOOT) {
            return new EasyViewHolder(mFooterView);
        }
        return onCreate(parent, viewType);//获取资源文件
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEAD || getItemViewType(position) == TYPE_FOOT) {
            return;
        }

        final int dataPosition = getDataPosition(holder);
        final T data = mDatas.get(dataPosition);
        onBind(holder, dataPosition, data);

        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.OnItemClick(v, dataPosition, data);
                }
            });
        }

        if (mItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemLongClickListener.OnItemLongClick(v, dataPosition, data);
                    return true;
                }
            });
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutParams = recyclerView.getLayoutManager();
        if (layoutParams instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutParams).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (type == TYPE_HEAD || type == TYPE_FOOT)
                        return ((GridLayoutManager) layoutParams).getSpanCount();
                    return 1;
                }
            });
        }

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            if (mHeaderView != null && holder.getLayoutPosition() == 0) {
                params.setFullSpan(true);
            } else if (mFooterView != null && holder.getLayoutPosition() == getFooterPosition()) {
                params.setFullSpan(true);
            } else {
                params.setFullSpan(false);
            }

        }


    }

    public int getDataPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    private int getFooterPosition() {
        if (mFooterView == null) {
            return -1;
        }
        if (mHeaderView == null) {
            return mDatas.size();
        }
        if (mHeaderView != null) {
            return mDatas.size() + 1;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (mFooterView != null && mHeaderView != null) {
            return mDatas.size() + 2;
        } else if (mFooterView != null || mHeaderView != null) {
            return mDatas.size() + 1;
        }
        return mDatas.size();
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, T data);

    public class EasyViewHolder extends RecyclerView.ViewHolder {

        public EasyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<T> {
        public void OnItemClick(View view, int position, T data);
    }

    interface OnItemLongClickListener<T> {
        public void OnItemLongClick(View view, int position, T data);
    }

    public List<T> getmDatas() {
        return mDatas;
    }
}
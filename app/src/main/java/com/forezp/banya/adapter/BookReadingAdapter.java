package com.forezp.banya.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.forezp.banya.R;
import com.forezp.banya.bean.book.Books;
import com.forezp.banya.utils.DisplayImgUtis;
import com.forezp.banya.viewimpl.book.BookDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;

/**
 * Created by forezp on 16/9/29.
 */
public class BookReadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOTER = -2;
    private List<Books> list;

    public BookReadingAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();

    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = View.inflate(parent.getContext(), R.layout.activity_view_footer, null);
            return new FooterViewHolder(view);
        } else {
            View rootView = View.inflate(parent.getContext(), R.layout.item_book_reading, null);
            return new BookReadeingViewHolder(rootView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        } else if (holder instanceof BookReadeingViewHolder) {
            BookReadeingViewHolder bookReadeingViewHolder = (BookReadeingViewHolder) holder;
            bookReadeingViewHolder.bindItem(list.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    /**
     * footer view
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_load_prompt)
        TextView tv_load_prompt;
        @BindView(R.id.progress)
        ProgressBar progress;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dipToPx(context, 40));
            itemView.setLayoutParams(params);
        }

        private void bindItem() {
            switch (status) {
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    tv_load_prompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    System.out.println("LOAD_NONE----");
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }
    }


    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }

    class BookReadeingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iV_film)
        ImageView iVFilm;
        @BindView(R.id.tv_film_name)
        TextView tvFilmName;
        @BindView(R.id.tv_film_grade)
        TextView tvFilmGrade;

        @BindView(R.id.ll_book)
        LinearLayout llBook;
        BookReadeingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void bindItem(Books book, int position) {
            ViewGroup.LayoutParams params=iVFilm.getLayoutParams();
            int width= ScreenUtils.getScreenWidthDp(context);
            int ivWidth=(width-ScreenUtils.dipToPx(context,80))/3;
            params.width=ivWidth;
            double height=(420.0/300.0)*ivWidth;
            params.height=(int)height;
            iVFilm.setLayoutParams(params);
            if(!TextUtils.isEmpty(book.getImages().getLarge())) {
                DisplayImgUtis.getInstance().display(context, book.getImages().getLarge(), iVFilm);
            }
            if(!TextUtils.isEmpty(book.getRating().getAverage())) {
                tvFilmGrade.setText("评分:" + book.getRating().getAverage());
            }else{
                tvFilmGrade.setText("暂无评分" );
            }
            if(!TextUtils.isEmpty(book.getTitle())) {
                tvFilmName.setText(book.getTitle());
            }
            llBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, BookDetailActivity.class);
                    intent.putExtra("id",book.getId());

                    context.startActivity(intent);
                }
            });


        }
    }

    public List<Books> getList() {
        return list;
    }

    public void setList(List<Books> list) {
        this.list = list;
    }
}

package com.forezp.banya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forezp.banya.R;
import com.forezp.banya.base.EasyRecyclerViewAdapter;
import com.forezp.banya.bean.filmusbox.Subjects;
import com.forezp.banya.utils.DisplayImgUtis;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;

/**
 * Created by forezp on 16/9/25.
 */
public class FilmUsBoxAdapter extends EasyRecyclerViewAdapter<Subjects> {

    Context context;
    public FilmUsBoxAdapter(Context context){
        this.context=context;

    }
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_live, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Subjects data) {
        ViewHolder holder= (ViewHolder) viewHolder;

        holder.tvFilmName.setText(data.getSubject().getTitle());
        ViewGroup.LayoutParams params=holder.iVFilm.getLayoutParams();
        int width= ScreenUtils.getScreenWidthDp(context);
        int ivWidth=(width-ScreenUtils.dipToPx(context,80))/3;
        params.width=ivWidth;
        double height=(420.0/300.0)*ivWidth;
        params.height=(int)height;
        holder.iVFilm.setLayoutParams(params);
        if(data.getSubject().getImages()!=null&&!TextUtils.isEmpty(data.getSubject().getImages().getLarge())) {
            DisplayImgUtis.getInstance().display(context, data.getSubject().getImages().getLarge(), holder.iVFilm);
        }
        if(!TextUtils.isEmpty(""+data.getSubject().getRating().getAverage())) {
            holder.tvFilmGrade.setText("评分:"+String.valueOf(data.getSubject().getRating().getAverage()));
        }else{
            holder.tvFilmGrade.setText("暂无评分");
        }
    }

    class ViewHolder extends EasyViewHolder{
        @BindView(R.id.iV_film)
        ImageView iVFilm;
        @BindView(R.id.tv_film_name)
        TextView tvFilmName;
        @BindView(R.id.tv_film_grade)
        TextView tvFilmGrade;

        public   ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

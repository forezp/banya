package com.forezp.banya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forezp.banya.R;
import com.forezp.banya.base.EasyRecyclerViewAdapter;
import com.forezp.banya.bean.filmdetail.FilmPeople;
import com.forezp.banya.utils.DisplayImgUtis;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by forezp on 16/9/25.
 */
public class CastAdapter extends EasyRecyclerViewAdapter<FilmPeople> {

    Context context;

    public CastAdapter(Context context) {
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_people, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, FilmPeople data) {
        ViewHolder holder= (ViewHolder) viewHolder;
        DisplayImgUtis.getInstance().display(context,data.getAvatars().getLarge(),holder.ivAvatar);
        holder.tvNameChinease.setText(data.getName());
        if(data.getType()==1){
            holder.tvNameEnglish.setText("［导演］");
        }else{
            holder.tvNameEnglish.setText("［演员］");
        }
    }

    class ViewHolder extends EasyViewHolder{
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name_chinease)
        TextView tvNameChinease;
        @BindView(R.id.tv_name_english)
        TextView tvNameEnglish;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

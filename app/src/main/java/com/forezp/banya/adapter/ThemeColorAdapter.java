package com.forezp.banya.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.forezp.banya.R;
import com.forezp.banya.base.EasyRecyclerViewAdapter;
import com.forezp.banya.bean.home.ThemeColor;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created
 */
public class ThemeColorAdapter extends EasyRecyclerViewAdapter<ThemeColor> {


    private int position;
    public ThemeColorAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_color, parent, false);
        return new ThemeColorViewHolder(view);
    }

    @Override
    public void onBind(final RecyclerView.ViewHolder viewHolder, final int RealPosition, ThemeColor data) {
        ((ThemeColorViewHolder) viewHolder).them_color.setImageResource(data.getColor());
        if (data.isChosen()) {
            ((ThemeColorViewHolder) viewHolder).chosen.setVisibility(View.VISIBLE);
            position=RealPosition;
        } else {
            ((ThemeColorViewHolder) viewHolder).chosen.setVisibility(View.GONE);
        }
    }


    class ThemeColorViewHolder extends EasyViewHolder {
        @BindView(R.id.them_color)
        CircleImageView them_color;
        @BindView(R.id.choose)
        ImageView chosen;

        public ThemeColorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public int getPosition() {
        return position;
    }
}

package com.forezp.banya.viewimpl.other;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.forezp.banya.R;
import com.forezp.banya.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by forezp on 16/10/15.
 */
public class RecommedActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_topic)
    ImageView ivTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);
        applyKitKatTranslucency(getResources().getColor(R.color.black));
        initView();
    }

    private void initView() {

        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });
        toolbar.setTitle("－。－推荐");


    }
    @Override
    public String setActName() {
        return null;
    }
}

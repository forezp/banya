package com.forezp.banya.viewimpl.other;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.forezp.banya.R;
import com.forezp.banya.base.BaseActivity;
import com.forezp.banya.utils.ThemeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by forezp on 16/10/2.
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        applyKitKatTranslucency();
        toolbar.setTitle("关于");
        toolbar.setBackgroundColor(ThemeUtils.getToolBarColor());
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backThActivity();
            }
        });
    }

    @Override
    public String setActName() {
        return null;
    }
}

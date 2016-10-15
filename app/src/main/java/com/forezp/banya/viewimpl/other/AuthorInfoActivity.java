package com.forezp.banya.viewimpl.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.forezp.banya.R;
import com.forezp.banya.base.BaseActivity;
import com.forezp.banya.viewimpl.webview.WebviewActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import forezp.com.douyalibrary.utils.ViewUtils;

/**
 * 我的代码陋习  不喜欢将字符串写咋 i资源文件中
 * 后续会改掉
 * Created by forezp on 16/10/2.
 */
public class AuthorInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @BindView(R.id.tv_github)
    TextView tvGithub;
    @BindView(R.id.tv_blog)
    TextView tvBlog;
    @BindString(R.string.blog)
    String blog;
    @BindString(R.string.github)
    String github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_info);
        ButterKnife.bind(this);
        applyKitKatTranslucency(getResources().getColor(R.color.orange_50));

        initToolbar();
    }

    private void initToolbar() {
        collapsingToolbarLayout.setTitle("风继续吹");
        ViewUtils.setTextViewUnderline(tvGithub,github);
        ViewUtils.setTextViewUnderline(tvBlog,blog);
    }

    @Override
    public String setActName() {
        return null;
    }

    @OnClick({R.id.tv_github, R.id.tv_blog})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_github:
                intent=new Intent(this, WebviewActivity.class);
                intent.putExtra(WebviewActivity.EXTRA_URL,"https://github.com/forezp");
                startThActivityByIntent(intent);
                break;
            case R.id.tv_blog:
                intent=new Intent(this, WebviewActivity.class);
                intent.putExtra(WebviewActivity.EXTRA_URL,"http://blog.csdn.net/forezp");
                startThActivityByIntent(intent);
                break;
        }
    }
}

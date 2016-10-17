package com.forezp.banya.viewimpl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.forezp.banya.R;
import com.forezp.banya.adapter.ThemeColorAdapter;
import com.forezp.banya.base.ActivityCollector;
import com.forezp.banya.base.BaseActivity;
import com.forezp.banya.base.EasyRecyclerViewAdapter;
import com.forezp.banya.bean.home.ThemeColor;
import com.forezp.banya.bean.top250.Root;
import com.forezp.banya.utils.ThemeUtils;
import com.forezp.banya.viewimpl.book.BookFragment;
import com.forezp.banya.viewimpl.film.FilmFragment;
import com.forezp.banya.viewimpl.music.MusicFragment;
import com.forezp.banya.viewimpl.other.AboutActivity;
import com.forezp.banya.viewimpl.other.AuthorInfoActivity;
import com.forezp.banya.viewimpl.other.RecommedActivity;
import com.forezp.banya.viewinterface.film.IgetTop250View;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目地址L：https://github.com/forezp/banya
 * author : 124746406@qq.com
 * 注意：douban API 有次数限制
 *
 */
public class MainActivity extends BaseActivity implements IgetTop250View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    @BindView(R.id.id_navigationview)
    NavigationView idNavigationview;
    @BindView(R.id.drawerlayout_home)
    DrawerLayout drawerlayoutHome;
    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;
    private FilmFragment filmFragment;
    private BookFragment bookFragment;
    private MusicFragment musicFragment;
    private List<Fragment> listFragment;
    private int currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // applyKitKatTranslucency();
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this,drawerlayoutHome, ThemeUtils.getThemeColor());
       // StatusBarUtil.setColor(MainActivity.this, ThemeUtils.getThemeColor());
        initView();
        initViewpagerAndFragment();
        initListener();
        initChangeTheme();
    }
    private void initListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_home:
                        currentFragment = 0;
                        break;
                    case R.id.rb_dynamic:

                        currentFragment = 1;

                        break;
                    case R.id.rb_message:
                        currentFragment=2;
                        break;

                }

                viewpager.setCurrentItem(currentFragment, false);

            }
        });

        viewpager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return listFragment.get(arg0);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                super.destroyItem(container, position, object);
            }

        });
    }





    private void initViewpagerAndFragment(){
        filmFragment=FilmFragment.newInstance();
        bookFragment=BookFragment.newInstance();
        musicFragment=MusicFragment.newInstance();
        listFragment=new ArrayList<>();
        listFragment.add(filmFragment);
        listFragment.add(bookFragment);
        listFragment.add(musicFragment);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setOnPageChangeListener(onPageChangeListener);

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioGroup.check(R.id.rb_home);
                    break;
                case 1:
                    radioGroup.check(R.id.rb_dynamic);
                    break;
                case 2:
                    radioGroup.check(R.id.rb_message);
                    break;
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private  void initView(){
        // setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(ThemeUtils.getToolBarColor());

        // 设置Drawerlayout开关指示器，即Toolbar最左边的那个icon
        ActionBarDrawerToggle mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerlayoutHome, toolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawerlayoutHome.setDrawerListener(mActionBarDrawerToggle);
        //给NavigationView填充顶部区域，也可在xml中使用app:headerLayout="@layout/header_nav"来设置
        idNavigationview.inflateHeaderView(R.layout.header_nav);
        View headerView = idNavigationview.getHeaderView(0);
        //TextView tvCentificate= (TextView) headerView.findViewById(R.id.tv_centificate);
      //  tvCentificate.setTextColor(ThemeUtils.getThemeColor());
        CircleImageView sdvHeader = (CircleImageView) headerView.findViewById(R.id.sdv_avatar);
        //String avatarUrl="http://b.hiphotos.baidu.com/image/pic/item/0823dd54564e925838c205c89982d158ccbf4e26.jpg";
        //DisplayImgUtis.getInstance().display(MainActivity.this,R.drawable.ic_avtar,sdvHeader);
        sdvHeader.setImageResource(R.drawable.ic_avtar);
        idNavigationview.inflateMenu(R.menu.menu_nav);
        idNavigationview.setItemIconTintList(ThemeUtils.getNaviItemIconTinkList());
        // 自己写的方法，设置NavigationView中menu的item被选中后要执行的操作
        onNavgationViewMenuItemSelected(idNavigationview);
    }


    /**
     * 设置NavigationView中menu的item被选中后要执行的操作
     *
     * @param mNav
     */
    private void onNavgationViewMenuItemSelected(NavigationView mNav) {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_home:
                        startThActivity(AuthorInfoActivity.class);
                        break;
                    case R.id.nav_menu_categories:
                        startThActivity(AboutActivity.class);

                        break;

                    case R.id.nav_menu_recommend:
                        startThActivity(RecommedActivity.class);
                        break;
                    case R.id.nav_menu_feedback:

                        break;
                    case R.id.nav_menu_setting:

                       // startThActivityByIntent(new Intent(MainActivity.this, SettingActivity.class));
                        break;

                    case R.id.nav_menu_theme:

                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_theme_color, null, false);
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theme_recycler_view);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        recyclerView.setAdapter(themeColorAdapter);
                        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("主题选择")
                                .setView(view)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ThemeUtils.setThemeColor( getResources().getColor(themeColorList.get(themeColorAdapter.getPosition()).getColor()));// 不要变换位置
                                        ThemeUtils.setThemePosition(themeColorAdapter.getPosition());
                                        // finish();
                                        new Handler().postDelayed(new Runnable() {
                                            public void run() {
                                                ActivityCollector.getInstance().refreshAllActivity();
                                                // closeHandler.sendEmptyMessageDelayed(MSG_CLOSE_ACTIVITY, 300);
                                            }
                                        }, 100);
                                    }
                                })
                                .show();

                        break;
                }

                // Menu item点击后选中，并关闭Drawerlayout
                menuItem.setChecked(true);
                //drawerlayoutHome.closeDrawers();
                // Toast.makeText(MainActivity.this,msgString,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private ArrayList<ThemeColor> themeColorList = new ArrayList<>();
    private ThemeColorAdapter themeColorAdapter = new ThemeColorAdapter();

    private void initChangeTheme(){
        themeColorAdapter = new ThemeColorAdapter();
        themeColorList.add(new ThemeColor(R.color.theme_red_base));
        themeColorList.add(new ThemeColor(R.color.theme_blue));
        themeColorList.add(new ThemeColor(R.color.theme_blue_light));
        themeColorList.add(new ThemeColor(R.color.theme_balck));
        themeColorList.add(new ThemeColor(R.color.theme_teal));
        themeColorList.add(new ThemeColor(R.color.theme_brown));
        themeColorList.add(new ThemeColor(R.color.theme_green));
        themeColorList.add(new ThemeColor(R.color.theme_red));
        themeColorAdapter.setDatas(themeColorList);
        themeColorAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                for (ThemeColor themeColor : themeColorList) {
                    themeColor.setChosen(false);
                }
                themeColorList.get(position).setChosen(true);
                themeColorAdapter.notifyDataSetChanged();

            }
        });
    }





    @Override
    public String setActName() {
        return null;
    }

    @Override
    public void getTop250Success(Root root, boolean isLoadMore) {

    }


    @Override
    public void getDataFail() {

    }
}

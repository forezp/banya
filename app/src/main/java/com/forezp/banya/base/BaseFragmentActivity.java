package com.forezp.banya.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by b508a on 2016/1/25.
 */
public  abstract  class BaseFragmentActivity extends AppCompatActivity {

    protected String actName;//用于友盟页面统计
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.getInstance().addActivity(this);
        actName=setActName();



    }
    public abstract String setActName();
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }

    public void onResume() {
        super.onResume();
       // MobclickAgent.onPageStart(actName);
       // MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
      //  MobclickAgent.onPageEnd(actName);
       // MobclickAgent.onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {


        ActivityCollector.getInstance().removeActivity(this);
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }














}

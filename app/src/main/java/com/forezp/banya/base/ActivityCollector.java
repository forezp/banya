package com.forezp.banya.base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 *
 */
public class ActivityCollector {


    private static Stack<Activity> activityStack;
    private static ActivityCollector instance;
    private ActivityCollector() {

    }

    public  void refreshAllActivity() {
        for (Activity activity : activityStack) {
            activity.recreate();
        }
    }

    /**
     * 单一实例
     */
    public static ActivityCollector getInstance() {
        if (instance == null) {
            instance = new ActivityCollector();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }


    public  void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
                break;
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     *
     */
    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }


    /**
     * 以前的Activity..
     */

//    public static ActivityCollector instance = new ActivityCollector();
//    private List<Activity> mLists = new ArrayList<Activity>();
//
//    private ActivityCollector() {
//    }
//
//    public synchronized static ActivityCollector getInstance() {
//
//        return instance;
//    }
//
//    /**
//     * 往集合中添加一个Activity
//     *
//     * @param pActivity
//     */
//    public void addActivity(Activity pActivity) {
//        if (pActivity != null) {
//            mLists.add(pActivity);
//        }
//    }
//
//    /**
//     * 从集合中删除一个Activity
//     *
//     * @param pActivity 需要删除的Activity
//     */
//    public void removeActivity(Activity pActivity) {
//        if (pActivity != null) {
//            if (mLists.contains(pActivity)) {
//                mLists.remove(pActivity);
//                pActivity.finish();
//                pActivity = null;
//            }
//        }
//    }
//
//    //从栈中进行删除集合顶得Activity
//    public void popActivity() {
//        Activity activity = mLists.get(mLists.size() - 1);
//        removeActivity(activity);
//    }
//
//    public int getNum() {
//        return mLists.size();
//    }
//
//    /**
//     * 完全删除集合中
//     */
//    public void finishActivity() {
//        if (mLists != null && mLists.size() >= 0) {
//            for (Activity pActivity : mLists) {
//                pActivity.finish();
//                pActivity = null;
//            }
//        }
//    }
//
//
//    /**
//     * 退出应用程序
//     */
//    public void AppExit(Context context) {
//        try {
//            finishActivity();
//            // 杀死该应用进程
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
//        } catch (Exception e) {
//        }
//    }
}
package com.forezp.banya.base;

import android.content.Context;

import com.forezp.banya.api.ApiFactory;
import com.forezp.banya.api.DoubanApi;


/**
 * 公共Presenter,所有Presenter继承自此类
 * 因为Presenter层一般用于校验数据正确性,故该类用于封装常用的数据校验方法
 * 将网络请求移动到本层处理
 */
public abstract class BasePresenter {
    // private Myhttp myHttp;
    public Context mContext;
    public static final DoubanApi doubanApi = ApiFactory.getDoubanApiSingleton();


    public BasePresenter(Context context) {
        mContext = context;
        // myHttp = new Myhttp();
    }



//    public void post(String url, MyhttpUtils.NetParams netParams, StringCallback callback) {
//        LG.e("url -------->>>>>>>>>","<<<<<<<<<<------"+url);
//       // OkHttpUtils.post().params(MyhttpUtils.getSignParamsSignMap(netParams)).url(url).build().execute(callback);
//        // OkHttpUtils.post().params(MyhttpUtils.getSignParamsSignMap(netParams)).url(url).build().execute(callback);
//        myHttp.post(url,netParams,callback);
//    }
//
//
//    public void get(String url, MyhttpUtils.NetParams netParams, StringCallback callback) {
//        LG.e("url -------->>>>>>>>>","<<<<<<<<<<------"+url);
//        // OkHttpUtils.post().params(MyhttpUtils.getSignParamsSignMap(netParams)).url(url).build().execute(callback);
//      //  OkHttpUtils.get().params(MyhttpUtils.getSignParamsSignMap(netParams)).url(url).build().execute(callback);
//        myHttp.get(url,netParams,callback);
//    }
//
//
//    /**
//     * 创建一个请求参数
//     *
//     * @return
//     */
//    public MyhttpUtils.NetParams createParams() {
//        return new MyhttpUtils().createNetParams();
//    }
//
//    /**
//     * 将请求实体类转换为请求参数对象
//     *
//     * @param t 请求实体类
//     * @return
//     */
//    public <T> MyhttpUtils.NetParams convertParams(T t) {
//        MyhttpUtils.NetParams params = createParams();
//
//        Class clazz = t.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        if (fields == null || fields.length == 0) {
//            return params;
//        }
//
//        try {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                params.put(field.getName(), String.valueOf(field.get(t)));
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
//
//        return params;
//    }
//
//    /**
//     * 校验指定的字符串是否为空,如果为空则弹出指定内容的Toast
//     *
//     * @param verifData
//     * @param view
//     * @param showMessage
//     * @return
//     */
//    public boolean isEmpty(String verifData, IBaseView view, String showMessage) {
//        if (TextUtils.isEmpty(verifData)) {
//            view.showTheToast(showMessage);
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * 校验请求是否成功
//     * @param result
//     * @return
//     */
//    public boolean isSuccess(String result) {
//        return GsonUtil.getResultCode(result).equals("1") ;
//    }
//
//    /**
//     *
//     * @param result
//     * @return
//     */
//    public String getData(String result){
//        return  GsonUtil.getResultData(result);
//    }
//
//    /**
//     * 获取失败的信息
//     * @param t
//     * @return
//     */
//   public String getFailMsg(String t){
//     return  GsonUtil.getResultMsg(t);
//   }
}

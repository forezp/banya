package com.forezp.banya.api;

/**
 *
 * Singleton Factory with retrofit
 */
public class ApiFactory {


    protected static final Object monitor = new Object();
    static DoubanApi doubanApiSingleton = null;


    //return Singleton
    public static DoubanApi getDoubanApiSingleton() {
        synchronized (monitor) {
            if (doubanApiSingleton == null) {
                doubanApiSingleton = new ApiRetrofit().getDoubanApiService();
            }
            return doubanApiSingleton;
        }
    }




}

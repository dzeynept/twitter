package com.konusarakogren.twitterclone.application;


import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    //    private static Context appContext;
    private final String TAG = "App";
    public static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("twitter")
                .clientKey("twitterKey")
                .server("http://192.168.2.246:1337/parse")
                .build()
        );
    }
}
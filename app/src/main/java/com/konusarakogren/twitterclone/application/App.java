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
                .applicationId("YOUR_APP_ID")
                .clientKey("YOUR_CLIENT_KEY")
                .server("http://localhost:1337/parse/")
                .build()
        );
    }
}
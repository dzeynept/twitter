package com.konusarakogren.twitterclone.activity.login.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private final String TAG = "BaseActivity";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void initViews() {

    }
}
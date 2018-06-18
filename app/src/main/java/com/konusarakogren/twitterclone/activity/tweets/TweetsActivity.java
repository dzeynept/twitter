package com.konusarakogren.twitterclone.activity.tweets;

import android.content.Context;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;

public class TweetsActivity extends BaseActivity {

    private final String TAG = "TweetsActivity";

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
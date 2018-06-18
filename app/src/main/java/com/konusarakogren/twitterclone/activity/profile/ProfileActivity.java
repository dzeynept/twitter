package com.konusarakogren.twitterclone.activity.profile;

import android.content.Context;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.parse.ParseUser;

public class ProfileActivity extends BaseActivity {

    private final String TAG = "ProfileActivity";

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
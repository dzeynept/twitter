package com.konusarakogren.twitterclone.activity.signup;

import android.content.Context;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;

public class SignupActivity extends BaseActivity {

    private final String TAG = "SignupActivity";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_signup;
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void initViews() {

    }
}
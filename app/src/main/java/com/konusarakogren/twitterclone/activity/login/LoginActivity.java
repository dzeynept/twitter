package com.konusarakogren.twitterclone.activity.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.konusarakogren.twitterclone.Util.OneShotClickListener;
import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.signup.SignupActivity;
import com.konusarakogren.twitterclone.activity.tweets.TweetsActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    private final String TAG = "BaseActivity";

    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.button_register)
    Button buttonRegister;

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
        buttonLogin.setOnClickListener(loginClickListener);
        buttonRegister.setOnClickListener(registerClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null && currentUser.isAuthenticated()) {
            startActivity(new Intent(LoginActivity.this, TweetsActivity.class));
            finish();
        }
    }

    private OneShotClickListener loginClickListener = new OneShotClickListener(200) {
        @Override
        public void onOneShotClick(View v) {
            ParseUser.logInInBackground(editEmail.getText().toString(), editPassword.getText().toString(), new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        startActivity(new Intent(LoginActivity.this, TweetsActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Your E-mail or Password is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, String.valueOf(e));
                    }
                }
            });
        }
    };

    private OneShotClickListener registerClickListener = new OneShotClickListener(200) {
        @Override
        public void onOneShotClick(View v) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        }
    };
}
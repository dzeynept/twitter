package com.konusarakogren.twitterclone.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.signup.SignupActivity;
import com.konusarakogren.twitterclone.activity.tweets.TweetsActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    private final String TAG = "BaseActivity";

    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;

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

    }

    @Override
    protected void onResume() {
        super.onResume();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null)
            startActivity(new Intent(LoginActivity.this, TweetsActivity.class));
    }

    private View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseUser.logInInBackground("Jerry", "showmethemoney", new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        startActivity(new Intent(LoginActivity.this, TweetsActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Your E-mail or Password is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, String.valueOf(e));
                    }
                }
            });
//            ParseUser user = new ParseUser();
//            user.setUsername(editName.getText().toString());
//            user.setPassword(editPassword.getText().toString());
//            user.setEmail(editEmail.getText().toString());
//
//            user.signUpInBackground(new SignUpCallback() {
//                public void done(ParseException e) {
//                    if (e == null) {
//                        startActivity(new Intent(LoginActivity.this, TweetsActivity.class));
//                        finish();
//                        // Hooray! Let them use the app now.
//                    } else {
//                        Log.e(TAG, String.valueOf(e));
//                        // Sign up didn't succeed. Look at the ParseException
//                        // to figure out what went wrong
//                    }
//                }
//            });
        }
    };
}
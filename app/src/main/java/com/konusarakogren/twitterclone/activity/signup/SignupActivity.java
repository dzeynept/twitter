package com.konusarakogren.twitterclone.activity.signup;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.tweets.TweetsActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.BindView;

public class SignupActivity extends BaseActivity {

    private final String TAG = "SignupActivity";

    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.button_signup)
    Button buttonSignUp;

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
        buttonSignUp.setOnClickListener(signUpClickListener);
    }

    private View.OnClickListener signUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseUser user = new ParseUser();
            user.setUsername(editName.getText().toString());
            user.setPassword(editPassword.getText().toString());
            user.setEmail(editEmail.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        startActivity(new Intent(SignupActivity.this, TweetsActivity.class));
                        finish();
                        // Hooray! Let them use the app now.
                    } else {
                        Log.e(TAG, String.valueOf(e));
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });
        }
    };
}
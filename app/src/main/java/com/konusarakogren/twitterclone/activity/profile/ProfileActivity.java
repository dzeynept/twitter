package com.konusarakogren.twitterclone.activity.profile;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ProfileActivity extends BaseActivity {

    private final String TAG = "ProfileActivity";
    TextView userName;
    EditText password;
    Button button_save;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void initViews() {
        userName = findViewById(R.id.profile_text_username);
        password = findViewById(R.id.profile_edittext_pass);
        button_save = findViewById(R.id.profile_button_save);

        final ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            userName.setText(currentUser.getUsername());
        } else {

        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().length()<5){
                    Toast.makeText(ProfileActivity.this, "En az 5 karakter olmalı", Toast.LENGTH_SHORT).show();
                }else {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    currentUser.setPassword(password.getText().toString());
                    currentUser.saveInBackground();
                }

            }
        });
    }
}
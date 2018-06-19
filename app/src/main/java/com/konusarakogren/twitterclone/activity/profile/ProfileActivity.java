package com.konusarakogren.twitterclone.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.login.LoginActivity;
import com.konusarakogren.twitterclone.activity.tweets.TweetsActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

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
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, TweetsActivity.class));
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile_back_button) {
            startActivity(new Intent(ProfileActivity.this, TweetsActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

                        currentUser.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null){
                                    Toast.makeText(ProfileActivity.this, "Parola değiştirildi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ProfileActivity.this,TweetsActivity.class));
                                }else{
                                    Toast.makeText(ProfileActivity.this, "Parola değiştirildi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }




            }
        });
    }
}
package com.konusarakogren.twitterclone.activity.tweets;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.login.LoginActivity;
import com.konusarakogren.twitterclone.activity.profile.ProfileActivity;
import com.konusarakogren.twitterclone.adapter.TweetsRecyclerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class TweetsActivity extends BaseActivity implements View.OnClickListener {
    RecyclerView tweets_rcyclerview;
    TweetsRecyclerAdapter adapter;
    FloatingActionButton fab;
    EditText add_tweet_edt;
    Button add_tweet_btn;
    ParseUser user;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tweets;
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void initViews() {

        fab = findViewById(R.id.tweets_fa_btn);
        fab.setOnClickListener(this);
        tweets_rcyclerview = findViewById(R.id.tweets_recycler_view);
        tweets_rcyclerview.setLayoutManager(new LinearLayoutManager(this));
        tweets_rcyclerview.setLayoutManager(new LinearLayoutManager(this));

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweets");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> tweetsList, ParseException e) {
                if (e == null) {
                    adapter = new TweetsRecyclerAdapter(tweetsList);
                    tweets_rcyclerview.setAdapter(adapter);
                    Log.d("tweets", "Retrieved " + tweetsList.size() + " tweets");
                } else {
                    Log.d("tweets", "Error: " + e.getMessage());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            ParseUser.logOut();
            startActivity(new Intent(TweetsActivity.this, LoginActivity.class));
            finish();
            return true;
        }else if (id == R.id.action_profile){

                startActivity(new Intent(TweetsActivity.this, ProfileActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tweets_fa_btn){
            showTweetPopUp();
        }
    }

    private void showTweetPopUp(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.pop_up_tweet);
        dialog.setCancelable(false);

        final Button btnSend = dialog.findViewById(R.id.btn_send);
        final EditText tweetText = dialog.findViewById(R.id.textview_info_content);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = tweetText.getText().toString();
                if (string.equalsIgnoreCase("")) {
                    Toast.makeText(TweetsActivity.this, "You cant send emty tweet!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseObject newTweet = new ParseObject("Tweets");
                newTweet.put("tweets", string);
                newTweet.put("userName", user.getUsername());
                newTweet.saveInBackground();
                newTweet.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                            Toast.makeText(TweetsActivity.this, "Tweet is posted!", Toast.LENGTH_SHORT).show();
                        else
                            Log.d(TAG, String.valueOf(e));
                    }
                });
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
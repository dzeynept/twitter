package com.konusarakogren.twitterclone.activity.tweets;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.konusarakogren.twitterclone.R;
import com.konusarakogren.twitterclone.activity.TweetsResponseModel;
import com.konusarakogren.twitterclone.activity.base.BaseActivity;
import com.konusarakogren.twitterclone.activity.login.LoginActivity;
import com.konusarakogren.twitterclone.adapter.TweetsRecyclerAdapter;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
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
       /* ParseObject newTweet = new ParseObject("Tweets");
        newTweet.put("new_tweet", "this is new tweet");
        newTweet.saveInBackground();*/
        user = ParseUser.getCurrentUser();


/*
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweets");
        query.getInBackground(user.getObjectId(), new GetCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (e == null) {
                    adapter = new TweetsRecyclerAdapter();
                    tweets_rcyclerview.setAdapter(adapter);

                } else {
                    // something went wrong
                }
            }
        });*/

        add_tweet_btn = findViewById(R.id.add_tweet_btn);
        add_tweet_edt = findViewById(R.id.tweets_edt);
        fab = findViewById(R.id.tweets_fa_btn);
        fab.setOnClickListener(this);
        add_tweet_btn.setOnClickListener(this);
        tweets_rcyclerview = findViewById(R.id.tweets_recycler_view);
        tweets_rcyclerview.setLayoutManager(new LinearLayoutManager(this));



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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tweets_fa_btn){
            add_tweet_btn.setVisibility(View.VISIBLE);
            add_tweet_edt.setVisibility(View.VISIBLE);
        }
        if (id == R.id.add_tweet_btn) {
            String string = add_tweet_edt.getText().toString();
            Toast.makeText(TweetsActivity.this, "btn", Toast.LENGTH_SHORT).show();

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
            add_tweet_btn.setVisibility(View.GONE);
            add_tweet_edt.setVisibility(View.GONE);
        }
    }
}
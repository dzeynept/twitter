package com.konusarakogren.twitterclone.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.konusarakogren.twitterclone.R;
import com.parse.ParseObject;

import java.util.List;

public class TweetsRecyclerAdapter extends RecyclerView.Adapter {
    List<ParseObject> tweetsList;

    public TweetsRecyclerAdapter(List<ParseObject> tweetsList) {
        this.tweetsList = tweetsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TweetViewHolder(layoutInflater.inflate(R.layout.tweets_recycler_item, parent, false));
    }

    private TweetViewHolder mHolder;
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        this.mHolder = (TweetViewHolder) holder;

        String a = tweetsList.get(position).getString("tweets");
        mHolder.tweet.setText(tweetsList.get(position).getString("tweets"));
    }

    @Override
    public int getItemCount() {
        if (tweetsList == null)
            return 0;
        else
            return tweetsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class TweetViewHolder extends RecyclerView.ViewHolder {
        TextView tweet;
        TextView userName;
        public TweetViewHolder(View itemView) {
            super(itemView);
            tweet = itemView.findViewById(R.id.tweets_txt);
            userName = itemView.findViewById(R.id.user_name_txt);
            }
    }
}

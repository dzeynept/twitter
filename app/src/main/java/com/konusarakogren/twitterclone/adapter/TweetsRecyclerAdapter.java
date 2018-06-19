package com.konusarakogren.twitterclone.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.konusarakogren.twitterclone.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetsRecyclerAdapter extends RecyclerView.Adapter<TweetsRecyclerAdapter.ViewHolder> {
    List<String> tweetsList;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.tweets_recycler_item, parent, false);
        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tweet;
        TextView userName;
        EditText tweet_edt;
        public ViewHolder(View itemView) {
            super(itemView);
            tweet = itemView.findViewById(R.id.tweets_txt);
            userName = itemView.findViewById(R.id.user_name_txt);
            tweet_edt = itemView.findViewById(R.id.tweets_edt);

            ParseUser user = ParseUser.getCurrentUser();
            if (user != null) {
                tweet.setText("tweet");
                userName.setText("username");

                //  tweetsList = user.get("tweets");
               // tweetsList.add(String.valueOf(user.get("tweets")));

            }
        }
    }
}

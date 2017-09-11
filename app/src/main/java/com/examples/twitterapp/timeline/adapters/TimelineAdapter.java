package com.examples.twitterapp.timeline.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.examples.twitterapp.R;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaniRosas on 10/9/17.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder>{

    public List<Post> items;
    public Context context;
    public OnItemClickListener clickListener;

    public TimelineAdapter(Context context, List<Post> items, OnItemClickListener clickListener) {
        this.items = items;
        this.context = context;
        this.clickListener = clickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post tweet = items.get(position);

        holder.setClickListener(tweet,clickListener);
        holder.time.setText(tweet.getTime());
        holder.post.setText(tweet.getPost());
        holder.setItems(tweet.getHashtags());


    }

    public void setItems(List<Post> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView time, post;
        private RecyclerView recyclerViewHashtags;
        private ArrayList<String> itemsHash;
        private HashtagListAdapter adapter;
        private ImageButton fav;

        private View view;


        public ViewHolder(Context context, View itemView) {
            super(itemView);

            this.view = itemView;

            time = (TextView) itemView.findViewById(R.id.textViewTime);
            post = (TextView) itemView.findViewById(R.id.textViewPost);
            fav  = (ImageButton) itemView.findViewById(R.id.imageButton);
            recyclerViewHashtags = (RecyclerView) itemView.findViewById(R.id.recyclerViewHashtags);

            itemsHash = new ArrayList<String>();
            adapter = new HashtagListAdapter(itemsHash);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            recyclerViewHashtags.setLayoutManager(layoutManager);
            recyclerViewHashtags.setAdapter(adapter);

        }

        public void setClickListener(final Post tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });

        }

        public void setItems(List<String> newItems){
            itemsHash.clear();
            itemsHash.addAll(newItems);
            adapter.notifyDataSetChanged();
        }
    }
}

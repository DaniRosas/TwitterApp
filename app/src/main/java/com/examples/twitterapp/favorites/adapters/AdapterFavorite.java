package com.examples.twitterapp.favorites.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.examples.twitterapp.R;
import com.examples.twitterapp.favorites.db.Favorite;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.ui.OnItemClickListener;

import java.util.List;

/**
 * Created by DaniRosas on 12/9/17.
 */

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {

    private List<Favorite> posts;
    private Context context;
    private OnItemClickListener clickListener;

    public AdapterFavorite(List<Favorite> posts, Context context, OnItemClickListener onItemClickListener) {
        this.posts = posts;
        this.context = context;
        this.clickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Favorite tweet = posts.get(position);

        holder.setClickListener(tweet,clickListener);
        holder.time.setText(tweet.getTime());
        holder.post.setText(tweet.getPost());

        //holder.fav.setImageResource(R.drawable.ic_favorite_white_24dp);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView time, post;
        private ImageButton fav;

        public ViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;

            time = (TextView) itemView.findViewById(R.id.textViewTime);
            post = (TextView) itemView.findViewById(R.id.textViewPost);
            fav  = (ImageButton) itemView.findViewById(R.id.imageButton);


        }

        public void setClickListener(final Favorite tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post post = new Post();
                    post.setTime(tweet.getTime());
                    post.setPost(tweet.getPost());
                    listener.onItemClick(post);
                }
            });

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Post post = new Post();
                    post.setTime(tweet.getTime());
                    post.setPost(tweet.getPost());
                    post.setId(tweet.getId());
                    listener.onFavClick(post, context);
                    fav.setImageResource(R.drawable.ic_favorite_border_white_24dp);

                }
            });

        }
    }
}

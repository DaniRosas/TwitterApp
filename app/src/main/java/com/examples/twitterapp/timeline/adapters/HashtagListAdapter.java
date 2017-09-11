package com.examples.twitterapp.timeline.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.twitterapp.R;

import java.util.List;

/**
 * Created by DaniRosas on 11/9/17.
 */

public class HashtagListAdapter  extends RecyclerView.Adapter<HashtagListAdapter.ViewHolder> {
    private List<String> items;

    public HashtagListAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hashtag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHashtag.setText(String.format("#%s",items.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHashtag;

        public ViewHolder(View view) {
            super(view);
            txtHashtag = (TextView) view.findViewById(R.id.textViewHashTag);
        }
    }
}
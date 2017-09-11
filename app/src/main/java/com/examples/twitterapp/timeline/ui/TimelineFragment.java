package com.examples.twitterapp.timeline.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.examples.twitterapp.R;
import com.examples.twitterapp.TwitterAppModule;
import com.examples.twitterapp.libs.di.LibsModule;
import com.examples.twitterapp.timeline.TimelinePresenter;
import com.examples.twitterapp.timeline.adapters.TimelineAdapter;
import com.examples.twitterapp.timeline.di.DaggerTimelineComponent;
import com.examples.twitterapp.timeline.di.TimelineModule;
import com.examples.twitterapp.timeline.entities.Post;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment implements TimelineView, OnItemClickListener {

    @Inject
    TimelineAdapter adapter;
    @Inject
    TimelinePresenter timelinesPresenter;

    FrameLayout container;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        setupInjection();
        setupRecyclerView();

        timelinesPresenter.getPostTweets();
        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        DaggerTimelineComponent
                .builder()
                .libsModule(new LibsModule(this))
                .twitterAppModule(new TwitterAppModule(getContext()))
                .timelineModule(new TimelineModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        timelinesPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        timelinesPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timelinesPresenter.onDestroy();
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void postUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void postDeleted(Post post) {

    }

    @Override
    public void onHashtagsError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void setPosts(List<Post> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Post tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }

    @Override
    public void onFavClick(Post tweet) {
        timelinesPresenter.toggleFavorite(tweet);
    }
}

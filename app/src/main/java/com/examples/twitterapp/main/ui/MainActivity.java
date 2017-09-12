package com.examples.twitterapp.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.examples.twitterapp.LoginActivity;
import com.examples.twitterapp.R;
import com.examples.twitterapp.favorites.ui.FavouritesFragment;
import com.examples.twitterapp.main.adapters.MainSectionsPagerAdapter;
import com.examples.twitterapp.timeline.ui.TimelineFragment;
import com.twitter.sdk.android.core.TwitterCore;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar   = (Toolbar)   findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[] {new TimelineFragment(),
                new FavouritesFragment()};
        String[] titles = new String[]{getString(R.string.main_header_timeline), getString(R.string.main_header_favourites)};
        final MainSectionsPagerAdapter mainSectionsPagerAdapter =
                                    new MainSectionsPagerAdapter(getSupportFragmentManager(),
                                                                fragments, titles);
        viewPager.setAdapter(mainSectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1) {
                    mainSectionsPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

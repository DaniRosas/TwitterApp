package com.examples.twitterapp.favorites.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.twitterapp.R;
import com.examples.twitterapp.favorites.adapters.AdapterFavorite;
import com.examples.twitterapp.favorites.db.Favorite;
import com.examples.twitterapp.favorites.db.FavoritesDbHelper;
import com.examples.twitterapp.timeline.entities.Post;
import com.examples.twitterapp.timeline.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment implements OnItemClickListener{

    private FavoritesDbHelper favoritesDbHelper;
    private List<Favorite> favorites;
    private RecyclerView recyclerView;
    private Context mContext;
    private AdapterFavorite adapterFavorite;
    private TextView text;


    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFavorites);
        text = (TextView) view.findViewById(R.id.textMessage);

        initializeVariables();
        //Instance of the helper
        favoritesDbHelper = new FavoritesDbHelper(getActivity());

        favorites = favoritesDbHelper.getAllFavorites();

        setVisibles();

        setupRecyclerView();


        // find adult users
        /*List<Post> posts = SQLite.select()
                .from(Post.class)
                .queryList();
*/        return view;
    }

    private void setVisibles() {
        if(favorites.size() == 0){
        text.setVisibility(View.VISIBLE);
    }else
        text.setVisibility(View.GONE);

    }

    private void setupRecyclerView() {
        adapterFavorite = new AdapterFavorite(favorites, mContext, this);
        recyclerView.setAdapter(adapterFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }

    private void initializeVariables() {
        favorites = new ArrayList<>();
        mContext = getActivity().getApplicationContext();
    }



    public void updateAdapter() {
        if(adapterFavorite != null){
            adapterFavorite.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Post tweet) {
        /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);*/
    }

    @Override
    public void onFavClick(Post tweet, Context context) {
        favoritesDbHelper.deleteFavorite(tweet.getId());
        for(int i = 0; i < favorites.size(); i++){
            Favorite f = favorites.get(i);
            if(f.getId().equals(tweet.getId())){
                favorites.remove(i);
                adapterFavorite.notifyItemRemoved(i);

            }
        }
    }
}

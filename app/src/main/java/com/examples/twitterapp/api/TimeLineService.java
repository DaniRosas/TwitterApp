package com.examples.twitterapp.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DaniRosas on 9/9/17.
 */

public interface TimeLineService {
    @GET("1.1/statuses/home_timeline.json")
    Call<List<Tweet>> homeTimeline(@Query("count") Integer count,
                                   @Query("trim_user") Boolean trim_user,
                                   @Query("exclude_replies") Boolean exclude_replies,
                                   @Query("contributor_details") Boolean contributor_details,
                                   @Query("include_entities") Boolean include_entities
                                   );
}

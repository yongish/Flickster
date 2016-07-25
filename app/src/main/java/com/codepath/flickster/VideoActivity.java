package com.codepath.flickster;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.flickster.models.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VideoActivity extends YouTubeBaseActivity {
    public static final String YT_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    AsyncHttpClient client;
    ArrayList<Trailer> trailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String id = getIntent().getStringExtra("id");
        trailers = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/movie/" + id + "/trailers?api_key=" + YT_API_KEY;

        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray trailersJsonResults;
                try {
                    trailersJsonResults = response.getJSONArray("youtube");
                    trailers.addAll(Trailer.fromJSONArray(trailersJsonResults));
                    Log.d("DEBUG", trailers.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                YouTubePlayerView youTubePlayerView =
                        (YouTubePlayerView) findViewById(R.id.player);

                youTubePlayerView.initialize(YT_API_KEY,
                        new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {

                                // do any work here to cue video, play video, etc.
                                //youTubePlayer.cueVideo("5xVh-7ywKpE");
                                // or to play immediately
                                youTubePlayer.setFullscreen(true);
                                youTubePlayer.loadVideo(trailers.get(0).getSource());
//                                youTubePlayer.loadVideo(videoId);
                            }
                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {
                                Toast.makeText(VideoActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }
}

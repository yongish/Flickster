package com.codepath.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getStringExtra("id");
        String backdropPath = getIntent().getStringExtra("backdropPath");
        String rating = getIntent().getStringExtra("rating");
        Double popularity = getIntent().getDoubleExtra("popularity", 0);
        String synopsis = getIntent().getStringExtra("synopsis");

        ImageView ivUnpopularBackdrop = (ImageView) findViewById(R.id.ivUnpopularBackdrop);
        Picasso.with(this).load(backdropPath).fit().centerCrop()
                .placeholder(R.drawable.default_poster).error(R.drawable.noposter)
                .transform(new RoundedCornersTransformation(15, 15))
                .into(ivUnpopularBackdrop);

        RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        ratingbar.setRating(Float.valueOf(rating));

        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText(String.valueOf(popularity));

        TextView tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvSynopsis.setText(synopsis);
    }

    public void playTrailer(View v) {
        Intent video = new Intent(DetailActivity.this, VideoActivity.class);
        video.putExtra("id", id);
        startActivity(video);
    }
}

package com.codepath.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String rating = getIntent().getStringExtra("rating");
        Double popularity = getIntent().getDoubleExtra("popularity", 0);
        String synopsis = getIntent().getStringExtra("synopsis");

        RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        ratingbar.setRating(Float.valueOf(rating));

        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText(String.valueOf(popularity));

        TextView tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvSynopsis.setText(synopsis);
    }
}

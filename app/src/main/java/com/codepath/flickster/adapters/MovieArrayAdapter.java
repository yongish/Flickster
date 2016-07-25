package com.codepath.flickster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    final int POPULAR = 5;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    public static class PopularViewHolder {
        ImageView ivBackdrop;
        TextView tvPopularTitle;
    }
    public static class LessPopularViewHolder {
        ImageView ivImage;
        TextView tvTitle;
    }

    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getVoteAverage() > POPULAR) {
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        int viewType = this.getItemViewType(position);
        View v;

        switch(viewType) {
            case 0:
                PopularViewHolder popularViewHolder;
                v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.item_popular_movie, parent, false);
                    popularViewHolder = new PopularViewHolder();
                    popularViewHolder.ivBackdrop = (ImageView) v.findViewById(R.id.ivBackdrop);
                    popularViewHolder.tvPopularTitle = (TextView) v.findViewById(R.id.tvPopularTitle);
                    v.setTag(popularViewHolder);
                } else {
                    popularViewHolder = (PopularViewHolder) v.getTag();
                }

                popularViewHolder.ivBackdrop.setImageResource(0);
                // populate data
                popularViewHolder.tvPopularTitle.setText(movie.getOriginalTitle());

                Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                        .placeholder(R.drawable.default_poster).error(R.drawable.noposter)
                        .transform(new RoundedCornersTransformation(15, 15))
                        .into(popularViewHolder.ivBackdrop);
                return v;
            case 1:
                LessPopularViewHolder lessPopularViewHolder;
                v = convertView;
                // check the existing view being reused
                if (v == null) {
                    LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = inflater.inflate(R.layout.item_movie, parent, false);
                    lessPopularViewHolder = new LessPopularViewHolder();
                    lessPopularViewHolder.ivImage = (ImageView) v.findViewById(R.id.ivImage);
                    lessPopularViewHolder.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
                    v.setTag(lessPopularViewHolder);
                } else {
                    lessPopularViewHolder = (LessPopularViewHolder) v.getTag();
                }

                // clear out image from v
                lessPopularViewHolder.ivImage.setImageResource(0);
                // populate data
                lessPopularViewHolder.tvTitle.setText(movie.getOriginalTitle());

                Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop()
                        .placeholder(R.drawable.default_poster).error(R.drawable.noposter)
                        .transform(new RoundedCornersTransformation(15, 15))
                        .into(lessPopularViewHolder.ivImage);
                // return the view
                return v;
            default:
                throw new IllegalStateException("Unknown data type");
        }
    }
}

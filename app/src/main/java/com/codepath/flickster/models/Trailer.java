package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Trailer {

    String source;

    public String getSource() {
        return source;
    }

    public Trailer(JSONObject jsonObject) throws JSONException {
        this.source = jsonObject.getString("source");
    }

    public static List<Trailer> fromJSONArray(JSONArray array) {
        ArrayList<Trailer> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Trailer(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}

package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Trailer {

    String key;
    String name;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Trailer(JSONObject jsonObject) throws JSONException {
        this.key = jsonObject.getString("key");
        this.name = jsonObject.getString("name");
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

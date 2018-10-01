package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject currentSandwich = new JSONObject(json);

            JSONObject name = currentSandwich.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);

            sandwich.setPlaceOfOrigin(currentSandwich.getString("placeOfOrigin"));

            sandwich.setDescription(currentSandwich.getString("description"));

            sandwich.setImage(currentSandwich.getString("image"));

            JSONArray ingredientsArray = currentSandwich.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the sandwich JSON results", e);
        }
        return sandwich;
    }
}

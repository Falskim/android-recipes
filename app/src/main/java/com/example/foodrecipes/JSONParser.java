package com.example.foodrecipes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONParser {

    private Context context;


    public JSONParser(Context context){
        this.context = context;
    }

    public ArrayList<Category> getRecipeCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonValue = (JSONObject)obj.get(key);

                String name = key;
                String image = jsonValue.get("image").toString();

                Category category = new Category();
                category.setName(name);
                category.setImage(image);

                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public ArrayList<Recipe> getRecipesFromCategory(String category) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            JSONArray recipeArray = new JSONObject(loadJSONFromAsset())
                    .getJSONObject(category)
                    .getJSONArray("recipe");

            for(int i = 0; i < recipeArray.length(); i++){
                JSONObject recipeJson = recipeArray.getJSONObject(i);

                String name = recipeJson.getString("name");
                String image = recipeJson.getString("image");
                String time = recipeJson.getString("time");
                String size = recipeJson.getString("size");
                String url = recipeJson.get("url").toString();

                Log.d("RecipeName", name);
                Log.d("RecipeImage", image);
                Log.d("RecipeURL", url);

                JSONArray jsonArray = recipeJson.getJSONArray("ingredients");
                List<String> ingredients = new ArrayList<>();
                for(int j = 0; j < jsonArray.length(); j++){
                    ingredients.add(jsonArray.getString(j));
                }

                jsonArray = recipeJson.getJSONArray("steps");
                List<String> steps = new ArrayList<>();
                for(int j = 0; j < jsonArray.length(); j++){
                    steps.add(jsonArray.getString(j));
                }

                Recipe recipe = new Recipe();
                recipe.setName(name);
                recipe.setImage(image);
                recipe.setTime(time);
                recipe.setSize(size);
                recipe.setUrl(url);
                recipe.setIngredients(ingredients);
                recipe.setSteps(steps);

                recipe.printData();

                recipes.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

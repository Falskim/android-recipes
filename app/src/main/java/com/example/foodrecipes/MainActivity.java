package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MyFirstTag", "Pertamax");
        getRecipeArrayList();
    }

    public ArrayList<Recipe> getRecipeArrayList() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            Iterator<String> temp = obj.keys();
            while (temp.hasNext()) {
                String key = temp.next();
                JSONObject jsonValue = (JSONObject)obj.get(key);

                String name = jsonValue.get("name").toString();
                String image = jsonValue.get("image").toString();
                String url = jsonValue.get("url").toString();

                Log.d("RecipeName", name);
                Log.d("RecipeImage", image);
                Log.d("RecipeURL", url);

                JSONArray jsonArray = jsonValue.getJSONArray("ingredients");
                List<String> ingredients = new ArrayList<>();
                for(int i = 0; i < jsonArray.length(); i++){
                    ingredients.add(jsonArray.getString(i));
                }

                jsonArray = jsonValue.getJSONArray("steps");
                List<String> steps = new ArrayList<>();
                for(int i = 0; i < jsonArray.length(); i++){
                    steps.add(jsonArray.getString(i));
                }

                Recipe recipe = new Recipe();
                recipe.setName(name);
                recipe.setImage(image);
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
            InputStream is = this.getAssets().open("Recipes.json");
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
package com.example.foodrecipes;

import android.util.Log;

import java.util.List;

public class Recipe {
    private String name;
    private String image;
    private List<String> ingredients;
    private List<String> steps;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printData() {
        Log.d("RecipeName", name);
        Log.d("RecipeImage", image);
        Log.d("RecipeUrl", url);
    }
}

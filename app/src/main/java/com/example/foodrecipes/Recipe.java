package com.example.foodrecipes;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String name;
    private String image;
    private String time;
    private String size;
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

    public String getIngredientsHTML() {
        String htmlString = "";
        for (String ingredient: ingredients) {
            htmlString += String.format("&#8226 %s<br/>", ingredient);
        }
        return htmlString;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getStepsHTML() {
        String htmlString = "";
        for (int i=0; i<steps.size(); i++) {
            htmlString += String.format("<b> Langkah-%d </b><br/>", (i+1));
            htmlString += String.format("%s <br/>", steps.get(i));
            if (i<steps.size()-1) htmlString += "<br/>";
        }
        return htmlString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void printData() {
        Log.d("RecipeName", name);
        Log.d("RecipeTime", time);
        Log.d("RecipeSize", size);
        Log.d("RecipeImage", image);
        Log.d("RecipeUrl", url);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

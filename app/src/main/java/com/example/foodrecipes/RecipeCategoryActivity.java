package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class RecipeCategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_category);

        getSupportActionBar().setTitle("Recipe Categories");

        ArrayList<Category> categories = new JSONParser(this).getRecipeCategories();
        adapter = new RecipeCategoryAdapter(this, categories);

        Log.d("MyTag", "Creating Recycler View");
        Log.d("MyTag", String.valueOf(categories));
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
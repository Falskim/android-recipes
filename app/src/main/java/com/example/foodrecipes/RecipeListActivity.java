package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        String category = getIntent().getStringExtra("category");
        getSupportActionBar().setTitle(category);
        ArrayList<Recipe> recipes = new JSONParser(this).getRecipesFromCategory(category);

        adapter = new RecipeListAdapter(this, recipes);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
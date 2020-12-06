package com.example.foodrecipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private final ArrayList<Recipe> recipeList;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipeList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recipeList = recipeList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        // set recycler view card text
        holder.cardText.setText(currentRecipe.getName());
        if (currentRecipe.getName().length() > 25)
            holder.cardText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        // set recycler view card image
        try {
            // get input stream
            InputStream ims = context.getAssets().open(currentRecipe.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.cardImage.setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView cardText;
        public final ImageView cardImage;
        final RecipeListAdapter mAdapter;

        public RecipeViewHolder(View itemView, RecipeListAdapter adapter) {
            super(itemView);
            this.cardText = itemView.findViewById(R.id.card_text);
            this.cardImage = itemView.findViewById(R.id.recipe_image);
            this.mAdapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            Recipe recipe = recipeList.get(mPosition);

            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipe", recipe);

            context.startActivity(intent);
        }
    }

}

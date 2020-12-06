package com.example.foodrecipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeCategoryAdapter extends RecyclerView.Adapter<RecipeCategoryAdapter.RecipeViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private final ArrayList<Category> categories;

    public RecipeCategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Category currentCategory = categories.get(position);
        // set recycler view card text
        holder.cardText.setText(currentCategory.getName());
        // set recycler view card image
        try {
            // get input stream
            InputStream ims = context.getAssets().open(currentCategory.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.cardImage.setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }

        Log.d("MyTag", currentCategory.getName());
        Log.d("MyTag", currentCategory.getImage());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView cardText;
        public final ImageView cardImage;
        final RecipeCategoryAdapter mAdapter;

        public RecipeViewHolder(View itemView, RecipeCategoryAdapter adapter) {
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
            String category = categories.get(mPosition).getName();

            Intent intent = new Intent(context, RecipeListActivity.class);
            intent.putExtra("category", category);

            context.startActivity(intent);
        }
    }

}

package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recipe Detail");

        Recipe recipe = (Recipe)getIntent().getSerializableExtra("recipe");
        renderRecipe(recipe);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    protected void renderRecipe(Recipe rec) {
        // set recipe name
        TextView recipeName = ((TextView)findViewById(R.id.recipe_name));
        recipeName.setText(rec.getName());
        if (rec.getName().length() > 25)
            recipeName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        // set recipe time
        ((TextView)findViewById(R.id.recipe_time)).setText(rec.getTime());
        // set recipe size
        ((TextView)findViewById(R.id.recipe_size)).setText(rec.getSize());
        // set recipe image
        try {
            // get input stream
            InputStream ims = this.getAssets().open(rec.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ((ImageView)findViewById(R.id.recipe_image)).setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
        // set recipe ingredients
        ((TextView)findViewById(R.id.recipe_ingredients)).setText(Html.fromHtml(rec.getIngredientsHTML()));
        // set recipe steps
        ((TextView)findViewById(R.id.recipe_steps)).setText(Html.fromHtml(rec.getStepsHTML()));
        // set recipe source url
        ((Button)findViewById(R.id.recipe_source)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(rec.getUrl()));
                startActivity(intent);
            }
        });
    }
}
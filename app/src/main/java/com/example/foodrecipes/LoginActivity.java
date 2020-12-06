package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private final String USERNAME = "user";
    private final String PASSWORD = "user";

    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);

        // autofill
        inputUsername.setText(USERNAME);
        inputPassword.setText(PASSWORD);

        getSupportActionBar().setTitle("Login");
    }

    public void login(View view) {
        String userUsername = inputUsername.getText().toString();
        String userPassword = inputPassword.getText().toString();

        if (!userUsername.equalsIgnoreCase(USERNAME) || !userPassword.equals(PASSWORD)) {
            Toast.makeText(this, "Wrong Login Account", Toast.LENGTH_SHORT).show();
            return;
        }

        this.startActivity(new Intent(this, RecipeCategoryActivity.class));
    }


}
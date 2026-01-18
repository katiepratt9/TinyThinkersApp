package com.example.tinythinkers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PictureAlphabetMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_picturealphabet);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnBack = findViewById(R.id.btnBack);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(PictureAlphabetMenuActivity.this, PictureAlphabetActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(PictureAlphabetMenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
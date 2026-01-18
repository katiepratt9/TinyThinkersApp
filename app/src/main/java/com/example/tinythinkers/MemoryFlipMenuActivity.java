package com.example.tinythinkers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MemoryFlipMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_memoryflip);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnBack = findViewById(R.id.btnBack);

        // Go to the game screen
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MemoryFlipMenuActivity.this, MemoryFlipActivity.class);
            startActivity(intent);
        });

        // Go back to main menu
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MemoryFlipMenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

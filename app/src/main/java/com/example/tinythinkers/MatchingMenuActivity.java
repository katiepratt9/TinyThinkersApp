package com.example.tinythinkers;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class MatchingMenuActivity extends AppCompatActivity {

    private MediaPlayer playSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_matching);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnBack = findViewById(R.id.btnBack);

        playSound = MediaPlayer.create(this, R.raw.popupsound);

        btnPlay.setOnClickListener(v -> {

            playSound.start();

            Intent intent = new Intent(MatchingMenuActivity.this, MatchingActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MatchingMenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

package com.example.tinythinkers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MathMenuActivity extends AppCompatActivity {

    MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_math);

        clickSound = MediaPlayer.create(this, R.raw.popupsound);

        Button add = findViewById(R.id.button);
        Button sub = findViewById(R.id.button2);
        Button mul = findViewById(R.id.button3);
        Button div = findViewById(R.id.button4);
        Button btnBack = findViewById(R.id.button5);

        add.setOnClickListener(v -> playSoundAndOpen("add"));
        sub.setOnClickListener(v -> playSoundAndOpen("sub"));
        mul.setOnClickListener(v -> playSoundAndOpen("mul"));
        div.setOnClickListener(v -> playSoundAndOpen("div"));

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(MathMenuActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void playSoundAndOpen(String operation) {
        if (clickSound != null) clickSound.start();
        Intent intent = new Intent(MathMenuActivity.this, MathActivity.class);
        intent.putExtra("operation", operation);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clickSound != null) clickSound.release();
    }
}


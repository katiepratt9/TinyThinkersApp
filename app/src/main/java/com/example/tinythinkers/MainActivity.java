package com.example.tinythinkers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start music service safely
        try {
            Intent musicIntent = new Intent(this, BackgroundMusicService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(musicIntent);
            } else {
                startService(musicIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openPictureAlphabet(android.view.View view) {
        startActivity(new Intent(this, PictureAlphabetMenuActivity.class));
    }

    public void openHangman(android.view.View view) {
        startActivity(new Intent(this, HangmanMenuActivity.class));
    }

    public void openMath(android.view.View view) {
        startActivity(new Intent(this, MathMenuActivity.class));
    }

    public void openMemoryFlip(android.view.View view) {
        startActivity(new Intent(this, MemoryFlipMenuActivity.class));
    }

    public void openTicTacToe(android.view.View view) {
        startActivity(new Intent(this, TicTacToeMenuActivity.class));
    }

    public void openMatching(android.view.View view) {
        startActivity(new Intent(this, MatchingMenuActivity.class));
    }

    public void openReferenceLinks(View view) {
        startActivity(new Intent(this, ReferenceLinks.class));
    }
    // Music stops only when app is fully closed
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}
package com.example.tinythinkers;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchingActivity extends AppCompatActivity {

    private String selectedShape = "";
    private int highScore = 0;
    private TextView highScoreText;
    private MediaPlayer correctAnswer;
    private MediaPlayer wrongAnswer;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "matchingGamePrefs";
    private static final String HIGH_SCORE_KEY = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);

        //image button
        ImageView circleShape = findViewById(R.id.circleShape);
        ImageView squareShape = findViewById(R.id.squareShape);
        ImageView triangleShape = findViewById(R.id.triangleShape);

        //text button
        Button circleBtn = findViewById(R.id.circleBtn);
        Button squareBtn = findViewById(R.id.squareBtn);
        Button triangleBtn = findViewById(R.id.triangleBtn);
        // pause butoton
        ImageButton pauseButton = findViewById(R.id.pause);
        if (pauseButton != null) {
            pauseButton.setOnClickListener(v -> showPauseDialog());
        }

        // High Score Text
        highScoreText = findViewById(R.id.highScoreText);

        //  MediaPlayers
        correctAnswer = createMediaPlayer(R.raw.correctnswer);
        wrongAnswer = createMediaPlayer(R.raw.wronganswer);

        //  click listeners for shapes
        if (circleShape != null) circleShape.setOnClickListener(v -> selectedShape = "Circle");
        if (squareShape != null) squareShape.setOnClickListener(v -> selectedShape = "Square");
        if (triangleShape != null)
            triangleShape.setOnClickListener(v -> selectedShape = "Triangle");

        // Button click listeners for text
        if (circleBtn != null) circleBtn.setOnClickListener(v -> checkAnswer("Circle"));
        if (squareBtn != null) squareBtn.setOnClickListener(v -> checkAnswer("Square"));
        if (triangleBtn != null) triangleBtn.setOnClickListener(v -> checkAnswer("Triangle"));
    }
    private MediaPlayer createMediaPlayer(int resId) {
        try {
            return MediaPlayer.create(this, resId);
        } catch (Exception e) {
            Toast.makeText(this, "Sound not found: " + resId, Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    private void checkAnswer(String nameSelected) {
        if (selectedShape.isEmpty()) {
            Toast.makeText(this, "Please tap a shape first!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedShape.equals(nameSelected)) {
            if (correctAnswer != null) correctAnswer.start();
            highScore++;
            saveHighScore();
            updateHighScoreText();
            showDialog("Correct!", "Well done! You matched it correctly.");
        } else {
            if (wrongAnswer != null) wrongAnswer.start();
            showDialog("Try Again!", "Oops! That's not the right match.");
        }
        selectedShape = "";
    }
    //update the score
    private void updateHighScoreText() {
        if (highScoreText != null)
            highScoreText.setText("👑 High Score: " + highScore);
    }
    private void saveHighScore() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HIGH_SCORE_KEY, highScore);
        //save the score
        editor.apply();
    }//pause button
    private void showPauseDialog() {
        new AlertDialog.Builder(this)
                .setTitle("How to Play")
                .setMessage("Click the shape and then click the name of the shape.")
                .setPositiveButton("BACK TO MENU", (dialog, which) -> goBackToMenu())
                .setNegativeButton("CONTINUE", (dialog, which) -> dialog.dismiss())
                .show();
    }//pause button
    private void goBackToMenu() {
        Intent intent = new Intent(MatchingActivity.this, MatchingMenuActivity.class);
        startActivity(intent);
        finish();
    }
    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("BACK", (dialog, which) -> {
                    Intent intent = new Intent(MatchingActivity.this, MatchingMenuActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    };
}

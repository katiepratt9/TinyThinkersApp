package com.example.tinythinkers;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class PictureAlphabetActivity extends AppCompatActivity {

    private ImageView imgLetter;
    private EditText etAnswer;
    private TextView tvHighScore;
    private Button btnSubmit, btnClear;
    private ImageButton btnHelp;

    private int score = 0, highScore = 0, currentIndex = 0;
    private int attempts = 0;
    private ArrayList<Integer> imageList;
    private ArrayList<Character> correctAnswers;
    private SharedPreferences prefs;
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_alphabet_game);

        imgLetter = findViewById(R.id.imgLetter);
        etAnswer = findViewById(R.id.etAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        btnHelp = findViewById(R.id.btnHelp);
        tvHighScore = findViewById(R.id.tvHighScore);

        correctSound = MediaPlayer.create(this, R.raw.correctnswer);
        wrongSound = MediaPlayer.create(this, R.raw.wronganswer);

        prefs = getSharedPreferences("PictureAlphabet", MODE_PRIVATE);
        highScore = prefs.getInt("HighScore", 0);
        tvHighScore.setText("🏆 High Score: " + highScore);

        loadRandomAlphabet();
        showImage();

        btnSubmit.setOnClickListener(v -> checkAnswer());
        btnClear.setOnClickListener(v -> etAnswer.setText(""));
        btnHelp.setOnClickListener(v -> showHelpDialog());
    }

    private void loadRandomAlphabet() {
        imageList = new ArrayList<>();
        correctAnswers = new ArrayList<>();

        String[] imageNames = {
                "apple", "balloon", "car", "dog", "elephant",
                "fish", "grape", "hat", "icecream", "juice",
                "kite", "lion", "music", "nine", "orange",
                "pineapple", "question", "robot", "sun", "tree",
                "umbrella", "vegetables", "water", "xylophone", "yoyo", "zebra"
        };

        for (int i = 0; i < imageNames.length; i++) {
            int resId = getResources().getIdentifier(imageNames[i], "drawable", getPackageName());
            if (resId != 0) {
                imageList.add(resId);
                correctAnswers.add((char) ('A' + i));
            }
        }

        long seed = System.nanoTime();
        Collections.shuffle(imageList, new java.util.Random(seed));
        Collections.shuffle(correctAnswers, new java.util.Random(seed));
    }

    private void showImage() {
        imgLetter.setImageResource(imageList.get(currentIndex));
        etAnswer.setText("");
    }

    private void checkAnswer() {
        String input = etAnswer.getText().toString().trim().toUpperCase();

        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a letter!", Toast.LENGTH_SHORT).show();
            return;
        }

        char correct = correctAnswers.get(currentIndex);

        if (input.charAt(0) == correct) {
            if (correctSound != null) correctSound.start();
            score++;
            attempts = 0;
            Toast.makeText(this, "🎉 Correct!", Toast.LENGTH_SHORT).show();
            showNextOrExitDialog(); // same behavior
        }

        else {
            attempts++;

            if (wrongSound != null) wrongSound.start();
            Toast.makeText(this, "❌ Try again!", Toast.LENGTH_SHORT).show();

            if (attempts < 3) {
                return;
            }

            attempts = 0;

            new AlertDialog.Builder(this)
                    .setTitle("Out of Tries!")
                    .setMessage("The correct answer was: " + correct +
                            "\n\nDo you want to move to the next question or exit the game?")
                    .setCancelable(false)
                    .setPositiveButton("Next", (dialog, which) -> {
                        currentIndex = (currentIndex + 1) % imageList.size();
                        showImage();
                        dialog.dismiss();
                    })
                    .setNegativeButton("Exit", (dialog, which) -> {
                        Intent intent = new Intent(PictureAlphabetActivity.this, PictureAlphabetMenuActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .show();

            return;
        }

        if (score > highScore) {
            highScore = score;
            prefs.edit().putInt("HighScore", highScore).apply();
            tvHighScore.setText("🏆 High Score: " + highScore);
        }
    }

    private void showNextOrExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Next Question?")
                .setMessage("Do you want to move to the next picture or exit the game?")
                .setCancelable(false)
                .setPositiveButton("Next", (dialog, which) -> {

                    currentIndex = (currentIndex + 1) % imageList.size();
                    showImage();
                    dialog.dismiss();
                })
                .setNegativeButton("Exit", (dialog, which) -> {

                    Intent intent = new Intent(PictureAlphabetActivity.this, PictureAlphabetMenuActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }

    private void showHelpDialog() {
        new AlertDialog.Builder(this)
                .setTitle("How to Play 🐜")
                .setMessage("Look at the picture and guess the first letter of the word!\n\n" +
                        "Example: 🐜 = A for Ant\n\n" +
                        "Type your answer and press 'Submit'.\nUse 'Clear' to erase your answer.")
                .setPositiveButton("OK", null)
                .setNegativeButton("BACK TO MENU", (dialog, which) -> {
                    Intent intent = new Intent(PictureAlphabetActivity.this, PictureAlphabetMenuActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}

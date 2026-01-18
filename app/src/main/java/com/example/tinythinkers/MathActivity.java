package com.example.tinythinkers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MathActivity extends AppCompatActivity {

    LinearLayout layoutNum1, layoutNum2;
    TextView operationSymbol, highScoreText;
    EditText answerInput;
    Button submitBtn;

    int num1, num2, correctAnswer;
    String operation;

    int score = 0;
    int questionCount = 0;
    int totalQuestions = 5;
    int attemptsLeft = 3;

    MediaPlayer correctSound, wrongSound, clickSound;

    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "mathGamePrefs";
    private static final String HIGH_SCORE_KEY = "highScore";
    private int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game);
        //sounds
        correctSound = MediaPlayer.create(this, R.raw.correctnswer);
        wrongSound = MediaPlayer.create(this, R.raw.wronganswer);
        clickSound = MediaPlayer.create(this, R.raw.popupsound);
        //pause button
        ImageButton pauseButton = findViewById(R.id.pause);
        if (pauseButton != null) {
            pauseButton.setOnClickListener(v -> showPauseDialog());
        }
        //
        layoutNum1 = findViewById(R.id.layoutNum1);
        layoutNum2 = findViewById(R.id.layoutNum2);
        operationSymbol = findViewById(R.id.operationSymbol);
        answerInput = findViewById(R.id.answerInput);
        submitBtn = findViewById(R.id.submitBtn);
        highScoreText = findViewById(R.id.highScoreText);

        // SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
        updateHighScoreText();

        operation = getIntent().getStringExtra("operation");
        // Generate first problem
        generateCatProblem();
        // play click sound when submit is pressed
        submitBtn.setOnClickListener(v -> {
            clickSound.start();
            checkAnswer();
        });
    }
    private void generateCatProblem() {
        layoutNum1.removeAllViews();
        layoutNum2.removeAllViews();
        //setting attempts
        attemptsLeft = 3;

        Random r = new Random();
        //for kids so random number is from 1-8
        //easy to play with small numbers
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(8) + 1;
        //no zero div
        if ("div".equals(operation) && num2 == 0) num2 = 1;

        switch (operation) {
            case "add":
                correctAnswer = num1 + num2;
                operationSymbol.setText("+");
                break;
            case "sub":
                correctAnswer = num1 - num2;
                operationSymbol.setText("-");
                break;
            case "mul":
                correctAnswer = num1 * num2;
                operationSymbol.setText("×");
                break;
            case "div":
                correctAnswer = num1 / num2;
                operationSymbol.setText("÷");
                break;
        }
        addCats(layoutNum1, num1);
        addCats(layoutNum2, num2);

        answerInput.setText("");
    }
    private void addCats(LinearLayout layout, int count) {
        for (int i = 0; i < count; i++) {
            ImageView cat = new ImageView(this);
            cat.setImageResource(R.drawable.onecat);
            cat.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
            layout.addView(cat);
        }
    }
    private void checkAnswer() {
        String ansStr = answerInput.getText().toString();
        if (ansStr.isEmpty()) {
            Toast.makeText(this, "Enter your answer!", Toast.LENGTH_SHORT).show();
            return;
        }
        int userAnswer = Integer.parseInt(ansStr);
        if (userAnswer == correctAnswer) {
            // play correct answer sound if the answer is correct
            if (correctSound != null) correctSound.start();
            score++;
            questionCount++;
            //update the new score if higher
            if (score > highScore) {
                highScore = score;
                //save
                saveHighScore();
                updateHighScoreText();
            }
            showNextOrFinishAlert("✅ Correct!", "Well done!");
        } else {
            // play wrong answer sound
            if (wrongSound != null) wrongSound.start();
            attemptsLeft--;
            //attempt 3 than show the answer
            if (attemptsLeft > 0) {
                Toast.makeText(this, "❌ Wrong! Try again. Attempts left: " + attemptsLeft, Toast.LENGTH_SHORT).show();
            } else {
                questionCount++;
                showNextOrFinishAlert("❌ Out of Attempts!", "Correct Answer: " + correctAnswer);
            }
        }
    }
    private void showNextOrFinishAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("NEXT", (dialog, which) -> {
                    if (questionCount < totalQuestions) {
                        generateCatProblem();
                    } else {
                        goToResult();
                    }
                })
                .setNegativeButton("FINISH", (dialog, which) -> goToResult())
                .show();
    }
    private void goToResult() {
        Intent intent = new Intent(MathActivity.this, MathResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
    private void saveHighScore() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HIGH_SCORE_KEY, highScore);
        editor.apply();
    }
    private void updateHighScoreText() {
        if (highScoreText != null)
            highScoreText.setText("🏆 High Score: " + highScore);
    }
    private void showPauseDialog() {
        new AlertDialog.Builder(this)
                .setTitle("How to Play")
                .setMessage("The game allows the player to select a shape and submit their answer. If the player selects the wrong shape three times, the correct answer is revealed. Each correct match increases the high score. A pause button lets the player pause the game or return to the main menu.")
                .setPositiveButton("BACK TO MENU", (dialog, which) -> goBackToMenu())
                .setNegativeButton("CONTINUE", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void goBackToMenu() {
        Intent intent = new Intent(MathActivity.this, MathMenuActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (correctSound != null) correctSound.release();
        if (wrongSound != null) wrongSound.release();
        if (clickSound != null) clickSound.release();
    }
}

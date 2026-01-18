package com.example.tinythinkers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MathResultActivity extends AppCompatActivity {

    TextView finalScoreText;
    Button playAgainBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_result);

        finalScoreText = findViewById(R.id.finalScoreText);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        exitBtn = findViewById(R.id.exitBtn);

        int score = getIntent().getIntExtra("score", 0);
        finalScoreText.setText("🏆 Your Score: " + score);

        playAgainBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MathResultActivity.this, MathMenuActivity.class);
            startActivity(intent);
            finish();
        });

        exitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MathResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}




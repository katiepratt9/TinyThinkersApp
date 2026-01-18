package com.example.tinythinkers;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryFlipActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private List<Integer> cardImages;
    private ImageView firstCard, secondCard, firstBack, secondBack;
    private boolean busy = false;

    private int moves = 0;
    private int matchedPairs = 0;
    private int HScore = Integer.MAX_VALUE;
    private TextView highScore;
    private SharedPreferences prefs;
    private MediaPlayer correctnswer;
    private MediaPlayer wronganswer;

    private int[] images = {
            R.drawable.lion1,
            R.drawable.panda,
            R.drawable.monkey,
            R.drawable.croc,
            R.drawable.bear,
            R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_flip_game);

        gridLayout = findViewById(R.id.gridBoard);
        highScore = findViewById(R.id.highScore);
        ImageButton btnHelp = findViewById(R.id.btnHelp);

        correctnswer = MediaPlayer.create(this, R.raw.correctnswer);
        wronganswer = MediaPlayer.create(this, R.raw.wronganswer);

        prefs = getSharedPreferences("MemoryFlipPrefs", MODE_PRIVATE);
        HScore = prefs.getInt("best_moves", Integer.MAX_VALUE);

        if (HScore == Integer.MAX_VALUE) {
            highScore.setText("-");
        } else {
            highScore.setText(String.valueOf(HScore));
        }

        btnHelp.setOnClickListener(v -> showHelpDialog());

        setupBoard();
    }

    private void setupBoard() {
        gridLayout.removeAllViews();
        cardImages = new ArrayList<>();
        moves = 0;
        matchedPairs = 0;

        for (int img : images) {
            cardImages.add(img);
            cardImages.add(img);
        }
        Collections.shuffle(cardImages);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < cardImages.size(); i++) {
            View cardView = inflater.inflate(R.layout.memory_flip_item_card, gridLayout, false);
            ImageView imgFront = cardView.findViewById(R.id.imgFront);
            ImageView imgBack = cardView.findViewById(R.id.imgBack);

            int imageId = cardImages.get(i);
            imgFront.setImageResource(imageId);

            cardView.setOnClickListener(v -> flipCard(imgFront, imgBack, imageId));
            gridLayout.addView(cardView);
        }
    }

    private void flipCard(ImageView front, ImageView back, int imageId) {
        if (busy || front.getVisibility() == View.VISIBLE) return;

        back.setVisibility(View.GONE);
        front.setVisibility(View.VISIBLE);

        if (firstCard == null) {
            firstCard = front;
            firstBack = back;
        } else {
            secondCard = front;
            secondBack = back;
            busy = true;
            moves++;
            gridLayout.postDelayed(this::checkMatch, 600);
        }
    }

    private void checkMatch() {
        if (firstCard.getDrawable().getConstantState() == secondCard.getDrawable().getConstantState()) {
            matchedPairs++;
            if (correctnswer != null) correctnswer.start();
            Toast.makeText(this, "Match!", Toast.LENGTH_SHORT).show();

            // Checking if all pairs are matched
            if (matchedPairs == images.length) {
                onGameComplete();
            }
        } else {
            if (wronganswer != null) wronganswer.start();
            firstCard.setVisibility(View.GONE);
            secondCard.setVisibility(View.GONE);
            firstBack.setVisibility(View.VISIBLE);
            secondBack.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Not A Match. Try Again!", Toast.LENGTH_SHORT).show();
        }

        firstCard = null;
        secondCard = null;
        firstBack = null;
        secondBack = null;
        busy = false;
    }

    private void onGameComplete() {
        String message;
        if (moves < HScore) {
            HScore = moves;
            prefs.edit().putInt("best_moves", HScore).apply();
            highScore.setText(String.valueOf(HScore));
            message = "New Best Record! You finished in " + moves + " moves!";
        } else {
            message = "Good job! You finished in " + moves + " moves!";
        }

        new AlertDialog.Builder(this)
                .setTitle("You Won! 🏆")
                .setMessage(message)
                .setPositiveButton("Play Again", (dialog, which) -> setupBoard())
                .setNegativeButton("Back to Menu", (dialog, which) -> finish())
                .show();
    }
    private void showHelpDialog() {
        new AlertDialog.Builder(this)
                .setTitle("How to Play 🎮")
                .setMessage("Flip two cards at a time to find matching pairs.\n" +
                        "Try to match all the animals!\n\n")
                .setPositiveButton("Got it!", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("BACK TO MENU", (dialog, which) ->  {
                    Intent intent = new Intent(MemoryFlipActivity.this, MemoryFlipMenuActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}
package com.example.tinythinkers;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
public class HangmanActivity extends AppCompatActivity {
    String[][] words = {{"CAT", "DOG", "HAT", "THE", "BUG", "MUG", "LET", "LIE", "TIE", "WIN"},
            {"TICK", "BEAR", "BEAN", "GAME", "LOSE", "TIME", "LEFT", "TRUE", "DARK", "MOON"},
            {"QUIRK", "LUCKY", "RHINO", "GNOME", "IGLOO", "SHARE", "CURSE", "FUNKY", "ORBIT", "HAUNT"},
    };
    String rightWord = "";
    int guessesLeft = 6;
    char guess = 'a';
    String lettersGuessed = "";
    boolean gameOver = false;
    int wins = 0;
    int highScore;
    TextView l1, l2, l3, l4, l5, letterbank, highScr;
    EditText guessSpot;
    ImageView hang;
    ImageButton pause;
    Button confirmB;
    private MediaPlayer wrongSound;
    private MediaPlayer correctSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.hangman_game);

        correctSound = MediaPlayer.create(this, R.raw.correctnswer);
        wrongSound = MediaPlayer.create(this, R.raw.wronganswer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameTitle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        l1 = findViewById(R.id.firstLetter);
        l2 = findViewById(R.id.secondLetter);
        l3 = findViewById(R.id.thirdLetter);
        l4 = findViewById(R.id.fourthLetter);
        l5 = findViewById(R.id.fifthLetter);
        highScr = findViewById(R.id.highscore);
        pause = findViewById(R.id.pause);
        letterbank = findViewById(R.id.letterBank);
        hang = findViewById(R.id.hangman);
        guessSpot = findViewById(R.id.guess);
        confirmB = findViewById(R.id.confirm_button);
        rightWord = pickAWord();
        highScore = getHighScore();
        highScr.setText("Highscore: " + highScore);
        Toast.makeText(HangmanActivity.this, rightWord, Toast.LENGTH_SHORT).show();
        setBoard(rightWord.length());
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HangmanActivity.this);
                builder.setIcon(R.drawable.q);
                builder.setTitle("How to play");
                builder.setMessage("Hangman Rules:A secret word of length 3-5 letters is chosen at random. You must guess the word one letter at a time." +
                        "If the letter is in the word, it will appear in its correct position. If the letter is NOT in the word, the hangman drawing progresses." +
                        "You have 6 wrong guesses before the hangman is complete. If you reveal all the letters before running out of guesses, you win." +
                        "If the drawing is finished before you guess the word, you lose.");
                builder.setCancelable(false);
                builder.setPositiveButton("RESUME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent game = new Intent(HangmanActivity.this, HangmanMenuActivity.class);
                        HangmanActivity.this.startActivity(game);
                    }
                });
                builder.show();
            }
        });
        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGameOver();
                String input = guessSpot.getText().toString().trim();
                if (!gameOver) {
                    if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                        Toast.makeText(HangmanActivity.this, "Enter exactly one letter!", Toast.LENGTH_SHORT).show();
                        guessSpot.setText("");
                        return;
                    }
                    guess = Character.toUpperCase(input.charAt(0));

                    if (lettersGuessed.contains(guess + "")) {
                        Toast.makeText(HangmanActivity.this, "You already guessed that letter!", Toast.LENGTH_SHORT).show();
                        guessSpot.setText("");
                        return;
                    }
                    checkGameOver();
                    lettersGuessed += guess;
                    letterbank.setText(lettersGuessed.replaceAll("", " ").trim());
                    checkGuess();
                    guessSpot.setText("");
                }
            }
        });
    }
    public void checkGameOver() {
        checkWin();
        checkLose();
    }
    private void saveHighScore(int score) {
        getPreferences(MODE_PRIVATE)
                .edit()
                .putInt("HIGH_SCORE", score)
                .apply();
    }
    private int getHighScore() {
        return getPreferences(MODE_PRIVATE)
                .getInt("HIGH_SCORE", 0);
    }
    public String pickAWord() {

        Random rand = new Random();
        int howManyLetters = rand.nextInt(5 - 3 + 1) + 3;
        int indexRandomWord = rand.nextInt(10 - 0 + 1) + 0;
        String rightWord = "";
        rightWord = words[howManyLetters - 3][indexRandomWord];
        return rightWord;
    }
    public void setBoard(int length) {
        l1.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.INVISIBLE);
        l3.setVisibility(View.INVISIBLE);
        l4.setVisibility(View.INVISIBLE);
        l5.setVisibility(View.INVISIBLE);

        if (length == 3) {
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
        } else if (length == 4) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
        } else if (length == 5) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
            l3.setVisibility(View.VISIBLE);
            l4.setVisibility(View.VISIBLE);
            l5.setVisibility(View.VISIBLE);
        }
        l1.setText("_");
        l2.setText("_");
        l3.setText("_");
        l4.setText("_");
        l5.setText("_");
    }
    public void checkGuess() {
        checkGameOver();
        if (gameOver == false) {
            if (!(rightWord.contains(guess + ""))) {
                guessesLeft -= 1;
                wrongSound.start();
                checkGameOver();
                setImageHangman();
            } else {
                correctSound.start();
                updateWord();

            }
        }
    }
    private void setImageHangman() {
        if(guessesLeft == 6)
        {
            hang.setImageResource(R.drawable.hone);
        }
        else if (guessesLeft == 5) {
            hang.setImageResource(R.drawable.htwo);
        } else if (guessesLeft == 4) {
            hang.setImageResource(R.drawable.hthree);
        } else if (guessesLeft == 3) {
            hang.setImageResource(R.drawable.hfour);
        } else if (guessesLeft == 2) {
            hang.setImageResource(R.drawable.hfive);
        } else if (guessesLeft == 1) {
            hang.setImageResource(R.drawable.hsix);
        } else if (guessesLeft == 0) {
            hang.setImageResource(R.drawable.hseven);
        }
    }
    private void updateWord() {
        checkGameOver();
        if (gameOver == false) {
            if (rightWord.length() == 3) {
                for (int i = 0; i < 3; i++) {
                    if (inLettersGuessed(rightWord.charAt(i))) {
                        if (i == 0) {
                            l2.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 1) {
                            l3.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 2) {
                            l4.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                    }
                }
            }
            if (rightWord.length() == 4) {
                for (int i = 0; i < rightWord.length(); i++) {
                    if (inLettersGuessed(rightWord.charAt(i))) {
                        if (i == 0) {
                            l1.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 1) {
                            l2.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 2) {
                            l3.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 3) {
                            l4.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                    }
                }
            }
            if (rightWord.length() == 5) {
                for (int i = 0; i < rightWord.length() ; i++) {
                    if (inLettersGuessed(rightWord.charAt(i))) {
                        if (i == 0) {
                            l1.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 1) {
                            l2.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 2) {
                            l3.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 3) {
                            l4.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                        if (i == 4) {
                            l5.setText(rightWord.valueOf(rightWord.charAt(i)));
                        }
                    }
                }
            }

        }
        checkGameOver();
    }
    public boolean inLettersGuessed(char c)
    {
        for(int i = 0; i < lettersGuessed.length(); i++)
        {
            if(c == lettersGuessed.charAt(i))
            {
                return true;
            }
        }
        return false;
    }
    private void checkWin() {
        int winCount = 0;
        if (rightWord.length() == 3) {
            for (int i = 0; i < 3; i++) {
                if (inLettersGuessed(rightWord.charAt(i))) {
                    winCount++;
                }
            }
        }
        if (rightWord.length() == 4) {
            for (int i = 0; i < rightWord.length(); i++) {
                if (inLettersGuessed(rightWord.charAt(i))) {
                    winCount++;
                }
            }
        }
        if (rightWord.length() == 5) {
            for (int i = 0; i < rightWord.length() ; i++) {
                if (inLettersGuessed(rightWord.charAt(i))) {
                    winCount++;
                }
            }
        }
        if (winCount == rightWord.length()) {
            gameOver = true;
            wins++;
            if (wins > highScore) {
                highScore = wins;
                saveHighScore(highScore);
                highScr.setText("👑High Score: " + highScore);
            }
            String message = "You Win!";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(message);
            builder.setMessage("What would you like to do now ?");
            builder.setCancelable(false);
            builder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    rightWord = "";
                    guessesLeft = 6;
                    setImageHangman();
                    lettersGuessed = "";
                    letterbank.setText("");
                    gameOver = false;
                    rightWord = pickAWord();
                    Toast.makeText(HangmanActivity.this, rightWord, Toast.LENGTH_SHORT).show();
                    setBoard(rightWord.length());
                }
            });
            builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent game = new Intent(HangmanActivity.this, HangmanMenuActivity.class);
                    HangmanActivity.this.startActivity(game);
                }
            });
            builder.show();
        }
    }
    private void checkLose() {
        if (guessesLeft == 0) {
            gameOver = true;
            String message = "You Lose! Word was: " + rightWord;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(message);
            builder.setMessage("What would you like to do now ?");
            builder.setCancelable(false);
            builder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    rightWord = "";
                    guessesLeft = 6;
                    setImageHangman();
                    lettersGuessed = "";
                    letterbank.setText("");
                    gameOver = false;
                    rightWord = pickAWord();
                    Toast.makeText(HangmanActivity.this, rightWord, Toast.LENGTH_SHORT).show();
                    setBoard(rightWord.length());
                }
            });
            builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent game = new Intent(HangmanActivity.this, HangmanMenuActivity.class);
                    HangmanActivity.this.startActivity(game);
                }
            });
            builder.show();
        }
    }
}




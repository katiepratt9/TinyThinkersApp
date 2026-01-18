package com.example.tinythinkers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HangmanMenuActivity extends AppCompatActivity {
    Button back;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_hangman);
        back = findViewById(R.id.backTTTM);
        play = findViewById(R.id.play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameTitle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sends user back to game selection
                Intent game = new Intent(HangmanMenuActivity.this, MainActivity.class);
                HangmanMenuActivity.this.startActivity(game);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(HangmanMenuActivity.this, HangmanActivity.class);
                HangmanMenuActivity.this.startActivity(game);
            }
        });
    }
}
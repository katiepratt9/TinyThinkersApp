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

public class TicTacToeMenuActivity extends AppCompatActivity {
    Button back;
    Button onep;
    Button twop;
    String againstwho = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_tictactoe);
        back = findViewById(R.id.backTTTM);
        onep = findViewById(R.id.oneplayer);
        twop = findViewById(R.id.twoplayer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameTitle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user back to game selection
                Intent game = new Intent(TicTacToeMenuActivity.this, MainActivity.class);
                TicTacToeMenuActivity.this.startActivity(game);
            }
        });
        onep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                againstwho = "one";
                Intent game = new Intent(TicTacToeMenuActivity.this, TicTacToeActivity.class);
                game.putExtra("againstwho", againstwho);
                TicTacToeMenuActivity.this.startActivity(game);
            }
        });
        twop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                againstwho = "two";
                Intent game = new Intent(TicTacToeMenuActivity.this, TicTacToeActivity.class);
                game.putExtra("againstwho", againstwho);
                TicTacToeMenuActivity.this.startActivity(game);
            }
        });
    }
}
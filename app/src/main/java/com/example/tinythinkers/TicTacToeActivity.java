package com.example.tinythinkers;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TicTacToeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    char[] board = {'n','n','n','n','n','n','n','n','n'};
    ImageView A;
    ImageView B;
    ImageView C;
    ImageView D;
    ImageView E;
    ImageView F;
    ImageView G;
    ImageView H;
    ImageView I;
    ImageButton pause;
    boolean turn = true;
    boolean game = true;
    boolean computerTurn = false;
    TextView p1;
    TextView p2;
    String againstwho;
    TextView highScr;
    int wins;
    int losses;
    int ties;
    MediaPlayer correctnswer;
    MediaPlayer wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tictactoe_game);
        Intent intent = getIntent();
        againstwho = intent.getStringExtra("againstwho");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameTitle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            prefs = getPreferences(MODE_PRIVATE);
            editor = prefs.edit();
            wins = prefs.getInt("wins", 0);
            losses = prefs.getInt("losses", 0);
            ties = prefs.getInt("ties", 0);
            correctnswer = MediaPlayer.create(this, R.raw.correctnswer);
            wrong = MediaPlayer.create(this, R.raw.wronganswer);
            if(againstwho.equals("two"))
            {
                highScr.setVisibility(View.INVISIBLE);
            }
            else
            {
                highScr.setVisibility(View.VISIBLE);
                highScr.setText("Wins: " + wins + "\nLosses: " + losses + "\nTies: " + ties);
            }
            return insets;
        });
        pause = findViewById(R.id.pause);
        p1 = findViewById(R.id.textView3);
        p2 = findViewById(R.id.textView4);
        highScr = findViewById(R.id.highscore);
        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);
        F = findViewById(R.id.F);
        G = findViewById(R.id.G);
        H = findViewById(R.id.H);
        I = findViewById(R.id.I);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicTacToeActivity.this);
                builder.setIcon(R.drawable.q);
                builder.setTitle("How to Play");
                builder.setMessage("Tic-Tac-Toe Rules: The game is played on a 3×3 grid. Player 1 is X and Player 2 (or the computer) is O. " +
                        "Players take turns placing their mark in an empty spot. The first player to get 3 in a row (horizontally, vertically, or diagonally) " +
                        "wins. If all 9 squares are filled and nobody has 3 in a row, the game ends in a tie.");
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
                        Intent game = new Intent(TicTacToeActivity.this, TicTacToeMenuActivity.class);
                        TicTacToeActivity.this.startActivity(game);
                    }
                });
                builder.show();
            }
        });
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(0, A);
                } else {
                    if (!computerTurn && game) {
                        turn(0, A);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(1, B);
                } else {
                    if (!computerTurn && game) {
                        turn(1, B);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(2, C);
                } else {
                    if (!computerTurn && game) {
                        turn(2, C);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(3, D);
                } else {
                    if (!computerTurn && game) {
                        turn(3, D);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(4, E);
                } else {
                    if (!computerTurn && game) {
                        turn(4, E);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(5, F);
                } else {
                    if (!computerTurn && game) {
                        turn(5, F);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(6, G);
                } else {
                    if (!computerTurn && game) {
                        turn(6, G);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(7, H);
                } else {
                    if (!computerTurn && game) {
                        turn(7, H);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (againstwho.equals("two")) {
                    turn(8, I);
                } else {
                    if (!computerTurn && game) {
                        turn(8, I);
                        computerTurn = true;
                        new Handler().postDelayed(() -> {
                            if (game) {
                                computerTurn();
                                computerTurn = false;
                            }
                        }, 1000); //1 second delay for effect
                    }
                }
            }
        });
    }
    void addWin() {
        int wins = prefs.getInt("wins", 0);
        editor.putInt("wins", wins + 1);
        editor.apply();
    }

    void addLoss() {
        int losses = prefs.getInt("losses", 0);
        editor.putInt("losses", losses + 1);
        editor.apply();
    }

    void addTie() {
        int ties = prefs.getInt("ties", 0);
        editor.putInt("ties", ties + 1);
        editor.apply();
    }
    boolean checkSame(int i, int j)
    {
        return board[i] == board[j];
    }

    boolean notN(int i, int j, int k)
    {
        return board[i] != 'n' && board[j] != 'n' && board[k] != 'n';
    }

    boolean isN(int i)
    {
        return board[i] == 'n';
    }

    boolean checkVertWin()
    {
        if(checkSame(0,3) && checkSame(3,6) && notN(0,3,6))
        {

            return true;
        }
        else if(checkSame(1,4) && checkSame(4,7) && notN(1,4,7))
        {
            return true;
        }
        else if(checkSame(2,5) && checkSame(5,8) && notN(2,5,8))
        {
            return true;
        }
        return false;
    }
    boolean checkHorzWin()
    {
        if(checkSame(0,1) && checkSame(1,2) && notN(0,1,2))
        {
            return true;
        }
        else if(checkSame(3,4) && checkSame(4,5) && notN(3,4,5))
        {
            return true;
        }
        else if(checkSame(6,7) && checkSame(7,8) && notN(6,7,8))
        {
            return true;
        }
        return false;
    }
    boolean checkDiagWin()
    {
        if(checkSame(0,4) && checkSame(4,8) && notN(0,4,8))
        {
            return true;
        }
        else if(checkSame(2,4) && checkSame(4,6) && notN(2,4,6))
        {
            return true;
        }
        return false;
    }
    void checkForWin(char winner) {
        if (!game) return; // prevent double counting
        boolean win = checkDiagWin() || checkVertWin() || checkHorzWin();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = "";
        if (win) {
            game = false;
            correctnswer.start();
            if (winner == 'x') addWin();
            else addLoss();
            message = winner + " Wins!!!";
        } else if (isBoardFull()) {
            game = false;
            wrong.start();
            addTie();
            message = "Tie game, nobody wins";
        }
        builder.setTitle(message);
        builder.setMessage("What would you like to do now?");
        builder.setCancelable(false);
        builder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });
        builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent game = new Intent(TicTacToeActivity.this, TicTacToeMenuActivity.class);
                startActivity(game);
            }
        });
        if (win || isBoardFull())
            builder.show();
    }
    void restartGame()
    {
        for(int j = 0; j < 9; j++)
            board[j] = 'n';
        turn = true;
        game = true;
        computerTurn = false;
        p1.setTextColor(Color.BLACK);
        p2.setTextColor(Color.BLACK);
        setImageNull(A);
        setImageNull(B);
        setImageNull(C);
        setImageNull(D);
        setImageNull(E);
        setImageNull(F);
        setImageNull(G);
        setImageNull(H);
        setImageNull(I);
        wins = prefs.getInt("wins", 0);
        losses = prefs.getInt("losses", 0);
        ties = prefs.getInt("ties", 0);
        if(againstwho.equals("two"))
            highScr.setVisibility(View.INVISIBLE);
        else
        {
            highScr.setVisibility(View.VISIBLE);
            highScr.setText("Wins: " + wins + "\nLosses: " + losses + "\nTies: " + ties);
        }
    }
    void setPlayerColor(int p)
    {
        if(p != 1)
        {
            p1.setTextColor(Color.WHITE);
            p2.setTextColor(Color.BLACK);
        }
        else
        {
            p2.setTextColor(Color.WHITE);
            p1.setTextColor(Color.BLACK);
        }
    }

    void turn(int spot, ImageView letter)
    {
        if (board[spot] == 'n' && game == true) {
            if (turn) {
                board[spot] = 'x';
                letter.setImageResource(R.drawable.x);
                checkForWin('x');
                setPlayerColor(1);
                turn = false;
            } else {
                board[spot] = 'o';
                letter.setImageResource(R.drawable.o);
                checkForWin('o');
                setPlayerColor(2);
                turn = true;
            }
        }
    }

    int canWin(int vert, int horz, int diag)
    {
        if (vert != -1)
        {
            return vert;
        }
        else if (horz != -1)
        {
            return horz;
        }
        else if (diag != -1)
        {
            return diag;
        }
        else
        {
            return -1;
        }
    }

    int checkVertWin(char type)
    {
        // column 0
        if(board[0] == type && board[3] == type && isN(6))
            return 6;
        if(board[0] == type && board[6] == type && isN(3))
            return 3;
        if(board[3] == type && board[6] == type && isN(0))
            return 0;

        // column 1
        if(board[1] == type && board[4] == type && isN(7))
            return 7;
        if(board[1] == type && board[7] == type && isN(4))
            return 4;
        if(board[4] == type && board[7] == type && isN(1))
            return 1;

        // column 2
        if(board[2] == type && board[5] == type && isN(8))
            return 8;
        if(board[2] == type && board[8] == type && isN(5))
            return 5;
        if(board[5] == type && board[8] == type && isN(2))
            return 2;
        return -1;
    }

    int checkHorzWin(char type)
    {
        // row 0
        if(board[0] == type && board[1] == type && isN(2))
            return 2;
        if(board[0] == type && board[2] == type && isN(1))
            return 1;
        if(board[1] == type && board[2] == type && isN(0))
            return 0;

        // row 1
        if(board[3] == type && board[4] == type && isN(5))
            return 5;
        if(board[3] == type && board[5] == type && isN(4))
            return 4;
        if(board[4] == type && board[5] == type && isN(3))
            return 3;

        // row 2
        if(board[6] == type && board[7] == type && isN(8))
            return 8;
        if(board[6] == type && board[8] == type && isN(7))
            return 7;
        if(board[7] == type && board[8] == type && isN(6))
            return 6;
        return -1;
    }

    int checkDiagWin(char type)
    {
        // main diagonal
        if(board[0] == type && board[4] == type && isN(8))
            return 8;
        if(board[0] == type && board[8] == type && isN(4))
            return 4;
        if(board[4] == type && board[8] == type && isN(0))
            return 0;

        // anti diagonal
        if(board[2] == type && board[4] == type && isN(6))
            return 6;
        if(board[2] == type && board[6] == type && isN(4))
            return 4;
        if(board[4] == type && board[6] == type && isN(2))
            return 2;

        return -1;
    }

    void computerTurn()
    {
        //Win if possible
        int vertc = checkVertWin('x');
        int horzc = checkHorzWin('x');
        int diagc = checkDiagWin('x');
        int wcVDH = canWin(vertc,horzc,diagc);

        int vertp = checkVertWin('o');
        int horzp = checkHorzWin('o');
        int diagp = checkDiagWin('o');
        int wpVDH = canWin(vertp, horzp, diagp);
        String m = "";
        if (wcVDH != -1)
        {
            turnCTranslate(wcVDH);
            computerTurn = false;
        }
        //Block the opponent's winning move
        else if (wpVDH != -1)
        {
            turnCTranslate(wpVDH);
            computerTurn = false;
        }
        // Take the center if free
        else if (isN(4))
        {
            turnCTranslate(4);
            computerTurn = false;
        }
        // Take the opposite corner
        else if (board[0] == 'o' && isN(8))
        {
            turnCTranslate(8);
            computerTurn = false;
        }
        else if (board[2] == 'o' && isN(6))
        {
            turnCTranslate(6);
            computerTurn = false;
        }
        else if (board[6] == 'o' && isN(2))
        {
            turnCTranslate(2);
            computerTurn = false;
        }
        else if (board[8] == 'o' && isN(0))
        {
            turnCTranslate(0);
            computerTurn = false;
        }
        // Take any corner
        else if (isN(0))
        {
            turnCTranslate(0);
            computerTurn = false;
        }
        else if (isN(2))
        {
            turnCTranslate(2);
            computerTurn = false;
        }
        else if (isN(6))
        {
            turnCTranslate(6);
            computerTurn = false;
        }
        else if (isN(8))
        {
            turnCTranslate(8);
            computerTurn = false;
        }
        // Take any side
        else if (isN(1))
        {
            turnCTranslate(1);
            computerTurn = false;
        }
        else if (isN(3))
        {
            turnCTranslate(3);
            computerTurn = false;
        }
        else if (isN(5))
        {
            turnCTranslate(5);
            computerTurn = false;
        }
        else if (isN(7))
        {
            turnCTranslate(7);
            computerTurn = false;
        }
    }

    void turnCTranslate(int spot)
    {
        if(spot == 0)
        {
            turn(spot, A);
        }
        else if(spot == 1)
        {
            turn(spot, B);
        }
        else if(spot == 2)
        {
            turn(spot, C);
        }
        else if(spot == 3)
        {
            turn(spot, D);
        }
        else if(spot == 4)
        {
            turn(spot, E);
        }
        else if(spot == 5)
        {
            turn(spot, F);
        }
        else if(spot == 6)
        {
            turn(spot, G);
        }
        else if(spot == 7)
        {
            turn(spot, H);
        }
        else if(spot == 8)
        {
            turn(spot, I);
        }
    }

    boolean isBoardFull() {
        for (char c : board) {
            if (c == 'n') return false;
        }
        return true;
    }

    void setImageNull(ImageView l)
    {
        l.setImageResource(0);
    }
    protected void onDestroy() {
        super.onDestroy();
        if (correctnswer != null) correctnswer.release();
        if (wrong != null) wrong.release();
    }
}
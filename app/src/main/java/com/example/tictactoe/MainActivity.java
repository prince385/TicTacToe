package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0-> X => Player1 && 1->0 => Player2

    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    int count = 0;

    String player1 = "Player1";
    String player2 = "Player2";


    @SuppressLint("SetTextI18n")
    public void onTap(View view) {

        ImageView img = (ImageView) view;
        int tappedInt = Integer.parseInt(img.getTag().toString());
        TextView status = findViewById(R.id.status);


        if (gameState[tappedInt] == 2 && gameActive) {
            gameState[tappedInt] = activePlayer;
            count = count + 1;
            if (count == 1) {
                turnPlayAgainBtnInvisible();
                turnResetBtnInvisible();
            }

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.e);
                activePlayer = 1;
                status.setText(player2 + getString(R.string.turn));
                img.animate().rotationBy(180f).setDuration(100);

            } else {
                img.setImageResource(R.drawable.d);
                activePlayer = 0;
                status.setText(player1 + getString(R.string.turn));
            }

            for (int[] winPosition : winningPos) {
                if (gameState[winPosition[0]] != 2 && gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]) {
                    String winner;
                    if (gameState[winPosition[0]] == 0) {
                        winner = player1 + getString(R.string.hasWon);
                        activePlayer = 0;
                        TextView score1 = findViewById(R.id.score1);
                        int x = Integer.parseInt(score1.getText().toString());
                        score1.setText(String.valueOf(++x));
                    } else {
                        winner = player2 + getString(R.string.hasWon);
                        activePlayer = 1;
                        TextView score2 = findViewById(R.id.score2);
                        int x = Integer.parseInt(score2.getText().toString());
                        score2.setText(String.valueOf(++x));
                    }
                    turnPlayAgainBtnVisible();
                    turnResetBtnVisible();

                    status.setTextColor(Color.parseColor("7ADCF5"));
                    status.setText(winner);
                    gameActive = false;
                } else if (count == 9 && gameActive) {
                    status.setText("Draw!");
                    gameActive = false;
                    turnPlayAgainBtnVisible();
                    turnResetBtnVisible();
                }
            }
            if (activePlayer == 0)
                activePlayer1();
            else activePlayer2();
        }
    }

    public void playAgain(View view) {
        gameActive = true;

        turnPlayAgainBtnInvisible();
        turnResetBtnInvisible();

        count = 0;
        TextView status = findViewById(R.id.status);
        status.setText(R.string.Status);
        String str;
        if (activePlayer == 0) {
            str = player1 + getString(R.string.turn);
            status.setText(str);
        } else {
            str = player2 + getString(R.string.turn);
            status.setText(str);
        }
        status.setTextColor(Color.WHITE);
        Arrays.fill(gameState, 2);
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void reset(View view) {
        TextView score1 = findViewById(R.id.score1);
        TextView score2 = findViewById(R.id.score2);

        activePlayer = 0;
        activePlayer1();

        score1.setText(R.string._0);
        score2.setText(R.string._0);

        playAgain(view);
    }

    public void turnPlayAgainBtnVisible() {
        Button btn = findViewById(R.id.play);
        btn.setVisibility(btn.VISIBLE);
    }

    public void turnPlayAgainBtnInvisible() {
        Button btn = findViewById(R.id.play);
        btn.setVisibility(btn.GONE);
    }

    public void turnResetBtnVisible() {
        Button btn = findViewById(R.id.reset);
        btn.setVisibility(btn.VISIBLE);
    }

    public void turnResetBtnInvisible() {
        Button btn = findViewById(R.id.reset);
        btn.setVisibility(btn.GONE);
    }

    public void activePlayer1() {
        TextView p1 = findViewById(R.id.Player1);
        TextView s1 = findViewById(R.id.score1);

        TextView p2 = findViewById(R.id.Player2);
        TextView s2 = findViewById(R.id.score2);

        p1.setTextColor(Color.parseColor("#7ADCF5"));
        s1.setTextColor(Color.parseColor("#7ADCF5"));

        p2.setTextColor(Color.parseColor("#FFFFFFFF"));
        s2.setTextColor(Color.parseColor("#FFFFFFFF"));
    }

    public void activePlayer2() {
        TextView p1 = findViewById(R.id.Player1);
        TextView s1 = findViewById(R.id.score1);

        TextView p2 = findViewById(R.id.Player2);
        TextView s2 = findViewById(R.id.score2);

        p1.setTextColor(Color.parseColor("#FFFFFFFF"));
        s1.setTextColor(Color.parseColor("#FFFFFFFF"));

        p2.setTextColor(Color.parseColor("#7ADCF5"));
        s2.setTextColor(Color.parseColor("#7ADCF5"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
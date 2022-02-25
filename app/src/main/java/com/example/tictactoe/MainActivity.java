package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0-> X => Player1 && 1->0 => Player2
    //Colors Used
//    grid-> 7ADCF5	5AC4EC	319EDF
//    background-> 072A40

    public static String keyScore1 = "com.example.tictactoe.score1";
    public static String keyScore2 = "com.example.tictactoe.score2";

    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    int count = 0;

//    String player1 = ((TextView)findViewById(R.id.Player1)).getText().toString();
//    String player2 = ((TextView)findViewById(R.id.Player2)).getText().toString();
//    String player1 = "Player";
//    String player2 = "Player";

    @SuppressLint("SetTextI18n")
    public void onTap(View view) {
        String player1 = ((TextView)findViewById(R.id.Player1)).getText().toString();
        String player2 = ((TextView)findViewById(R.id.Player2)).getText().toString();

        ImageView img = (ImageView) view;
        int tappedInt = Integer.parseInt(img.getTag().toString());
        TextView status = findViewById(R.id.status);

        if (gameState[tappedInt] == 2 && gameActive) {
            gameState[tappedInt] = activePlayer;
            count = count + 1;
            if (count == 1) {
                turnPlayAgainBtnInvisible();
                turnFinishBtnInvisible();
            }

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                status.setText(player2 + getString(R.string.turn));
                img.animate().rotationBy(180f).setDuration(100);

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                status.setText(player1 + getString(R.string.turn));
            }

            for (int[] winPosition : winningPos) {
                if (gameState[winPosition[0]] != 2 && gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]) {
                    String winner;
                    if (gameState[winPosition[0]] == 0) {
                        winner = player1 + " " + getString(R.string.hasWon);
                        activePlayer = 0;
                        TextView score1 = findViewById(R.id.score1);
                        int x = Integer.parseInt(score1.getText().toString());
                        score1.setText(String.valueOf(++x));
                    } else {
                        winner = player2 + " " + getString(R.string.hasWon);
                        activePlayer = 1;
                        TextView score2 = findViewById(R.id.score2);
                        int x = Integer.parseInt(score2.getText().toString());
                        score2.setText(String.valueOf(++x));
                    }
                    turnPlayAgainBtnVisible();
                    turnFinishBtnVisible();

                    status.setTextColor(Color.parseColor("#7ADCF5"));
                    status.setText(winner);
                    gameActive = false;
                }
            }
            if (count == 9 && gameActive) {
                status.setText("Draw!");
                gameActive = false;
                turnPlayAgainBtnVisible();
                turnFinishBtnVisible();
            }

            if (activePlayer == 0)
                activePlayer1();
            else activePlayer2();
        }
    }

    public void playAgain(View view) {
        gameActive = true;
        String player1 = ((TextView)findViewById(R.id.Player1)).getText().toString();
        String player2 = ((TextView)findViewById(R.id.Player2)).getText().toString();

        turnPlayAgainBtnInvisible();
        turnFinishBtnInvisible();

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

//    public void reset(View view) {
//        TextView score1 = findViewById(R.id.score1);
//        TextView score2 = findViewById(R.id.score2);
//
//        activePlayer = 0;
//        activePlayer1();
//
//        score1.setText(R.string._0);
//        score2.setText(R.string._0);
//
//        playAgain(view);
//    }

    public void turnPlayAgainBtnVisible() {
        Button btn = findViewById(R.id.play);
        btn.setVisibility(btn.VISIBLE);
    }

    public void turnPlayAgainBtnInvisible() {
        Button btn = findViewById(R.id.play);
        btn.setVisibility(btn.GONE);
    }

    public void turnFinishBtnVisible() {
        Button btn = findViewById(R.id.finish);
        btn.setVisibility(btn.VISIBLE);
    }

    public void turnFinishBtnInvisible() {
        Button btn = findViewById(R.id.finish);
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

    public void onFinish(View view) {
        int score1 = Integer.parseInt(((TextView) findViewById(R.id.score1)).getText().toString());
        int score2 = Integer.parseInt(((TextView) findViewById(R.id.score2)).getText().toString());
        String player1 = ((TextView) findViewById(R.id.Player1)).getText().toString();
        String player2 = ((TextView) findViewById(R.id.Player2)).getText().toString();

        Intent terminate = new Intent(MainActivity.this,Result.class);

        terminate.putExtra(PlayerInfo.keyPlayer1,player1);
        terminate.putExtra(PlayerInfo.keyPlayer2,player2);
        terminate.putExtra(keyScore1,score1);
        terminate.putExtra(keyScore2,score2);

        startActivity(terminate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String player1 = intent.getStringExtra(PlayerInfo.keyPlayer1);
        String player2 = intent.getStringExtra(PlayerInfo.keyPlayer2);

        TextView player1Name = (TextView) findViewById(R.id.Player1);
        TextView player2Name = (TextView) findViewById(R.id.Player2);

        player1Name.setText(player1);
        player2Name.setText(player2);

//        Button finish = (Button) findViewById(R.id.finish);
//        finish.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                int score1 = Integer.parseInt(((TextView) findViewById(R.id.score1)).getText().toString());
//                int score2 = Integer.parseInt(((TextView) findViewById(R.id.score2)).getText().toString());
//
//                Intent terminate = new Intent(MainActivity.this,Result.class);
//
//                terminate.putExtra(PlayerInfo.keyPlayer1,player1);
//                terminate.putExtra(PlayerInfo.keyPlayer2,player2);
//                terminate.putExtra(keyScore1,score1);
//                terminate.putExtra(keyScore2,score2);
//
//                startActivity(terminate);
//            }
//        });
    }
}
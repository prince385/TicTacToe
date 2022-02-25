package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    public void playAgain(View view){
        Intent intent = new Intent(Result.this,PlayerInfo.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String player1 = intent.getStringExtra(PlayerInfo.keyPlayer1);
        String player2 = intent.getStringExtra(PlayerInfo.keyPlayer2);

        int score1 = intent.getIntExtra(MainActivity.keyScore1,0);
        int score2 = intent.getIntExtra(MainActivity.keyScore2,0);

        ((TextView) findViewById(R.id.resultPlayer1)).setText(player1);
        ((TextView) findViewById(R.id.resultPlayer2)).setText(player2);
        ((TextView) findViewById(R.id.resultScore1)).setText(String.valueOf(score1));
        ((TextView) findViewById(R.id.resultScore2)).setText(String.valueOf(score2));

        String winnerText = "";
        String point = " points";
        if(Math.abs(score1 - score2) == 1)
            point = " point";

        if(score1 > score2)
            winnerText = player1 + " Has Won " + player2 + " By " + (score1 - score2) + point;
        else if(score1 < score2)
            winnerText = player2 + " Has Won " + player1 + " By " + (score2 - score1) + point;
        else winnerText = "Oh! It's a Draw...";

        ((TextView) findViewById(R.id.winner)).setText(winnerText);

    }
}
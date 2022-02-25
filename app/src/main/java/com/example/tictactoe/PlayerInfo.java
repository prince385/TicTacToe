package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerInfo extends AppCompatActivity {

    public static String keyPlayer1 = "com.example.tictactoe.Player1";
    public static String keyPlayer2 = "com.example.tictactoe.Player2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        Button play = (Button) findViewById(R.id.playNow);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String player1 = ((EditText) findViewById(R.id.editTextPlayer1Name)).getText().toString();
                String player2 = ((EditText) findViewById(R.id.editTextPlayer2Name)).getText().toString();

                if(player1.isEmpty())
                    player1 = getString(R.string.player1);
                if(player2.isEmpty())
                    player2 = getString(R.string.player2);

                Intent intent = new Intent(PlayerInfo.this,MainActivity.class);
                intent.putExtra(keyPlayer1,player1);
                intent.putExtra(keyPlayer2,player2);
                startActivity(intent);
            }
        });
    }
}
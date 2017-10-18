package com.example.bauyrzhan.calculator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartMenuActivity extends AppCompatActivity {

    private static final int REQ_CODE_SECOND_ACTIVITY = 111;
    String word, meaning;
    MediaPlayer welcomeSound;
    int soundCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        welcomeSound = MediaPlayer.create(this, R.raw.gta_4_loading_screen);
        welcomeSound.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        welcomeSound.pause();
        soundCurrentPosition = welcomeSound.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        welcomeSound.seekTo(soundCurrentPosition);
        welcomeSound.start();
    }

    public void playGame(View view) {
        Intent intent = new Intent(this, Dictionary.class);
        intent.putExtra("Word", word);
        intent.putExtra("Meaning", meaning);
        startActivity(intent);
    }

    public void goToAddWord(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivityForResult(intent, REQ_CODE_SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_SECOND_ACTIVITY) {
            if(resultCode == RESULT_OK) {
                //If word and its meaning were added from the AddWordActivity, then they are written to dictionary.txt
                word = data.getStringExtra("Word");
                meaning = data.getStringExtra("Meaning");
            }
        }
    }

}

package com.example.bauyrzhan.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordActivity extends AppCompatActivity {

    EditText editTextForWord, editTextForMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        editTextForWord = (EditText) findViewById(R.id.editTextForWord);
        editTextForMeaning = (EditText) findViewById(R.id.editTextForMeaning);
    }

    public void addWord(View view) {
        if(editTextForMeaning.getText().length() != 0 && editTextForWord.getText().length() != 0) {
            Intent intent = new Intent();
            String word = editTextForWord.getText().toString(), meaning = editTextForMeaning.getText().toString();
            intent.putExtra("Word", word);
            intent.putExtra("Meaning", meaning);
            setResult(RESULT_OK, intent);
            finish();
        }
        else
            Toast.makeText(this, "Please, give full details", Toast.LENGTH_SHORT).show();
    }
}

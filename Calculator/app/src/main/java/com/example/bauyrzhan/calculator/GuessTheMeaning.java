package com.example.bauyrzhan.calculator;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Random;
        import java.util.Scanner;

public class GuessTheMeaning extends Activity {

    TextView word;
    ListView listView;
    String fileName = "android.resource://com.example.bauyrzhan.calculator/raw/dictionary.txt";
    Activity act;
    final ArrayList<String> words = new ArrayList<String>();
    final ArrayList<String> meanings = new ArrayList<String>();

    public void changeWord() {
        Random rand = new Random();
        word.setText(words.get(rand.nextInt(words.size())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_meaning);
        word = (TextView) findViewById(R.id.word);
        listView = (ListView) findViewById(R.id.meanings);
        Scanner scan = new Scanner("~/Desktop/5 semester/Android/HW/Lab 3/dictionary.txt");
        int counter = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();

            if(counter < 10) {
                words.add(line);
            }
            else {
                meanings.add(line);
            }
            counter++;
        }
        scan.close();

        changeWord();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meanings);
        listView.setAdapter(arrayAdapter);

        act = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView) view;
                for(int i=0;i<meanings.size();i++) {
                    if(item.getText().equals(meanings.get(i))) {
                        if(word.getText().equals(words.get(i))) {
                            Toast.makeText(act, "Correct", Toast.LENGTH_SHORT).show();
                            changeWord();
                            break;
                        }
                        else {
                            Toast.makeText(act, "Incorrect", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            }
        });

    }


    public void nextWord(View view) {
        changeWord();
    }
}

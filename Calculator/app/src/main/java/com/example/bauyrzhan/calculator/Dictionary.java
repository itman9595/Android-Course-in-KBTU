package com.example.bauyrzhan.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Bauyrzhan on 9/6/17.
 */

public class Dictionary extends Activity {

    ListView listView;
    Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        listView = (ListView) findViewById(R.id.words);

        InputStream is = getResources().openRawResource(R.raw.dictionary);
        Scanner scan = new Scanner(is);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String verification = sharedPref.getString("dictionary", "Data exists");

        //This step is required to read all predefined data from raw/dictionary.txt, because any data located in res or raw folder cannot be overwritten
        if(verification.length() == 0) {
            SharedPreferences.Editor prefsEditor = sharedPref.edit();
            prefsEditor.putString("dictionary", "Data exists");
            prefsEditor.apply();

            PrintStream output = null;
            try {
                output = new PrintStream(openFileOutput("dictionary.txt", MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while(scan.hasNextLine()) {

                String line = scan.nextLine();
                output.println(line);

            }
            output.close();
            scan.close();
        }

        //Process of reading data from dictionary.txt begins now
        try {
            scan = new Scanner(openFileInput("dictionary.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> words = new ArrayList<String>();
        final ArrayList<String> meanings = new ArrayList<String>();
        boolean allWordsAdded = false;

        //Get a word and its meaning from the Intent
        Intent intent = getIntent();
        String word = intent.getStringExtra("Word"), meaning = intent.getStringExtra("Meaning");
        boolean dataExists = false;
        if(word != null && meaning != null && word.length() != 0 && meaning.length() != 0) {
            dataExists = true;
        }

        String allText = "";
        while(scan.hasNextLine()) {

            String line = scan.nextLine();

            if(line.length() != 0) {
                if(dataExists)
                    allText += line + "\n";
                if(!allWordsAdded)
                    words.add(line);
                else
                    meanings.add(line);
            }

            if(line == "\n" || line == "\r" || line == null || line.length() == 0) {
                if(dataExists) {
                    words.add(word);
                    allText += word + "\n\n";
                }
                allWordsAdded = true;
            }

        }

        if(dataExists) {
            meanings.add(meaning);
            allText += meaning;
        }

        scan.close();

        //This is required in case if a new word and its meaning are added from AddWordActivity
        PrintStream output = null;
        if(dataExists) {
            try {
                output = new PrintStream(openFileOutput("dictionary.txt", MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //Overwriting dictionary.txt with received data
        if(dataExists) {
            try {
                output = new PrintStream(getApplicationContext().openFileOutput("dictionary.txt", MODE_PRIVATE));
                output.print(allText);
                output.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            intent.removeExtra("Word");
            intent.removeExtra("Meaning");
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        listView.setAdapter(arrayAdapter);

        act = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(act, Words_Meaning.class);
                intent.putExtra("meaning", meanings.get(position));
                startActivity(intent);
            }
        });

    }

}

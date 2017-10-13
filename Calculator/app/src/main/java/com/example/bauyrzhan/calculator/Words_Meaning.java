package com.example.bauyrzhan.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Bauyrzhan on 9/6/17.
 */

public class Words_Meaning extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_meaning_in_dictionary);
        TextView txtV = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        String extra = intent.getStringExtra("meaning");
        txtV.setText(extra);
    }

    public void back(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}

package com.example.bauyrzhan.calculator;

import android.text.format.DateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileLocalization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_localization);
        TextView localizedBirthDate = (TextView) findViewById(R.id.localizedBirthDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(localizedBirthDate.getText().toString());
        } catch (ParseException e) {}

        android.icu.text.DateFormat df = android.icu.text.DateFormat.getDateInstance(java.text.DateFormat.LONG, Locale.getDefault());
        localizedBirthDate.setText(df.format(date));
    }

}

package com.example.bauyrzhan.calculator;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static android.R.attr.data;
import static android.R.attr.height;
import static android.R.attr.width;

public class WorldFlagsGameActivity extends AppCompatActivity {

    TextView tv;
    TableLayout table;
    TableRow row1, row2, row3;
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String[] countries;
    List<Integer> linksToCountryIcons;
    List<Integer> imageResources;
    int originCountryPos = 0;

    public static boolean checkForDuplicates(List<Integer> flags) {
        Set inputSet = new HashSet(flags);
        if(inputSet.size()< flags.size())
            return true;
        return false;
    }

    void shuffleCountries() {
        imageResources = new ArrayList<Integer>();
        int originCountryPosInTable = randomNumberGenerator(9);
        for (int i=0;i<9;i++) {
            if(i == originCountryPosInTable)
                imageResources.add(linksToCountryIcons.get(originCountryPos));
            else {
                Integer imgData = linksToCountryIcons.get(randomNumberGenerator(linksToCountryIcons.size()));
                while(imgData == linksToCountryIcons.get(originCountryPos)) {
                    imgData = linksToCountryIcons.get(randomNumberGenerator(linksToCountryIcons.size()));
                }
                imageResources.add(imgData);
                while(checkForDuplicates(imageResources)) {
                    imageResources.remove(imageResources.size()-1);
                    imgData = linksToCountryIcons.get(randomNumberGenerator(linksToCountryIcons.size()));
                    while(imgData == linksToCountryIcons.get(originCountryPos)) {
                        imgData = linksToCountryIcons.get(randomNumberGenerator(linksToCountryIcons.size()));
                    }
                    imageResources.add(imgData);
                }
            }
        }

        btn1.setImageResource(imageResources.get(0));
        btn2.setImageResource(imageResources.get(1));
        btn3.setImageResource(imageResources.get(2));
        btn4.setImageResource(imageResources.get(3));
        btn5.setImageResource(imageResources.get(4));
        btn6.setImageResource(imageResources.get(5));
        btn7.setImageResource(imageResources.get(6));
        btn8.setImageResource(imageResources.get(7));
        btn9.setImageResource(imageResources.get(8));
    }

    int randomNumberGenerator(int range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }

    void invokeDialog(View v) {
        ImageButton imgBtn = (ImageButton) v;
        AlertDialog.Builder builder = new AlertDialog.Builder(WorldFlagsGameActivity.this);
        Drawable drawable = imgBtn.getDrawable();
        builder.setIcon(drawable);
        for(int i=0;i<linksToCountryIcons.size();i++) {
            int id = linksToCountryIcons.get(i);
            if (drawable.getConstantState().equals(getResources().getDrawable(id).getConstantState())) {
                if(linksToCountryIcons.get(originCountryPos) == id)
                    builder.setTitle("Correct");
                else
                    builder.setTitle("Incorrect");
                switch (id) {
                    case R.drawable.germany: {
                        builder.setMessage("It is a federal parliamentary republic in central-western Europe");
                    }
                        break;
                    case R.drawable.austria: {
                        builder.setMessage("It is a federal republic and a landlocked country of over 8.7 million people in Central Europe");
                    }
                        break;
                    case R.drawable.sweden: {
                        builder.setMessage("officially the Kingdom of Sweden, is a Scandinavian country in Northern Europe");
                    }
                        break;
                    case R.drawable.switzerland: {
                        builder.setMessage("officially the Swiss Confederation, is a federal republic in Europe.");
                    }
                        break;
                    case R.drawable.norway: {
                        builder.setMessage("officially the Kingdom of Norway, is a sovereign state and unitary monarchy whose territory comprises the western portion of the Scandinavian Peninsula plus the remote island of Jan Mayen and the archipelago of Svalbard.");
                    }
                        break;
                    case R.drawable.denmark: {
                        builder.setMessage("Officially the Kingdom of Denmark, is a Nordic country and a sovereign state");
                    }
                        break;
                    case R.drawable.netherlands: {
                        builder.setMessage("Also known informally as Holland, is a densely populated country in Western Europe, also incorporating three island territories in the Caribbean");
                    }
                        break;
                    case R.drawable.united_kingdom: {
                        builder.setMessage("commonly known as the United Kingdom (UK) and colloquially Great Britain (GB) or simply Britain, is a sovereign country in western Europe");
                    }
                        break;
                    case R.drawable.france: {
                        builder.setMessage("Officially the French Republic, is a country whose territory consists of metropolitan France in western Europe, as well as several overseas regions and territories");
                    }
                        break;
                    case R.drawable.spain: {
                        builder.setMessage("Officially the Kingdom of Spain, is a sovereign state located on the Iberian Peninsula in southwestern Europe, with two large archipelagoes, the Balearic Islands in the Mediterranean Sea and the Canary Islands off the North African Atlantic coast, two cities, Ceuta and Melilla, in the North African mainland and several small islands in the Alboran Sea near the Moroccan coast");
                    }
                        break;
                    case R.drawable.portugal: {
                        builder.setMessage("Officially the Portuguese Republic, is a sovereign state located on the Iberian Peninsula in southwestern Europe");
                    }
                        break;
                    case R.drawable.italy: {
                        builder.setMessage("Officially the Italian Republic, is a unitary parliamentary republic in Europe");
                    }
                        break;
                    default:
                        break;
                }
                break;
            }
        }

        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    originCountryPos = randomNumberGenerator(countries.length);
                    tv.setText(countries[originCountryPos]+"?");
                    shuffleCountries();
                }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_flags_game);

        countries = getResources().getStringArray(R.array.countries);
        linksToCountryIcons = new ArrayList<Integer>(Arrays.asList(
                R.drawable.germany,
                R.drawable.austria,
                R.drawable.sweden,
                R.drawable.switzerland,
                R.drawable.norway,
                R.drawable.denmark,
                R.drawable.netherlands,
                R.drawable.united_kingdom,
                R.drawable.france,
                R.drawable.spain,
                R.drawable.portugal,
                R.drawable.italy));

        tv = new TextView(this);
        originCountryPos = randomNumberGenerator(countries.length);
        tv.setText(countries[originCountryPos]+"?");
        tv.setTextSize(20);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 20, 0, 0); //right top left bottom
        tv.setLayoutParams(params);
        tv.setId(R.id.tvForCountry);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.world_flags_game_layout);
        layout.addView(tv);

        row1 = new TableRow(this);
        row2 = new TableRow(this);
        row3 = new TableRow(this);

        btn1 = new ImageButton(this);
        btn1.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row1.addView(btn1);
        btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn2 = new ImageButton(this);
        btn2.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row1.addView(btn2);
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn3 = new ImageButton(this);
        btn3.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row1.addView(btn3);
        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn4 = new ImageButton(this);
        btn4.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row2.addView(btn4);
        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn5 = new ImageButton(this);
        btn5.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row2.addView(btn5);
        btn5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn6 = new ImageButton(this);
        btn6.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn6.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row2.addView(btn6);
        btn6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn7 = new ImageButton(this);
        btn7.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn7.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row3.addView(btn7);
        btn7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn8 = new ImageButton(this);
        btn8.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn8.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row3.addView(btn8);
        btn8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        btn9 = new ImageButton(this);
        btn9.setLayoutParams(new TableRow.LayoutParams(300, 300));
        btn9.setScaleType(ImageView.ScaleType.CENTER_CROP);
        row3.addView(btn9);
        btn9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                invokeDialog(v);
            }
        });

        table = (TableLayout) findViewById(R.id.countriesTL);
        RelativeLayout.LayoutParams tableParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableParams.addRule(RelativeLayout.BELOW, tv.getId());
        tableParams.setMargins(0, 100, 0, 0); //right top left bottom
        table.setLayoutParams(tableParams);
        table.setGravity(Gravity.CENTER_HORIZONTAL);
        table.addView(row1, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        table.addView(row2, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        table.addView(row3, new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        shuffleCountries();
    }
}

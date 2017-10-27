package com.example.bauyrzhan.calculator;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Scanner;

public class LocalDatabase extends AppCompatActivity {

    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_local_database);

        SQLiteDatabase db = openOrCreateDatabase("local_DB", MODE_PRIVATE, null);
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.pizza));
        String query = "";
        while (scan.hasNextLine()) { //build and execute queries
            query += scan.nextLine() + "\n";
            if (query.trim().endsWith(";")) {
                db.execSQL(query);
                query = "";
            }
        }

        persons = new ArrayList<Person>();

        Cursor cr = db.rawQuery("Select name, age, gender from person", null);
        if (cr.moveToFirst()) {
            while (cr.moveToNext()) {
                String name = cr.getString(cr.getColumnIndex("name"));
                int age = cr.getInt(cr.getColumnIndex("age"));
                String gender = cr.getString(cr.getColumnIndex("gender"));
                persons.add(new Person(name, age, gender));
            }
            cr.close();
        }

        GridView gridView = (GridView) findViewById(R.id.gridviewDB);
        PersonsAdapter personsAdapter = new PersonsAdapter(this, persons);
        gridView.setAdapter(personsAdapter);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Database");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(persons.size());

        DataPoint[] dps = new DataPoint[persons.size()];

        for (int i=0;i<persons.size();i++) {
            dps[i] = new DataPoint(i, persons.get(i).getAge());
        }

        LineGraphSeries <DataPoint> series = new LineGraphSeries <DataPoint>(dps);

        /*graph.getViewport().getGridLabelRenderer().setLabelFormatter (new DefaultLabelFormatter() {
            @Override
            public  String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    if(value == Math.floor(value) && !Double.isInfinite(value)) {
                        int v = (int)value;
                        return persons.get(v).getName();
                    } else {
                        return "";
                    }
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });*/


        graph.addSeries(series);

    }

    public static class PersonsAdapter extends BaseAdapter {

        private final Context mContext;
        private final ArrayList<Person> persons;

        public PersonsAdapter(Context context, ArrayList<Person> persons) {
            this.mContext = context;
            this.persons = persons;
        }

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Person person = persons.get(position);

            if(convertView == null) {
                final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                convertView = layoutInflater.inflate(R.layout.linearlayout_person, null);
            }

            final TextView textViewName = (TextView)convertView.findViewById(R.id.textview_person_name);
            final TextView textViewAge = (TextView)convertView.findViewById(R.id.textview_person_age);
            final TextView textViewGender = (TextView)convertView.findViewById(R.id.textview_person_gender);

            textViewName.setText(person.getName());
            textViewAge.setText(Integer.toString(person.getAge()));
            textViewGender.setText(person.getGender());
            return convertView;
        }
    }
}

package com.example.bauyrzhan.calculator;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class PillTracker extends AppCompatActivity {

    ArrayList<Prescription> prescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_tracker);

        prescriptions = new ArrayList<>();

        InputStream inputStream = getResources().openRawResource(R.raw.prescriptions);
        XmlToJson xmlToJson = new XmlToJson.Builder(inputStream, null).build();
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // convert to a JSONObject
        JSONObject jsonObject = xmlToJson.toJson();
        try {
            JSONArray pills = jsonObject.getJSONObject("pills").getJSONArray("pill");
            for (int i = 0; i < pills.length(); i++) {
                JSONObject pill = pills.getJSONObject(i);
                Prescription p = new Prescription(
                        pill.getString("name"),
                        pill.getString("dose"),
                        pill.getString("quantity"),
                        pill.getString("frequency"),
                        pill.getString("duration"));
                prescriptions.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final CustomAdapter customAdapter = new CustomAdapter();
        ListView catListView = (ListView) findViewById(R.id.prescriptionsListView);
        catListView.setAdapter(customAdapter);
        final PillTracker pill = this;

        IntentFilter filter = new IntentFilter();
        filter.addAction("calendarIsBound");
        this.registerReceiver(new PillTracker.PillPrescriptionReceiver(), filter);

        catListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PrescriptionService.class);
                intent.setAction("BindThePrescriptionToCalendar");
                intent.putExtra("name", prescriptions.get(position).getName());
                intent.putExtra("dose", prescriptions.get(position).getDose());
                startService(intent);
            }
        });
    }

    public class PillPrescriptionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                final String action = intent.getAction();
                if (action.equals("calendarIsBound")) {
                    Notification.Builder builder = new Notification.Builder(getApplicationContext()).setContentTitle(intent.getStringExtra("name")).setContentText(intent.getStringExtra("dose")).setAutoCancel(true);
                    Notification notification = builder.build();
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(111, notification);
                }
            }
        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return prescriptions.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.prescription_list_custom_layout, null);

            TextView pillName = (TextView)view.findViewById(R.id.pillName);
            TextView pillQuantity = (TextView)view.findViewById(R.id.pillQuantity);
            TextView pillDose = (TextView)view.findViewById(R.id.pillDose);
            TextView pillFrequency = (TextView)view.findViewById(R.id.pillFrequency);
            TextView pillDuration = (TextView)view.findViewById(R.id.pillDuration);

            pillName.setText(prescriptions.get(i).getName());
            pillQuantity.setText(prescriptions.get(i).getQuantity());
            pillDose.setText(prescriptions.get(i).getDose());
            pillFrequency.setText(prescriptions.get(i).getFrequency());
            pillDuration.setText(prescriptions.get(i).getDuration());

            return view;

        }

    }

}

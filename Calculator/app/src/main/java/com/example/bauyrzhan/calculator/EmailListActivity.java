package com.example.bauyrzhan.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class EmailListActivity extends AppCompatActivity {

    static ArrayList<MessageInfo> emails = new ArrayList<MessageInfo>();
    private static final int REQ_CODE_SECOND_ACTIVITY = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_list);

        ArrayList<String> dates = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.dates)));
        ArrayList<String> titles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.titles)));
        final ArrayList<String> identifications = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.identifications)));
        ArrayList<String> senders = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.senders)));
        ArrayList<String> subjects = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.subjects)));

        for(int i = 0; i < getResources().getStringArray(R.array.identifications).length; i++) {
            MessageInfo emailInfo = new MessageInfo(dates.get(i), titles.get(i), identifications.get(i), senders.get(i), subjects.get(i));
            emails.add(emailInfo);
        }

        final CustomAdapter customAdapter = new CustomAdapter();
        final ListView listView = (ListView) findViewById(R.id.emailListView);
        listView.setAdapter(customAdapter);

        RadioButton sortByDateRadioBtn = (RadioButton) findViewById(R.id.sortByDateRadioBtn);
        RadioButton sortByTitleRadioBtn = (RadioButton) findViewById(R.id.sortByTitleRadioBtn);

        sortByDateRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(emails, Collections.reverseOrder());
                listView.setAdapter(customAdapter);
            }
        });

        sortByTitleRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(emails, new Comparator<MessageInfo>() {
                    @Override
                    public int compare(MessageInfo m1, MessageInfo m2) {
                        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
                    }
                });
                listView.setAdapter(customAdapter);
            }
        });

        final Activity act = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(act, EmailDetailsActivity.class);
                intent.putExtra("email_identification", emails.get(position).getID());
                startActivityForResult(intent, REQ_CODE_SECOND_ACTIVITY);
            }
        });
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return emails.size();
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

            view = getLayoutInflater().inflate(R.layout.email_list_custom_layout, null);
            TextView txtSender = (TextView)view.findViewById(R.id.textSender);
            TextView txtSubject = (TextView)view.findViewById(R.id.textSubject);

            txtSender.setText(emails.get(i).getSender());
            txtSubject.setText(emails.get(i).getSubject());
            return view;

        }
    }
}

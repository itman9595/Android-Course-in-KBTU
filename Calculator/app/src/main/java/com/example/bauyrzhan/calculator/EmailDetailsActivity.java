package com.example.bauyrzhan.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class EmailDetailsActivity extends AppCompatActivity {

    private static final int REQ_CODE_SECOND_ACTIVITY = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_details);

        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("email_identification"));

        TextView emailSender = (TextView)findViewById(R.id.emailSenderTextView);
        String[] senders = getResources().getStringArray(R.array.senders);
        emailSender.setText(senders[id-1]);

        TextView emailCC = (TextView)findViewById(R.id.emailCCTextView);
        String[] copy_carbons = getResources().getStringArray(R.array.copy_carbons);
        emailCC.setText(copy_carbons[id-1]);

        TextView emailSubject = (TextView)findViewById(R.id.emailSubjectTextView);
        String[] subjects = getResources().getStringArray(R.array.subjects);
        emailSubject.setText(subjects[id-1]);

        TextView emailContent = (TextView)findViewById(R.id.emailContentTextView);
        emailContent.setText(R.string.email_content);
    }

    public void goBackToEmailsActivity(View view) {
        finish();
    }
}

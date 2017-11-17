package com.example.bauyrzhan.calculator;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SMS_Send extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__send);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.
                PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS}, 111);
        }
    }

    public void sendMessage(View view) {
        SmsManager mgr = SmsManager.getDefault();
        mgr.sendTextMessage("5556", null, ((EditText)findViewById(R.id.messageSendEditText)).getText().toString(), null, null);

    }
}

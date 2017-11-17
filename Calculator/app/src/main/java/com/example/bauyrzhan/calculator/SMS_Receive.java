package com.example.bauyrzhan.calculator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Bauyrzhan on 11/8/17.
 */

public class SMS_Receive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage [] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        String msgText = "";
        for(int i = 0; i < msgs.length; i++) {
            String smsBody = msgs[i].getMessageBody().toString();
            String address = msgs[i].getOriginatingAddress();
            msgText += "SMS From: " + address + "\n" + smsBody + "\n";
        }
        Toast.makeText(context, msgText, Toast.LENGTH_SHORT).show();
    }
}

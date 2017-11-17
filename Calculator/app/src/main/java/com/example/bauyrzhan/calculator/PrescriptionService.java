package com.example.bauyrzhan.calculator;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.provider.CalendarContract;
import android.provider.CalendarContract.*;

import java.util.Calendar;

public class PrescriptionService extends IntentService {

    public PrescriptionService() {
        super("PrescriptionService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals("BindThePrescriptionToCalendar")) {
                Log.i("Act", "Begin");
                Log.i("act", intent.getStringExtra("name"));
                Log.i("act", intent.getStringExtra("dose"));
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(beginTime.get(Calendar.YEAR), beginTime.get(Calendar.MONTH), beginTime.get(Calendar.DAY_OF_MONTH), beginTime.get(Calendar.HOUR_OF_DAY), beginTime.get(Calendar.MINUTE));
                Calendar endTime = Calendar.getInstance();
                endTime.set(beginTime.get(Calendar.YEAR), beginTime.get(Calendar.MONTH), beginTime.get(Calendar.DAY_OF_MONTH), beginTime.get(Calendar.HOUR_OF_DAY)+1, beginTime.get(Calendar.MINUTE));
                Intent calendar_intent = new Intent(Intent.ACTION_INSERT).setData(Events.CONTENT_URI).putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis()).putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis()).putExtra(Events.TITLE, "Take your " + intent.getStringExtra("name")).putExtra(Events.DESCRIPTION, "Dose: " + intent.getStringExtra("dose")).putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
                startActivity(calendar_intent);
                Log.i("Act", "Finished");
/*
                Intent broadcastIntent = new Intent().putExtra("name", intent.getStringExtra("name")).putExtra("dose", intent.getStringExtra("dose")).setAction("calendarIsBound");
                sendBroadcast(broadcastIntent);
                Log.i("Act", "broadcast");*/
            }
        }
    }
}


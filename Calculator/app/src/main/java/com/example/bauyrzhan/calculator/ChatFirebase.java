package com.example.bauyrzhan.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFirebase extends AppCompatActivity {

    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_firebase);
        // Write a message to the database
        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference table = fb.child("db/friends");

        /*DatabaseReference me = table.push();
        me.child("name").setValue("Bauyrzhan");
        me.child("date").setValue("25 Oct 2017 14:05");
        me.child("message").setValue("Hello, dude!");

        DatabaseReference friend = table.push();
        friend.child("name").setValue("Olzhas");
        friend.child("date").setValue("25 Oct 2017 14:07");
        friend.child("message").setValue("Hi, pal!");

        DatabaseReference me1 = table.push();
        me1.child("name").setValue("Bauyrzhan");
        me1.child("date").setValue("25 Oct 2017 14:08");
        me1.child("message").setValue("Have you seen a trailer to \"Outlast 2\"?");

        DatabaseReference friend1 = table.push();
        friend1.child("name").setValue("Olzhas");
        friend1.child("date").setValue("25 Oct 2017 14:10");
        friend1.child("message").setValue("Yeah, the game seems to be awesome :D");
        */

        Query query = table.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                persons = new ArrayList<Person>();

                for (DataSnapshot c : dataSnapshot.getChildren()) {
                    persons.add(new Person(c.child("name").getValue().toString() + " wrote at " + c.child("date").getValue().toString(), 0, c.child("message").getValue().toString()));
                }

                ListView listView = (ListView) findViewById(R.id.firebaseLV);
                LocalDatabase.PersonsAdapter personsAdapter = new LocalDatabase.PersonsAdapter(getApplicationContext(), persons);
                listView.setAdapter(personsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

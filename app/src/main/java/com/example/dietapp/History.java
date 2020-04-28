package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    ListView myListview;
    ArrayList myArrayList= new ArrayList<>();//Creating Array List
    DatabaseReference dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        final ArrayAdapter<String> MyArrayAdapter= new ArrayAdapter<String>(History.this,android.R.layout.simple_list_item_1,myArrayList);
        myListview=findViewById(R.id.listviewMealHistory);//List View
        myListview.setAdapter(MyArrayAdapter);

        dbReference = FirebaseDatabase.getInstance().getReference("SetMealForTheDay");

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override

            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Calendar mealCalendar = Calendar.getInstance();
                String mealsCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(mealCalendar.getTime());
                String childValue1=dataSnapshot.child("id").getValue(String.class);
                String childValue2=dataSnapshot.child("protienSM").getValue(String.class);
                String childValue3=dataSnapshot.child("carbsSM").getValue(String.class);
                String childValue4=dataSnapshot.child("fatsSM").getValue(String.class);

                int n1 = Integer.parseInt(childValue2);
                int n2 = Integer.parseInt(childValue3);
                int n3 = Integer.parseInt(childValue4);

                myArrayList.add("On "+childValue1+" Total Value of PCF: "+(n1+n2+n3));

                MyArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MyArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

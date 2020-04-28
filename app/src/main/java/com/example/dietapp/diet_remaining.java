package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class diet_remaining extends AppCompatActivity {
Button btnBack,displayRMG;
Button btnQuit;
TextView rmgPTN,rmgCBS,rmgFTS;
    DatabaseReference dbRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dbRefrence= FirebaseDatabase.getInstance().getReference("RemainingMeal");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_remaining);
        Calendar mealCalendar = Calendar.getInstance();
        final String mealsCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(mealCalendar.getTime());

        rmgPTN = findViewById(R.id.RgProtien);
        rmgCBS = findViewById(R.id.RgRCarbs);
        rmgFTS = findViewById(R.id.RgFats);

        displayRMG=findViewById(R.id.btnDisplayrmg);
        displayRMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietRemaining();
            }
        });
        btnBack= (Button)findViewById(R.id.btnBkEnterMeal);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(diet_remaining.this,"Remaining Diet Added For the Day",Toast.LENGTH_LONG).show();
                startActivity(new Intent(diet_remaining.this,HomePage.class));
                new diet_remaining().finish();
            }
        });

        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new diet_remaining().finish();
            }
        });
    }

    public void dietRemaining(){
        dbRefrence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                int childValue1=dataSnapshot.child("d1").getValue(Integer.class);
                int childValue2=dataSnapshot.child("d2").getValue(Integer.class);
                int childValue3=dataSnapshot.child("d3").getValue(Integer.class);

                rmgPTN.setText(String.valueOf(childValue1));
                rmgCBS.setText(String.valueOf(childValue2));
                rmgFTS.setText(String.valueOf(childValue3));



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
    @Override
    protected void onStart() {
        super.onStart();
        dietRemaining();

    }
}

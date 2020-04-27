package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class set_diet_pcf extends AppCompatActivity {
    Button btnSubmitNext;
    Button btnLgout,btnDisplay;
    FirebaseAuth authFirebase;
    DatabaseReference databaseDietAPPDB;
    FirebaseUser cUser;
    String CurrentUserID;
    String mDateTime;
    private FirebaseAuth.AuthStateListener authAuthstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diet_pcf);

       /* Bundle sumBundle=getIntent().getExtras();

        int intPtn=sumBundle.getInt("ptnKey");
        int intCbs=sumBundle.getInt("cbsKey");
        int intFts=sumBundle.getInt("ftsKey");

        TextView textView = findViewById(R.id.textViewTotalMeal);
        textView.setText("Total:"+(intPtn+intFts+intCbs));*/
        Calendar mealCalendar = Calendar.getInstance();
        String mealsCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(mealCalendar.getTime());
        mDateTime = mealsCurrentDate;

        btnSubmitNext= (Button)findViewById(R.id.btnDietAdd);
        btnLgout=(Button)findViewById(R.id.btnLogout);
        btnDisplay=findViewById(R.id.btnDisplayDiet);

        btnSubmitNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(set_diet_pcf.this,diet_remaining.class));
                new set_diet_pcf().finish();
                System.exit(0);

            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMeal();
            }
        });
        btnLgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authFirebase.getInstance().signOut();
                Intent intSignup= new Intent(set_diet_pcf.this,Login_Signup.class);
                startActivity(intSignup);
            }
        });
        }
    public void totalMeal()

    {

        databaseDietAPPDB = FirebaseDatabase.getInstance().getReference();
        databaseDietAPPDB.child("SetMealForTheDay").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*String vProtien=dataSnapshot.child(CurrentUserID).child("protienSM").getValue(String.class);
                String vCarbs=dataSnapshot.child(CurrentUserID).child("CarbsSM").getValue(String.class);
                String vFats=dataSnapshot.child(CurrentUserID).child("FatsSM").getValue(String.class);*/
                String vProtien=dataSnapshot.child("protienSM").getValue(String.class);
                String vCarbs=dataSnapshot.child("CarbsSM").getValue(String.class);
                String vFats=dataSnapshot.child("FatsSM").getValue(String.class);
                TextView setTexttest= findViewById(R.id.textViewTotalMeal);

                int intPtn=Integer.parseInt(vProtien);
                int intCbs=Integer.parseInt(vCarbs);
                int intFts= Integer.parseInt(vFats);
                setTexttest.setText(intCbs+intPtn+intFts);


               /* Bundle sumBundle = new Bundle();
                sumBundle.putInt("ptnKey",intPtn);
                sumBundle.putInt("cbsKey",intCbs);
                sumBundle.putInt("ftsKey",intFts);

                Intent intMeal = new Intent(MainActivity.this,set_diet_pcf.class);
                intMeal.putExtras(sumBundle);*/



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    }


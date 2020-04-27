package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText inuptProtien;
    EditText inuptFats;
    EditText inuptCarbs;
    Button btnSubmit;
    TextView setTotalMeal;
    TextView mDateTime;
    FirebaseUser cUser;
    String CurrentUserID;

    DatabaseReference databaseDietAPPDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDietAPPDB= FirebaseDatabase.getInstance().getReference("SetMealForTheDay");

        Calendar mealCalendar = Calendar.getInstance();
        String mealsCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(mealCalendar.getTime());
        mDateTime= (TextView)findViewById(R.id.textViewDate);
        mDateTime.setText(mealsCurrentDate);
        inuptProtien=(EditText)findViewById(R.id.addProtien);
        inuptCarbs=(EditText)findViewById(R.id.addCarbs);
        inuptFats=(EditText)findViewById(R.id.addFats);
        btnSubmit=(Button)findViewById(R.id.Submit);

        setTotalMeal=(TextView)findViewById(R.id.textViewTotalMeal);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //totalMeal();
                Submit();
                startActivity(new Intent(MainActivity.this, set_diet_pcf.class));
                new MainActivity().finish();
                System.exit(0);

            }
        });
    }
    private void Submit()
    {

        String ProtienQuantity=inuptProtien.getText().toString().trim();
        String CarbsQuantity=inuptCarbs.getText().toString().trim();
        String FatsQuantity=inuptFats.getText().toString().trim();

        if(!(ProtienQuantity.isEmpty() && CarbsQuantity.isEmpty() && FatsQuantity.isEmpty())){
            String id=mDateTime.getText().toString().trim();
            SetMeal setMeal= new SetMeal(id,ProtienQuantity,CarbsQuantity,FatsQuantity);
            databaseDietAPPDB.child(id).setValue(setMeal);
            Toast.makeText(this,"Diet Added For the Day",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"You Should Enter Value",Toast.LENGTH_LONG).show();
        }
    }


}

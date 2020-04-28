package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.EventListener;


public class set_diet_pcf extends AppCompatActivity {
    Button btnSubmitNext,btndisplay;
    Button btnLgout;
    EditText inpPTN,inpCBS,inpFTS;
    TextView sumofMeal,pValue,cValue,fValue;
    FirebaseAuth authFirebase;
    DatabaseReference dbRefer;
    FirebaseUser cUser;
    String CurrentUserID;
    String mDateTime;
    int ptnQ,fatsQ,cbsQ,n1,n2,n3;
    private FirebaseAuth.AuthStateListener authAuthstate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diet_pcf);


        Calendar mealCalendar = Calendar.getInstance();
        final String mealsCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(mealCalendar.getTime());
        mDateTime = mealsCurrentDate;

        btnSubmitNext= (Button)findViewById(R.id.btnDietCalculate);
        btndisplay=findViewById(R.id.btnDietTotal);
        btnLgout=(Button)findViewById(R.id.btnLogout);
        inpPTN=findViewById(R.id.inputPrtn);
        inpCBS=findViewById(R.id.inputCarbs);
        inpFTS=findViewById(R.id.inputFats);

        pValue=findViewById(R.id.ptnValue);
        cValue=findViewById(R.id.carbsVlue);
        fValue=findViewById(R.id.fatsValue);
        sumofMeal = findViewById(R.id.textViewTotalMeal);



        //System.out.println(SumDiets);





        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMeal();
            }
        });
        btnSubmitNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String ProtienQ=inpPTN.getText().toString().trim();
                String CarbsQ=inpCBS.getText().toString().trim();
                String FatsQ=inpFTS.getText().toString().trim();
                String intP=pValue.getText().toString().trim();
                String intC=cValue.getText().toString().trim();
                String intF=fValue.getText().toString().trim();
                if(!(ProtienQ.isEmpty() || CarbsQ.isEmpty() || FatsQ.isEmpty()))
                {
                    ptnQ = Integer.parseInt(ProtienQ);
                    fatsQ = Integer.parseInt(FatsQ);
                    cbsQ = Integer.parseInt(CarbsQ);
                    n1 = Integer.parseInt(intP);
                    n2 = Integer.parseInt(intC);
                    n3 = Integer.parseInt(intF);

                    int d1 = (n1 - ptnQ);
                    int d2 = (n2 - cbsQ);
                    int d3 = (n3 - fatsQ);
                    dbRefer = FirebaseDatabase.getInstance().getReference("RemainingMeal");
                    int rmgProtienQuantity = d1;
                    int rmgCarbsQuantity = d2;
                    int rmgFatsQuantity = d3;
                    String id = mealsCurrentDate;
                    SetMeal setMeal = new SetMeal(id, rmgProtienQuantity, rmgCarbsQuantity, rmgFatsQuantity);
                    dbRefer.child(id).setValue(setMeal);
                    Toast.makeText(set_diet_pcf.this, "Remaining Diet Added For the Day", Toast.LENGTH_LONG).show();
                    Intent remainingMeal = new Intent(set_diet_pcf.this, diet_remaining.class);
                    startActivity(remainingMeal);
                }else
                    {
                        inpPTN.setError("Please Enter Valid Value");
                        inpCBS.setError("Please Enter Valid Value");
                        inpFTS.setError("Please Enter Valid Value");
                        Toast.makeText(set_diet_pcf.this,"Pleas Input Values in the Required Fields",Toast.LENGTH_LONG).show();

                }


            }
        });

        btnLgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                authFirebase.getInstance().signOut();
                Intent intSignup= new Intent(set_diet_pcf.this,login.class);
                startActivity(intSignup);

            }
        });
        }
public void currentMeal(){
        dbRefer= FirebaseDatabase.getInstance().getReference("SetMealForTheDay");
    dbRefer.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            String childValue1=dataSnapshot.child("protienSM").getValue(String.class);
            String childValue2=dataSnapshot.child("carbsSM").getValue(String.class);
            String childValue3=dataSnapshot.child("fatsSM").getValue(String.class);

            int n1 = Integer.parseInt(childValue1);
            int n2 = Integer.parseInt(childValue2);
            int n3 = Integer.parseInt(childValue3);

            pValue.setText(String.valueOf(n1));
            cValue.setText(String.valueOf(n2));
            fValue.setText(String.valueOf(n3));
            int sum=(n1+n2+n3);
            sumofMeal.setText(String.valueOf(sum));



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

}


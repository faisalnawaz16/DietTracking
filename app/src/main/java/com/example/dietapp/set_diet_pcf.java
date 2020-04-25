package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class set_diet_pcf extends AppCompatActivity {
    Button btnSubmitNext;
    Button btnLgout;
    FirebaseAuth authFirebase;
    private FirebaseAuth.AuthStateListener authAuthstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diet_pcf);

        Button btnSubmitNext= (Button)findViewById(R.id.btnDietAdd);
        Button btnLgout=(Button)findViewById(R.id.btnLogout);

        btnSubmitNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(set_diet_pcf.this,MainActivity.class));
                new set_diet_pcf().finish();
                System.exit(0);

            }
        });
        btnLgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intSignup= new Intent(set_diet_pcf.this,Login_Signup.class);
                startActivity(intSignup);
            }
        });
        }
    }


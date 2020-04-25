package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class diet_remaining extends AppCompatActivity {
Button btnBack;
Button btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_remaining);

        Button btnBack= (Button)findViewById(R.id.btnBkEnterMeal);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(diet_remaining.this,MainActivity.class));
                new diet_remaining().finish();
                System.exit(0);
            }
        });

        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new diet_remaining().finish();
                System.exit(0);
            }
        });
    }
}

package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText inuptProtien;
Button btnSubmit;

DatabaseReference databaseDietAPPDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDietAPPDB= FirebaseDatabase.getInstance().getReference();
        inuptProtien=(EditText)findViewById(R.id.addProtien);
        btnSubmit=(Button)findViewById(R.id.Submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
                startActivity(new Intent(MainActivity.this, diet_remaining.class));
                new MainActivity().finish();
                System.exit(0);

            }
        });
    }
    private void Submit()
    {
        String ProtienQuantity=inuptProtien.getText().toString().trim();
        if(!TextUtils.isEmpty(ProtienQuantity)){
            String id= databaseDietAPPDB.push().getKey();

            Toast.makeText(this,"Diet Added",Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(this,"You Should Enter Value",Toast.LENGTH_LONG).show();
        }
    }
}

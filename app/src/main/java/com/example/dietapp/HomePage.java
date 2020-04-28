package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth authFirebase;
    DatabaseReference dbRefer;
    private FirebaseAuth.AuthStateListener authAuthstate;
    ListView listViewMeal;
    List<SetMeal> mealList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        dbRefer = FirebaseDatabase.getInstance().getReference("SetMealForTheDay");
        authFirebase=FirebaseAuth.getInstance();
        currentUserInfo();
    }

    public void currentUserInfo(){
        FirebaseUser cUser = authFirebase.getCurrentUser();
        String cUseremail = cUser.getEmail();
        TextView cUserName=findViewById(R.id.loggedUserEmail);
        cUserName.setText(cUseremail);

        findViewById(R.id.btnMainActivity).setOnClickListener(this);
        findViewById(R.id.btnEnterMealEaten).setOnClickListener(this);
        findViewById(R.id.btnCheckRemainig).setOnClickListener(this);
        findViewById(R.id.btnHistory).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMainActivity:
                startActivity(new Intent(HomePage.this,MainActivity.class));
                break;
            case R.id.btnEnterMealEaten:
                startActivity(new Intent(HomePage.this,set_diet_pcf.class));
                break;
            case R.id.btnCheckRemainig:
                startActivity(new Intent(HomePage.this,diet_remaining.class));
                break;
            case R.id.btnHistory:
                startActivity(new Intent(HomePage.this,History.class));
                break;
            case R.id.btnLogoutMain:
                authFirebase.getInstance().signOut();
                Intent initLogin= new Intent(HomePage.this,login.class);
                initLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(initLogin);
                break;

        }
    }
}

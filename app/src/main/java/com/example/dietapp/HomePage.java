package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomePage extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth authFirebase;
    private FirebaseAuth.AuthStateListener authAuthstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        authFirebase=FirebaseAuth.getInstance();

        currentUserInfo();
    }
    @Override
    protected void onStart() {
        super.onStart();
        /*if(authFirebase.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,login.class));*/

        }

        /*authFirebase.addAuthStateListener(authAuthstate);*/

    public void currentUserInfo(){
        FirebaseUser cUser = authFirebase.getCurrentUser();
        String cUseremail = cUser.getEmail();
        TextView cUserName=findViewById(R.id.loggedUserEmail);
        cUserName.setText(cUser.getEmail());

        findViewById(R.id.btnMainActivity).setOnClickListener(this);
        findViewById(R.id.btnEnterMealEaten).setOnClickListener(this);
        findViewById(R.id.btnCheckRemainig).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMainActivity:
                startActivity(new Intent(HomePage.this,MainActivity.class));
                break;
            case R.id.btnBkEnterMeal:
                startActivity(new Intent(HomePage.this,set_diet_pcf.class));
                break;
            case R.id.btnCheckRemainig:
                startActivity(new Intent(HomePage.this,diet_remaining.class));
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

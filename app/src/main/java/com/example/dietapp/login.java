package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth authFirebase;
    EditText emaill,passwordl;
    TextView txtSignUp;
    Button btnLogIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authFirebase = FirebaseAuth.getInstance();
        emaill = findViewById(R.id.lgnEmail);
        passwordl = findViewById(R.id.lgnPassword);
        txtSignUp = (TextView) findViewById(R.id.textViewSignup);
        btnLogIN = findViewById(R.id.lgnButton);
        btnLogIN.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
    }

    private void userLogin(){
        String emailID = emaill.getText().toString().trim();
        String pwd = passwordl.getText().toString().trim();
        if (emailID.isEmpty()) {
            emaill.setError("Please Enter Email");
            emaill.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
            emaill.setError("Please Enter Valid Email");
            emaill.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            passwordl.setError("Enter Password");
            passwordl.requestFocus();
            return;
        }
        if(pwd.length()<6){
            passwordl.setError("Password Must be of 6 Characters");
            passwordl.requestFocus();
            return;
        }

        authFirebase.signInWithEmailAndPassword(emailID,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(login.this,"Sign In Successful",Toast.LENGTH_SHORT).show();
                    Intent homePage=new Intent(login.this,HomePage.class);
                    homePage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homePage);

                }
                else {
                        Toast.makeText(login.this,"Error Occured,Please try Again!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewSignup:
                startActivity(new Intent(login.this,Login_Signup.class));
                break;
                case R.id.lgnButton:
                    userLogin();
                    break;
        }
    }
}

package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login_Signup extends AppCompatActivity implements View.OnClickListener{
    EditText email, password;

    private FirebaseAuth authFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPass);
        authFirebase = FirebaseAuth.getInstance();

        findViewById(R.id.btnSignup).setOnClickListener(this);
        findViewById(R.id.textViewSignIn).setOnClickListener(this);

    }

    private void registerNewUser(){
        String emailID = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if (emailID.isEmpty()) {
            email.setError("Please Enter Email");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
            email.setError("Please Enter Valid Email");
            email.requestFocus();
            return;
        }
        if (pwd.isEmpty()) {
            password.setError("Enter Password");
            password.requestFocus();
            return;
        }
        if(pwd.length()<6){
            password.setError("Password Must be of 6 Characters");
            password.requestFocus();
            return;
        }

            authFirebase.createUserWithEmailAndPassword(emailID,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(Login_Signup.this,"SignUp Successful",Toast.LENGTH_SHORT).show();
                        Intent homePage=new Intent(Login_Signup.this,HomePage.class);
                        homePage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homePage);
                    }
                    else {
                        if(task.getException()instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(Login_Signup.this,"You Are Already Registered",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(Login_Signup.this,"Error Occured,Please try Again!",Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignup:
                registerNewUser();
            break;
            case R.id.textViewSignIn:
            startActivity(new Intent(Login_Signup.this,login.class));
            break;
        }
    }
}

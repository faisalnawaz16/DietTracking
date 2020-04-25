package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Signup extends AppCompatActivity {
EditText email,password;
Button btnSignIN;
TextView txtSignIn;
FirebaseAuth authFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        authFirebase=FirebaseAuth.getInstance();
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPass);
        txtSignIn=findViewById(R.id.textViewSignIn);
        btnSignIN=findViewById(R.id.btnSignup);

        btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID= email.getText().toString();
                String pwd= password.getText().toString();
                if(emailID.isEmpty())
                {
                    email.setError("Please Enter Email");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
                else if(emailID.isEmpty() && pwd.isEmpty())
                {
                    email.setError("Please Enter Email");
                    password.setError("Enter Password");
                    email.requestFocus();
                    password.requestFocus();
                    Toast.makeText(Login_Signup.this,"Fields are Empty",Toast.LENGTH_LONG).show();
                }
                else if(!emailID.isEmpty() && pwd.isEmpty())
                {
                    authFirebase.createUserWithEmailAndPassword(emailID,pwd).addOnCompleteListener(Login_Signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Login_Signup.this,"UnSuccessgull SignUp",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                startActivity(new Intent(Login_Signup.this,set_diet_pcf.class));

                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(Login_Signup.this,"Error Occured",Toast.LENGTH_LONG).show();
                }
            }
        });
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login_Signup.this,login.class));
            }
        });
    }
}

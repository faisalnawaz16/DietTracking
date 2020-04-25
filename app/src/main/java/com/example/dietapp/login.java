package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    FirebaseAuth authFirebase;
    private FirebaseAuth.AuthStateListener authAuthstate;

    EditText emaill,passwordl;
    TextView txtSignUp;
    Button btnLogIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authFirebase=FirebaseAuth.getInstance();
        emaill=findViewById(R.id.lgnEmail);
        passwordl=findViewById(R.id.lgnPassword);
        txtSignUp=(TextView)findViewById(R.id.textViewSignup);
        btnLogIN=findViewById(R.id.lgnButton);

        authAuthstate = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser= authFirebase.getCurrentUser();
                if(currentUser!=null)
                {
                    Toast.makeText(login.this,"You are logged in Successfully..!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(login.this,set_diet_pcf.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(login.this,"Please Log in to Continue",Toast.LENGTH_LONG).show();
                }

            }
        };
        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lgEmailID= emaill.getText().toString();
                String lgPwd= passwordl.getText().toString();
                if(lgEmailID.isEmpty())
                {
                    emaill.setError("Please Enter Error");
                    emaill.requestFocus();
                }
                else if(lgPwd.isEmpty())
                {
                    passwordl.setError("Enter Password");
                    passwordl.requestFocus();
                }
                else if(lgEmailID.isEmpty() && lgPwd.isEmpty())
                {
                    emaill.setError("Please Enter Error");
                    passwordl.setError("Enter Password");
                    emaill.requestFocus();
                    passwordl.requestFocus();
                    Toast.makeText(login.this,"Fields are Empty",Toast.LENGTH_LONG).show();
                }
                else if(!lgEmailID.isEmpty() && lgPwd.isEmpty())
                {
                    authFirebase.signInWithEmailAndPassword(lgEmailID,lgPwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(login.this,"Login Failed/Wrong Credentials",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Intent setDiet= new Intent(login.this,set_diet_pcf.class);
                                startActivity(setDiet);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(login.this,"Please Sign Up to Login",Toast.LENGTH_LONG).show();
                }
            }
        });

       txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Login_Signup.class));
            }
        }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        authFirebase.addAuthStateListener(authAuthstate);
    }
}

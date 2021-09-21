package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    private EditText etpass, etemail;
    private Button etsignup, login;
    String sname, spass, semail;
    private ProgressDialog pb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        pb = new ProgressDialog(signup.this);
        etemail = findViewById(R.id.logemail);
        etpass = findViewById(R.id.logpass);
        etsignup = findViewById(R.id.signup);
        login = findViewById(R.id.login);


        etsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spass = etpass.getText().toString();
                semail = etemail.getText().toString();


                if (!TextUtils.isEmpty(spass) && !TextUtils.isEmpty(semail)) {


                    mAuth.createUserWithEmailAndPassword(semail, spass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "signin is successfull", Toast.LENGTH_LONG).show();
                                pb.setMessage("registering please wait...");
                                pb.show();
                                Intent intent = new Intent(signup.this, login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(signup.this, " the fields cannot be empty", Toast.LENGTH_LONG).show();
                }

                pb.dismiss();
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
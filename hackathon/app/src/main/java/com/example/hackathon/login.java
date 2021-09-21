package com.example.hackathon;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class login extends AppCompatActivity {

    private EditText ema, password,loginname;
    private Button Btn,b;
    private ProgressDialog progressbar;
    String e,p;
    SharedPreferences sp;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        ema = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        b=findViewById(R.id.button);
        loginname=findViewById(R.id.loginname);
        if(mAuth.getCurrentUser() !=null)
        {
            Intent intent= new Intent(login.this,
                    MainActivity.class);
           startActivity(intent);
        }



        // Set on Click Listener on Sign-in button{
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=ema.getText().toString();
                String p=password.getText().toString();
                mAuth.signInWithEmailAndPassword(e,p)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String namelogin=loginname.getText().toString();
                                    sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor e=sp.edit();
                                    e.putString("loginname",namelogin);
                                    e.commit();
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                    Intent intent= new Intent(login.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(login.this,signup.class);

                startActivity(i);
                finish();


            }
        });


    }


}
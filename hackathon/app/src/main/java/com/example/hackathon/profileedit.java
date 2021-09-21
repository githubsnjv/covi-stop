package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class profileedit extends AppCompatActivity {
    EditText e,pname,pemail,pid,pphone;
    Button b1;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit2);
sp= PreferenceManager.getDefaultSharedPreferences(profileedit.this);
        b1=findViewById(R.id.peedsabebtn);
        pemail=findViewById(R.id.peedemail);
        pid=findViewById(R.id.peetcovidid);
        pname=findViewById(R.id.peetname);
        pphone=findViewById(R.id.peetphone);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=pemail.getText().toString();
                String covidid=pid.getText().toString();
                String phonenuber=pphone.getText().toString();
                String email=pemail.getText().toString();
                SharedPreferences.Editor e=sp.edit();
                e.putString("name",name);
                e.putString("covidid",covidid);
                e.putString("phonenumber",phonenuber);
                e.putString("emailid",email);
                e.commit();
                Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
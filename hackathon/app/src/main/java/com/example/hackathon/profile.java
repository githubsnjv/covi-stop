package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    Button b,b1;
    TextView e,name,email,ptvid,ptvphone;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        b=findViewById(R.id.button3);
        email=findViewById(R.id.ptvemail);
        ptvid=findViewById(R.id.pcovidid);
        name=findViewById(R.id.ptvname);
        ptvphone=findViewById(R.id.ptvphone);

        sp= PreferenceManager.getDefaultSharedPreferences(profile.this);


                name.setText(sp.getString("name","name"));
                ptvid.setText(sp.getString("covidid","covidid"));
                ptvphone.setText(sp.getString("phonenumber","phonenuber"));
                email.setText(sp.getString("emailid","email"));



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile.this,profileedit.class);
                startActivity(i);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// This uses android:parentActivityName and
// android.support.PARENT_ACTIVITY meta-data by default
                stackBuilder.addNextIntentWithParentStack(i);
                PendingIntent pendingIntent = stackBuilder
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            }
        });

    }
}
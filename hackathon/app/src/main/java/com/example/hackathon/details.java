package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class details extends AppCompatActivity {

    Button btnadd;
    EditText id, date, place, temp, oxy;
    SharedPreferences sp;
    ListView listView;
    private List l;
    CalendarView cv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        cv=findViewById(R.id.calendarView);
        listView = findViewById(R.id.lv);
        l = new ArrayList();
        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(sp.getString("covidid",""));



                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        String d=dayOfMonth+"d"+month+"m"+year;
                        System.out.println(d);
                        DatabaseReference ref1 = myRef.child(d);
                        ref1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                l.clear();
                                if (snapshot.exists())
                                {

                                    for (DataSnapshot ds : snapshot.getChildren()) {

                                        data up = ds.getValue(data.class);
                                        l.add(up);
                                    }
                                    adapter adpt = new adapter(details.this, l);
                                    listView.setAdapter(adpt);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"NO data exist",Toast.LENGTH_SHORT).show();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getApplicationContext(), "something went wrond", Toast.LENGTH_SHORT).show();
                            }
                        });
                               }
        });
    }
}




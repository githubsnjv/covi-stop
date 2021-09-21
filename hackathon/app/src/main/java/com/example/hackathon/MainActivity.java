package com.example.hackathon;



import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    FirebaseAuth mAuth;
    TextView text,introtext;
    Button b;
    private ImageView qrimg;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView4);
        dl = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        mAuth = FirebaseAuth.getInstance();
        qrimg = findViewById(R.id.imageView2);
        introtext=findViewById(R.id.textView3);
        qrgen();

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.drawer);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.acc) {

                    Intent i = new Intent(MainActivity.this, details.class);
                    startActivity(i);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

// This uses android:parentActivityName and
// android.support.PARENT_ACTIVITY meta-data by default
                    stackBuilder.addNextIntentWithParentStack(i);
                    PendingIntent pendingIntent = stackBuilder
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                    return true;


                }
                if (id == R.id.parameters) {
                    Toast.makeText(MainActivity.this, "parameters",
                            Toast.LENGTH_SHORT).show();

                }
                if (id == R.id.logout) {
                    mAuth.signOut();
                    Intent i = new Intent(MainActivity.this, login.class);
                    startActivity(i);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// This uses android:parentActivityName and
// android.support.PARENT_ACTIVITY meta-data by default
                    stackBuilder.addNextIntentWithParentStack(i);
                    PendingIntent pendingIntent = stackBuilder
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


                }
                if (id == R.id.Profile) {
                    Toast.makeText(MainActivity.this, "profile display",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, profile.class);
                    startActivity(i);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
// This uses android:parentActivityName and
// android.support.PARENT_ACTIVITY meta-data by default
                    stackBuilder.addNextIntentWithParentStack(i);
                    PendingIntent pendingIntent = stackBuilder
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                }


                return true;
            }
        });
        introtext.setText("Hi "+sp.getString("loginname","")+"!");
        String s = "This time is more important time for all of us. by this app we can able to make our observation for covid will be easy." +
                "To know more about this read the instruction in the app";
        text.setText(s);



    }

    protected void qrgen()
    {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        qrimg=findViewById(R.id.imageView2);
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;
        String qr = sp.getString("covidid", "hai");
        System.out.println("value ="+qr)  ;
        if (qr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter the covid id generate QR code", Toast.LENGTH_SHORT).show();
        } else {
            // setting this dimensions inside our qr code
            // encoder to generate our qr code.
            qrgEncoder = new QRGEncoder(qr, null, QRGContents.Type.TEXT, dimen);
            try {
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.encodeAsBitmap();
                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrimg.setImageBitmap(bitmap);
            } catch (WriterException e) {
                // this method is called for
                // exception handling.
                Log.e("Tag", e.toString());
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}



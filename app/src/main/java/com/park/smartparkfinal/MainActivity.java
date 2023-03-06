package com.park.smartparkfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView tracker,parking_list, imageView5, imageView6, imageView3,bluetooth,feedback,parking_status,p_manual,Location_Area,parking1,parking2;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parking2 =(ImageView)findViewById(R.id.parking2);


        parking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("google.navigation:q=20.013345,73.8206902&mode=1"));
                intent.setPackage("com.google.android.apps.maps");

                if(intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                }
            }
        });


        /*bluetooth = (ImageView) findViewById(R.id.bluetooth);
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(HomeActivity.this, bluetooth_page.class);
                startActivity(streamIntent);
            }
        });*/

        tracker = (ImageView) findViewById(R.id.tracker);
        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(MainActivity.this, ParkingInfo.class);
                startActivity(streamIntent);
            }
        });

        //p_manual = (ImageView) findViewById(R.id.p_manual);
        /*p_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(HomeActivity.this, parking_manual.class);
                startActivity(streamIntent);
            }
        });*/

        /*parking1 = (ImageView) findViewById(R.id.parking1);
        parking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(MainActivity.this, Scanner.class);
                startActivity(streamIntent);
            }
        });*/

       /* parking2 = (ImageView) findViewById(R.id.parking2);
        parking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(HomeActivity.this, parking_list_page.class);
                startActivity(streamIntent);
            }
        });*/


       Location_Area = (ImageView) findViewById(R.id.Location_Area);
        Location_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(MainActivity.this, location_area.class);
                startActivity(streamIntent);
            }
        });



        /*feedback = (ImageView) findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(HomeActivity.this, support_contact.class);
                startActivity(streamIntent);
            }
        });*/

        parking_status = (ImageView) findViewById(R.id.parking_status);
        parking_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(MainActivity.this, ContactUs.class);
                startActivity(streamIntent);
            }
        });
        setzero();
    }

    public void setzero() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:a");
        try {
            Date EndTime = dateFormat.parse("12:56:AM");
            Date StartTime = dateFormat.parse("04:00:PM");
            Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));

            if (CurrentTime.equals(StartTime)) {

                firebaseFirestore.collection("Slot1").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    }
                });

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
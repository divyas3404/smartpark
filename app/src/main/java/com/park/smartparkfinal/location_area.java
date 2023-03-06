package com.park.smartparkfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class location_area extends AppCompatActivity {

    ImageView imageView4,imageView10,imageView5,imageView6,imageView9,imageView3;
    FirebaseFirestore fStore;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_area);

        fStore = FirebaseFirestore.getInstance();

        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking.class);
                startActivity(streamIntent);
            }
        });

        /*imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking.class);
                startActivity(streamIntent);
            }
        });*/

        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking3.class);
                startActivity(streamIntent);
            }
        });
        /*imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking.class);
                startActivity(streamIntent);
            }
        });

        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking.class);
                startActivity(streamIntent);
            }
        });*/

        imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(location_area.this, booking2.class);
                startActivity(streamIntent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        fStore.collection("Slot1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                count = documentSnapshots.size();
                if(count==3)
                {
                    imageView4.setImageResource(R.drawable.car1);
                    imageView4.setClickable(false);
                }
            }
        });

        fStore.collection("Slot2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                count = documentSnapshots.size();
                if(count==40)
                {
                    imageView4.setImageResource(R.drawable.car1);
                    imageView4.setClickable(false);
                }
            }
        });


        fStore.collection("Slot3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                count = documentSnapshots.size();
                if(count==40)
                {
                    imageView4.setImageResource(R.drawable.car1);
                    imageView4.setClickable(false);
                }
            }
        });
    }
}
package com.park.smartparkfinal;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class booking3 extends AppCompatActivity {

    TextView error, total;
    EditText vehid, spot, hours ,UPI;
    Button book;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    List<String> spots = new ArrayList<>();

    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking3);
        error = findViewById(R.id.error);
        total = findViewById(R.id.total);
        vehid = findViewById(R.id.vehId);
        spot = findViewById(R.id.spot);
        hours = findViewById(R.id.hours);
        book = findViewById(R.id.book);
        UPI =findViewById(R.id.upi);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        String CurrentUser = fAuth.getCurrentUser().getEmail();






        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vehid.getText().toString().isEmpty() || spot.getText().toString().isEmpty() || hours.getText().toString().isEmpty() || UPI.getText().toString().isEmpty()) {

                    error.setText("* Fill in all the fields");
                }
                else{

                    if(spots.contains(spot.getText().toString())){

                        error.setText("* Spot not available at this time!");
                    }
                    else{
                        spots.add(spot.getText().toString());
                        Map<String,Object> user = new HashMap<>();
                        user.put("Spotno", spot.getText().toString());
                        user.put("Hours", hours.getText().toString());
                        user.put("Total", Double.toString(calculateTotal()));

                        fStore.collection("Slot3").document(CurrentUser).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){

                                    Toast.makeText(booking3.this, "Booked", Toast.LENGTH_LONG).show();
                                    String spotS =spot.getText().toString();
                                    String hoursS = hours.getText().toString();
                                    Intent ii1=new Intent(booking3.this, Qrcode.class);
                                    ii1.putExtra("spot", spotS);
                                    ii1.putExtra("hours" , hoursS);
                                    startActivity(ii1);



                                } else {

                                    String error = task.getException().getMessage();
                                    Toast.makeText(booking3.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                                }

                            }
                        });
                        Toast.makeText(booking3.this,"Booking Confirmed",Toast.LENGTH_SHORT).show();


                        //startActivity(new Intent(BookingActivity.this,DashboardActivity.class));




                    }

                    total.setText("₹"+Double.toString(calculateTotal()));
                }

            }
        });

    }

   /*public void getCount(){

       //String CurrentUser = fAuth.getCurrentUser().getEmail();
        fStore.collection("Slot1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                count = documentSnapshots.size();
                error.setText(Integer.toString(count));
            }
        });
    }*/

    protected double calculateTotal(){
        double i = Double.parseDouble(hours.getText().toString()) * 12.5;
        return i;
    }
}
package com.park.smartparkfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListUser extends AppCompatActivity {


    Toolbar toolBar;
    android.widget.ListView ListView;

    //List View with click item From Firebase Simple Method in android studio Part 2


    FirebaseFirestore db;

    List<String> nameList=new ArrayList<>();
    //List<String> latlangList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        db=FirebaseFirestore.getInstance();

        ListView=(ListView)findViewById(R.id.listview);


        db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                nameList.clear();
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    nameList.add(snapshot.getString("Email"));
                    nameList.add(snapshot.getString("Phone"));
                    //latlangList.add(snapshot.getString("LatLang"));
                }
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item,nameList);
                adapter.notifyDataSetChanged();
                ListView.setAdapter(adapter);
            }
        });





    }
}
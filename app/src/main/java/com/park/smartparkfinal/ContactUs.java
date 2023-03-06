package com.park.smartparkfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ContactUs extends AppCompatActivity {

    EditText subject,message;
    Button submit;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        subject =(EditText)findViewById(R.id.edit_text_subject);
        message =(EditText)findViewById(R.id.edit_text_message);
        submit  =(Button)findViewById(R.id.button_send);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        String CurrentUser = fAuth.getCurrentUser().getEmail();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sub= subject.getText().toString();
                String msg= message.getText().toString();


                Map<String,Object> user = new HashMap<>();
                user.put("Subject" , sub);
                user.put("Message",msg);


                fStore.collection("ContactUs").document(CurrentUser).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(ContactUs.this, "Submited", Toast.LENGTH_LONG).show();


                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(ContactUs.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                        }

                    }
                });


            }
        });
    }
}
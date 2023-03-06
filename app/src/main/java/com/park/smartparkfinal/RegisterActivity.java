package com.park.smartparkfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {
    EditText Email,CPassword,Password,Name,PhoneNumber;
    Button Register;
    TextView LoginLink;
    private FirebaseAuth mAuth;

    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email =(EditText)findViewById(R.id.r_email);
        CPassword =(EditText)findViewById(R.id.r_pass);
        Password =(EditText)findViewById(R.id.r_pass2);
        Register =(Button)findViewById(R.id.r_Register);
        LoginLink =(TextView)findViewById(R.id.log_in);
        Name=(EditText)findViewById(R.id.nameinput);
        PhoneNumber=(EditText)findViewById(R.id.phnumber);

        //String CurrentUser = mAuth.getCurrentUser().getEmail();
        //String user_id = mAuth.getCurrentUser().getUid();

        final String randomName = UUID.randomUUID().toString();

        mAuth = FirebaseAuth.getInstance();
        fStore =FirebaseFirestore.getInstance();

        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setupIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(setupIntent);

            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString();
                String cpassword=CPassword.getText().toString();
                String password=Password.getText().toString();
                String name =Name.getText().toString();
                String phnumber =PhoneNumber.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(cpassword) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phnumber))
                {

                    if(cpassword.equals(password))
                    {
                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {

                                    Map<String,Object> user = new HashMap<>();
                                    user.put("Email", email);
                                    user.put("Password", password);
                                    user.put("Name" ,name);
                                    user.put("Phone" ,phnumber);

                                    fStore.collection("Users").document(randomName).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful())
                                            {
                                                Intent setupIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(setupIntent);
                                                finish();
                                            }else
                                            {
                                                String error = task.getException().getMessage();
                                                Toast.makeText(RegisterActivity.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });



                                }else
                                {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }else
                    {
                        Toast.makeText(RegisterActivity.this, "Confirm Password and Password Field doesn't match.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "All Fields Are Required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
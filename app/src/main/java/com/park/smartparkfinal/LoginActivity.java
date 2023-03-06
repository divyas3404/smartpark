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

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    Button login;
    FirebaseAuth mAuth;
    TextView reg_now, reg_row2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg_now = (TextView) findViewById(R.id.reg_now);
        reg_row2 = (TextView) findViewById(R.id.reg_now2);
        reg_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent streamIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(streamIntent);
            }
        });

        reg_row2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });





        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.l_email);
        pass = findViewById(R.id.l_pass);
        login = findViewById(R.id.l_loginB);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckFun(email, pass);
            }

            private void CheckFun(EditText email, EditText pass) {
                String s_email, s_pass;

                s_email = email.getText().toString();
                s_pass = pass.getText().toString();

                if (TextUtils.isEmpty(s_email) || TextUtils.isEmpty(s_pass)) {
                    Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    LoginFun(s_email, s_pass);
                }
            }

            private void LoginFun(String s_email, String s_pass) {
                mAuth.signInWithEmailAndPassword(s_email, s_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }


}
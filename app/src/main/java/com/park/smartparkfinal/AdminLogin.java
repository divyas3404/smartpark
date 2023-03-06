package com.park.smartparkfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLogin extends AppCompatActivity {
    EditText Email , Pass;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Email =(EditText)findViewById(R.id.l_email);
        Pass =(EditText)findViewById(R.id.l_pass);

        Login =(Button)findViewById(R.id.l_loginB);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =Email.getText().toString();
                String pass = Pass.getText().toString();

                if(email.equals("admin@gmail.com") && pass.equals("123"))
                {
                    Intent intent = new Intent(AdminLogin.this , AdminPanel.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
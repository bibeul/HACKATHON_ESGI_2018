package com.esgi.al.solistrophe.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import al.esgi.com.solistrophe.R;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Button signIn = findViewById(R.id.signInButton);
        Button register = findViewById(R.id.registerButton);

        final EditText login = findViewById(R.id.loginText);
        final EditText password = findViewById(R.id.passwordText);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(login.getText());
                System.out.println(password.getText());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Register.class);
                startActivity(intent);
            }
        });
    }


}

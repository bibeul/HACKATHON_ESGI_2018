package com.esgi.al.solistrophe.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.menu.Menu;
import com.esgi.al.solistrophe.utils.ApiClass;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button create = findViewById(R.id.createButton);
        Button cancel = findViewById(R.id.cancelrButton);

        final EditText password = findViewById(R.id.passwordText);
        final EditText address = findViewById(R.id.addressText);
        final EditText email = findViewById(R.id.emailText);
        final EditText username = findViewById(R.id.usernameText);
        final EditText phone = findViewById(R.id.phoneText);
        final EditText firstname = findViewById(R.id.firstNameText);
        final EditText lastname = findViewById(R.id.lastNamenText);

        final ApiClass apiClass = new ApiClass();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClass.register(firstname.getText().toString(), lastname.getText().toString(),
                        phone.getText().toString(), address.getText().toString(), username.getText().toString(),
                        email.getText().toString(), password.getText().toString());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Register.this, Signin.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Signin.class);
                startActivity(intent);
            }
        });
    }

}

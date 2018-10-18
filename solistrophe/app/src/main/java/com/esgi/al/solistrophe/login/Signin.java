package com.esgi.al.solistrophe.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.menu.Menu;
import com.esgi.al.solistrophe.utils.ApiClass;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Button signIn = findViewById(R.id.signInButton);
        Button register = findViewById(R.id.registerButton);

        final EditText login = findViewById(R.id.loginText);
        final EditText password = findViewById(R.id.passwordText);

        final ApiClass apiClass = new ApiClass();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClass.connection(login.getText().toString(),password.getText().toString());
                System.out.println(apiClass.getAuth());
//                Intent intent = new Intent(Signin.this, Menu.class);
//                startActivity(intent);
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

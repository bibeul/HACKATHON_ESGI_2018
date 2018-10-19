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

import java.io.IOException;

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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (apiClass.getResp() != null) {
                    if(apiClass.getResp().get("id") != null){
                        apiClass.setAuth(apiClass.getResp());
                        System.out.println(apiClass.getAuth());
                        Intent intent = new Intent(Signin.this, Menu.class);
                        startActivity(intent);
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "Login / Password incorrect";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
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

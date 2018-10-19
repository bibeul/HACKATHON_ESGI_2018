package com.esgi.al.solistrophe.Services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.utils.ApiClass;

public class DeclaredService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declared_service);

        final ApiClass apiClass = new ApiClass();

        Button create = findViewById(R.id.createServiceButton);
        Button cancel = findViewById(R.id.cancelServiceButton);

        final EditText name = findViewById(R.id.nameServiceText);
        final EditText description = findViewById(R.id.descriptionServiceText);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClass.declaredService(name.getText().toString(), description.getText().toString());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(DeclaredService.this, Service.class);
                startActivity(intent);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(DeclaredService.this, Service.class);
                startActivity(intent);

            }
        });

    }
}

package com.esgi.al.solistrophe.Sinister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.travijuu.numberpicker.library.NumberPicker;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.utils.ApiClass;

public class DeclaredSinister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declared_sinister);

        final ApiClass apiClass = new ApiClass();

        Button create = findViewById(R.id.createButton);
        Button cancel = findViewById(R.id.cancelrButton);

        final EditText name = findViewById(R.id.nameText);
        final EditText description = findViewById(R.id.descriptionText);
        final NumberPicker severity = findViewById(R.id.severityNumber);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClass.declaredSinister(name.getText().toString(), description.getText().toString(),
                        String.valueOf(severity.getValue()), apiClass.getAuth().get("userId").asText());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(DeclaredSinister.this, Sinister.class);
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
                Intent intent = new Intent(DeclaredSinister.this, Sinister.class);
                startActivity(intent);

            }
        });

    }
}

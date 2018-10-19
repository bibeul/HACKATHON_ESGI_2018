package com.esgi.al.solistrophe.Services;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.esgi.al.solistrophe.R;

import java.util.ArrayList;

public class Service extends AppCompatActivity {

    ArrayList<com.esgi.al.solistrophe.model.Service> datas;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_service);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.declaredService);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Service.this, DeclaredService.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.service_list);

        datas = new ArrayList<>();

        datas.add(new com.esgi.al.solistrophe.model.Service("Nom1", "description1", 0));
        datas.add(new com.esgi.al.solistrophe.model.Service("Nom2", "description2", 1));
        datas.add(new com.esgi.al.solistrophe.model.Service("Nom3", "description3", 2));
        datas.add(new com.esgi.al.solistrophe.model.Service("Nom4", "description4", 3));


        adapter = new CustomAdapter(datas, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.esgi.al.solistrophe.model.Service model = datas.get(position);

                Snackbar.make(view, model.getName()+"\n"+model.getDescription(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

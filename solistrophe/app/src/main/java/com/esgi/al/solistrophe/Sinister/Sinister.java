package com.esgi.al.solistrophe.Sinister;

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

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.utils.ApiClass;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Sinister extends AppCompatActivity {

    ArrayList<com.esgi.al.solistrophe.model.Sinister> datas;
    ListView listView;
    private static CustomAdapter adapter;

    final ApiClass apiClass = new ApiClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinister);

        String userId = apiClass.getAuth().get("userId").asText();
        ApiClass.getAccountInformation(userId);

        FloatingActionButton fab = findViewById(R.id.declaredSinister);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sinister.this, DeclaredSinister.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.sinister_list);
        datas = new ArrayList<>();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ApiClass.findAllMatches(apiClass.getResp().get("location").get("lng").asText(), apiClass.getResp().get("location").get("lat").asText());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (JsonNode jsonnode : apiClass.getResp().get("location")){
            ApiClass.getMatchesSinister(jsonnode.get("id").asText());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(apiClass.getResp());

            for (JsonNode sinister : apiClass.getResp()){
                if( sinister.get("accountId") != apiClass.getAuth().get("userId")){
                    datas.add(new com.esgi.al.solistrophe.model.Sinister(sinister.get("name").asText(), sinister.get("description").asText(), sinister.get("severity").asInt(), sinister.get("state").asInt(), sinister.get("id").asInt(),sinister.get("accountId").asInt()));
                }
            }
        }

        adapter = new CustomAdapter(datas, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.esgi.al.solistrophe.model.Sinister model = datas.get(position);

                Snackbar.make(view, model.getName()+"\n"+model.getDescription()+" STATE :" + model.getState(), Snackbar.LENGTH_LONG)
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

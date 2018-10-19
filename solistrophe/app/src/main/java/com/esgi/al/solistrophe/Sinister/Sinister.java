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
import android.widget.Toolbar;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.login.Register;
import com.esgi.al.solistrophe.login.Signin;
import com.esgi.al.solistrophe.login.Signin;
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ApiClass.findAllMatches(apiClass.getResp().get("location").get("lng").asText(), apiClass.getResp().get("location").get("lat").asText());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (JsonNode jsonnode : apiClass.getResp().get("location")){
            ApiClass.getMatchesSinister(jsonnode.get("id").asText());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(apiClass.getResp().size() != 0){
                datas.add(new com.esgi.al.solistrophe.model.Sinister(apiClass.getResp().get(0).get("name").asText(), apiClass.getResp().get(0).get("description").asText(), apiClass.getResp().get(0).get("severity").asInt(), apiClass.getResp().get(0).get("state").asInt(), apiClass.getResp().get(0).get("id").asInt(),apiClass.getResp().get(0).get("accountId").asInt()));
            }
        }

        //ApiClass.getMatchesServices()

        /*datas.add(new com.esgi.al.solistrophe.model.Sinister("Innondation", "Cave innondé", 3, 0, 1,1997));
        datas.add(new com.esgi.al.solistrophe.model.Sinister("ORAGE", "TONERRE", 0, 1, 2,7991));
        datas.add(new com.esgi.al.solistrophe.model.Sinister("Feu de foret", "Ma maison elle est toute brulée", 2, 2, 3,79491));
        datas.add(new com.esgi.al.solistrophe.model.Sinister("Frizer", "Super mechant", 5, 15, 4,7966591));*/

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

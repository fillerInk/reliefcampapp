package xyz.appmaker.keralarescue.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xyz.appmaker.keralarescue.Adapters.CampRecycleViewAdapter;
import xyz.appmaker.keralarescue.Interfaces.RecycleItemClickListener;
import xyz.appmaker.keralarescue.MainActivity;
import xyz.appmaker.keralarescue.Models.States;
import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;
import xyz.appmaker.keralarescue.Tools.Misc;
import xyz.appmaker.keralarescue.Tools.PreferensHandler;

public class CampsActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<CampNames> campNames = new ArrayList<>();
    RecycleItemClickListener recycleItemClickListener;
    Spinner districtSpinner;
    PreferensHandler pref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        pref = new PreferensHandler(context);
        districtSpinner = (Spinner) findViewById(R.id.spinner_district);
        ArrayAdapter<States> districtAdapter = new ArrayAdapter<States>(this,
                android.R.layout.simple_spinner_item, Misc.getStates());
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        districtSpinner.setAdapter(districtAdapter);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent actField = new Intent(CampsActivity.this, FieldsActivity.class);
               startActivity(actField);
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TO-DO
                // make network call for camp list
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        campNames.add(new CampNames("Camp 1 ",1, "ekm"));
        campNames.add(new CampNames("Camp 2 ",2, "ekm"));
        campNames.add(new CampNames("Camp 3 ",3, "ekm"));
        campNames.add(new CampNames("Camp 4 ",4, "ekm"));

        mRecyclerView = (RecyclerView) findViewById(R.id.camp_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        recycleItemClickListener = new RecycleItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
               // Log.e("TAG","onItem final call"+position);
                Intent fieldIntent = new Intent(CampsActivity.this, FieldsActivity.class);
                startActivity(fieldIntent);
            }
        };
        // specify an adapter (see also next example)
        mAdapter = new CampRecycleViewAdapter(campNames, recycleItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_logout) {
            Toast.makeText(this, "Loging out", Toast.LENGTH_SHORT).show();
            logoutUser();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  void logoutUser(){
        if(pref!= null){
            pref.setUserToken("");
            Intent actLogin = new Intent(CampsActivity.this, MainActivity.class);
            startActivity(actLogin);
        }
    }
}

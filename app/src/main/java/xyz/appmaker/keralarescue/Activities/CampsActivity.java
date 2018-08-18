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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.appmaker.keralarescue.Adapters.CampRecycleViewAdapter;
import xyz.appmaker.keralarescue.AppController;
import xyz.appmaker.keralarescue.Interfaces.RecycleItemClickListener;
import xyz.appmaker.keralarescue.MainActivity;
import xyz.appmaker.keralarescue.Models.States;
import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;
import xyz.appmaker.keralarescue.Room.CampDatabase;
import xyz.appmaker.keralarescue.Tools.APIService;
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
    private APIService apiService;
    private CampDatabase dbInstance;
    String districtSelectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camps);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        pref = new PreferensHandler(context);
        districtSpinner = findViewById(R.id.spinner_district);
        ArrayAdapter<States> districtAdapter = new ArrayAdapter<States>(this,
                android.R.layout.simple_spinner_item, Misc.getStates());
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dbInstance = CampDatabase.getDatabase(context);
        districtSpinner.setAdapter(districtAdapter);
        apiService = AppController.getRetrofitInstance();


        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TO-DO
                // make network call for camp list
                States states = (States) parent.getSelectedItem();
                districtSelectedValue = states.getId();
                updateCamps(districtSelectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.camp_recycler_view);


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
        loadCamps("tvm");
    }


    public void updateCamps(final String district) {
        Call<List<CampNames>> response = apiService.getCampList(authToken(), district);
        response.enqueue(new Callback<List<CampNames>>() {
            @Override
            public void onResponse(Call<List<CampNames>> call, Response<List<CampNames>> response) {
                Log.e("TAG", "success response ");

                List<CampNames> items = response.body();

                if (items != null && items.size() > 0) {
                    String name = items.get(0).getName();
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                    insetCampDb(items);
                }
                loadCamps(district);
            }

            @Override
            public void onFailure(Call<List<CampNames>> call, Throwable t) {
                Log.e("TAG", "fail response ");

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void insetCampDb(final List<CampNames> var) {
        Log.e("TAG", "insetCampDb ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dbInstance.campDao().insertCapms(var);

            }
        }).start();
    }

    public void loadCamps(final String district) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                campNames = dbInstance.campDao().getAllCampsByDistrict(district);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CampRecycleViewAdapter(campNames, recycleItemClickListener);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                });
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camp, menu);
        return true;
    }

    public String authToken() {
        return "JWT " + pref.getUserToken();
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

    public void logoutUser() {
        if (pref != null) {
            pref.setUserToken("");
            Intent actLogin = new Intent(CampsActivity.this, MainActivity.class);
            startActivity(actLogin);
        }
    }
}

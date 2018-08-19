package xyz.appmaker.keralarescue.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.appmaker.keralarescue.AppController;
import xyz.appmaker.keralarescue.Models.UpdateCamp;
import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;
import xyz.appmaker.keralarescue.Tools.APIService;
import xyz.appmaker.keralarescue.Tools.PreferensHandler;

public class RequirementActivity extends AppCompatActivity {

    EditText totalPeopleEditText;
    EditText maleEditText;
    EditText femaleEditText;
    EditText infantsEditText;
    EditText foodEditText;
    EditText clothingEditText;
    EditText sanitaryEditText;
    EditText medicalEditText;
    EditText otherEditText;
    private APIService apiService;
    private PreferensHandler pref;
    String campID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalPeopleEditText = findViewById(R.id.number_people);
        maleEditText = findViewById(R.id.number_males);
        femaleEditText = findViewById(R.id.number_females);
        infantsEditText = findViewById(R.id.number_infants);
        foodEditText = findViewById(R.id.food);
        clothingEditText = findViewById(R.id.clothing);
        sanitaryEditText = findViewById(R.id.sanitary);
        medicalEditText = findViewById(R.id.medical);
        otherEditText = findViewById(R.id.other);
        campID = getIntent().getStringExtra("campId");
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        apiService = AppController.getRetrofitInstance();
        pref = new PreferensHandler(getApplicationContext());

//        campID = "5";
        apiService.getCamp(authToken(), campID).enqueue(new Callback<UpdateCamp>() {
            @Override
            public void onResponse(Call<UpdateCamp> call, Response<UpdateCamp> response) {
                Log.e("TAG","tocken "+authToken());

                if (response.isSuccessful()) {
                    UpdateCamp camp = response.body();
                    maleEditText.setText(camp.total_males);
                    femaleEditText.setText(camp.total_females);
                    infantsEditText.setText(camp.total_infants);
                    foodEditText.setText(camp.food_req);
                    clothingEditText.setText(camp.clothing_req);
                    sanitaryEditText.setText(camp.sanitary_req);
                    medicalEditText.setText(camp.medical_req);
                    otherEditText.setText(camp.other_req);
                }
            }

            @Override
            public void onFailure(Call<UpdateCamp> call, Throwable t) {

            }
        });


    }


    public void updateData(){
        UpdateCamp updateCamp = new UpdateCamp();
        updateCamp.total_people = totalPeopleEditText.getText().toString();
        updateCamp.total_males = maleEditText.getText().toString();
        updateCamp.total_females = femaleEditText.getText().toString();
        updateCamp.total_infants =infantsEditText.getText().toString();
        updateCamp.food_req = foodEditText.getText().toString();
        updateCamp.clothing_req = clothingEditText.getText().toString();
        updateCamp.sanitary_req = sanitaryEditText.getText().toString();
        updateCamp.medical_req = medicalEditText.getText().toString();
        updateCamp.other_req = otherEditText.getText().toString();
        apiService.updateCamp(authToken(), campID, updateCamp).enqueue(new Callback<UpdateCamp>() {
            @Override
            public void onResponse(Call<UpdateCamp> call, Response<UpdateCamp> response) {
                Log.e("TAG","on response update data "+response);

                if (response.isSuccessful()) {
                    Log.e("TAG","on response siccc data "+response);
                   /* UpdateCamp camp = response.body();
                    maleEditText.setText(camp.total_males);
                    femaleEditText.setText(camp.total_females);
                    infantsEditText.setText(camp.total_infants);
                    foodEditText.setText(camp.food_req);
                    clothingEditText.setText(camp.clothing_req);
                    sanitaryEditText.setText(camp.sanitary_req);
                    medicalEditText.setText(camp.medical_req);
                    otherEditText.setText(camp.other_req);*/
                }
            }

            @Override
            public void onFailure(Call<UpdateCamp> call, Throwable t) {
                Log.e("TAG","on Error update data "+t);

            }
        });
    }

    public String authToken() {

        return "JWT " + pref.getUserToken();
    }


}

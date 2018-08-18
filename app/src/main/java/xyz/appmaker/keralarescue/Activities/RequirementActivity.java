package xyz.appmaker.keralarescue.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import xyz.appmaker.keralarescue.R;

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
        foodEditText= findViewById(R.id.food);
        clothingEditText= findViewById(R.id.clothing);
        sanitaryEditText= findViewById(R.id.sanitary);
        medicalEditText= findViewById(R.id.medical);
        otherEditText= findViewById(R.id.other);


    }

}

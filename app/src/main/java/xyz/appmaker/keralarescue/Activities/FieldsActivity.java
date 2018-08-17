package xyz.appmaker.keralarescue.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Tools.PreferensHandler;

public class FieldsActivity extends AppCompatActivity {


    EditText nameEdt, ageEdt, addressEdt, mobileEdt, notesEdt;
    Spinner campNameSpn, genderSpn, districtSpn;
    String[] arrayGender = new String[] {
            "Male", "Female"
    };
    String[] arrayDistricts = new String[] {
            "Thiruvananthapuram", "Kollam", "Pathanamthitta", "Alappuzha", "Kottayam", "Idukki", "Ernakulam", "Thrissur", "Palakkad", "Malappuram", "Kozhikode", "Wayanad", "Kannur", "Kasaragod"
    };
    PreferensHandler pref;
    Button submitBtn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        pref = new PreferensHandler(context);

        // Gender spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayGender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpn = (Spinner) findViewById(R.id.gender);
        genderSpn.setAdapter(genderAdapter);


        genderSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Districts Spinner
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayDistricts);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpn = (Spinner) findViewById(R.id.district);

        districtSpn.setAdapter(districtAdapter);

        if(pref != null)
            districtSpn.setSelection(pref.getDistrictDef());

        districtSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pref != null)
                    pref.setDistrictDef(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        nameEdt = (EditText) findViewById(R.id.name);
        ageEdt = (EditText) findViewById(R.id.age);
        addressEdt = (EditText) findViewById(R.id.address);
        mobileEdt = (EditText) findViewById(R.id.mobile);
        notesEdt = (EditText) findViewById(R.id.note);
        submitBtn = (Button ) findViewById(R.id.submit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){

                }

            }
        });

    }



    public boolean validateData() {
        if(nameEdt.getText().equals("") || ageEdt.getText().equals("") || addressEdt.getText().equals("") || mobileEdt.getText().equals("") || notesEdt.getText().equals("")){
            Toast.makeText(context, "Please enter all fields",
                    Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }


}

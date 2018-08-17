package xyz.appmaker.keralarescue.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.appmaker.keralarescue.Models.States;
import xyz.appmaker.keralarescue.R;
import xyz.appmaker.keralarescue.Room.CampDatabase;
import xyz.appmaker.keralarescue.Room.CampDatabase_Impl;
import xyz.appmaker.keralarescue.Room.PersonData.PersonDataDao;
import xyz.appmaker.keralarescue.Room.PersonData.PersonDataEntity;
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

    HashMap<String, String> distMap = new HashMap<>();
    PreferensHandler pref;
    Button submitBtn;
    Context context;
    private  PersonDataDao personDao;
   // private WordViewModel mWordViewModel;

    CampDatabase dbInstance;

    ArrayList<States>  statesList = new ArrayList<>();

    String stateSelectedValue = "tvm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        pref = new PreferensHandler(context);
        dbInstance = CampDatabase.getDatabase(context);
        // Gender spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayGender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpn = (Spinner) findViewById(R.id.gender);
        genderSpn.setAdapter(genderAdapter);


        genderSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //stateSelectedValue

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Districts Spinner
        statesList.add(new States("tvm","Thiruvananthapuram"));
        statesList.add(new States("kol","Kollam"));
        statesList.add(new States("ptm","Pathanamthitta"));
        statesList.add(new States("alp","Alappuzha"));
        statesList.add(new States("ktm","Kottayam"));
        statesList.add(new States("idk","Idukki"));
        statesList.add(new States("ekm","Ernakulam"));
        statesList.add(new States("tcr","Thrissur"));
        statesList.add(new States("pkd","Palakkad"));
        statesList.add(new States("mpm","Malappuram"));
        statesList.add(new States("koz","Kozhikode"));
        statesList.add(new States("wnd","Wayanad"));
        statesList.add(new States("knr","Kannur"));
        statesList.add(new States("ksr","Kasaragod"));

        ArrayAdapter<States> districtAdapter = new ArrayAdapter<States>(this,
                android.R.layout.simple_spinner_item, statesList);
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

                States states = (States) parent.getSelectedItem();
                stateSelectedValue = states.getId();

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
                Log.e("Tag","state code " + stateSelectedValue);
                if(validateData()){
                    PersonDataEntity personDataModel = new PersonDataEntity(nameEdt.getText().toString(), "123",ageEdt.getText().toString(), "male",
                            addressEdt.getText().toString(),"EKM", mobileEdt.getText().toString(), notesEdt.getText().toString());
                    insetDb(personDataModel);
                }
            }
        });

    }



    public boolean validateData() {
        if(nameEdt.getText().toString().equals("") || ageEdt.getText().toString().equals("") || addressEdt.getText().toString().equals("") ||
                mobileEdt.getText().toString().equals("") || notesEdt.getText().toString().equals("")){
            Toast.makeText(context, "Please enter all fields",
                    Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    public  void insetDb(final PersonDataEntity var){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbInstance.personDataDao().insert(var);
            }
        }) .start();
    }


}

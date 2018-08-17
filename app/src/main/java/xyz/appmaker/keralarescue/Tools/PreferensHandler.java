package xyz.appmaker.keralarescue.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferensHandler {
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context c;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "app_pref";
    final String district_def = "district_def";
    final String camp_def = "camp_def";



    @SuppressLint("CommitPrefEdits")
    public PreferensHandler(Context context) {
        this.c = context;
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDistrictDef(int var){
        editor.putInt(district_def, var);
        editor.commit();
    }

    public int getDistrictDef(){
        return pref.getInt(district_def, 0);
    }
}

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
    final String recent_camp_id = "recent_camp_id";
    final String recent_camp_name = "recent_camp_name";

    final String camp_def = "camp_def";
    final String userTokenKey = "user_token";
    final String user_name = "user_name";



    @SuppressLint("CommitPrefEdits")
    public PreferensHandler(Context context) {
        this.c = context;
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDistrictDef(int var) {
        editor.putInt(district_def, var);
        editor.commit();
    }

    public int getDistrictDef() {
        return pref.getInt(district_def, 0);
    }

    public String getUserToken() {
        return pref.getString(userTokenKey, "");
    }

    public void setUserToken(String userToken) {
        editor.putString(userTokenKey, userToken);
        editor.commit();
    }

    //  Recent camp selection
    public void setRecentCampID(int var) {
        editor.putInt(recent_camp_id, var);
        editor.commit();
    }

    public int getRecentCampID() {
        return pref.getInt(recent_camp_id, -1);
    }

    // Camp details
    public void setRecentCamp(String var) {
        editor.putString(recent_camp_name, var);
        editor.commit();
    }

    public String getRecentCamp() {
        return pref.getString(recent_camp_name, "");
    }


    // Username
    public void setUsername(String var) {
        editor.putString(user_name, var);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(user_name, "");
    }

}

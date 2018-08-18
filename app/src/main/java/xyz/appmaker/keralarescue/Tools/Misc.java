package xyz.appmaker.keralarescue.Tools;

import java.util.ArrayList;
import java.util.List;

import xyz.appmaker.keralarescue.Models.States;

public class Misc {
    static ArrayList<States> statesList = new ArrayList<>();


    public static ArrayList<States> getStates(){
        // Districts Spinner
        statesList.add(new States("-", "-"));
        statesList.add(new States("tvm", "Thiruvananthapuram"));
        statesList.add(new States("kol", "Kollam"));
        statesList.add(new States("ptm", "Pathanamthitta"));
        statesList.add(new States("alp", "Alappuzha"));
        statesList.add(new States("ktm", "Kottayam"));
        statesList.add(new States("idk", "Idukki"));
        statesList.add(new States("ekm", "Ernakulam"));
        statesList.add(new States("tcr", "Thrissur"));
        statesList.add(new States("pkd", "Palakkad"));
        statesList.add(new States("mpm", "Malappuram"));
        statesList.add(new States("koz", "Kozhikode"));
        statesList.add(new States("wnd", "Wayanad"));
        statesList.add(new States("knr", "Kannur"));
        statesList.add(new States("ksr", "Kasaragod"));
        return statesList;
    }


}

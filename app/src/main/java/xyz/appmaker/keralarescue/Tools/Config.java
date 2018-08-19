package xyz.appmaker.keralarescue.Tools;

import java.util.ArrayList;

import xyz.appmaker.keralarescue.Models.District;

public class Config {
//    public static String BASE_URL = "http://192.168.0.20:8000";
 public static String BASE_URL = "http://35.202.24.11";
    //public static String BASE_URL = "https://keralarescue.in";

 static ArrayList<District> districtList = new ArrayList<>();


 public static ArrayList<District> getDistricts() {
  if (districtList.size() > 0)
   return districtList;
  // Districts Spinner
  districtList.add(new District("", "-"));
  districtList.add(new District("tvm", "Thiruvananthapuram"));
  districtList.add(new District("kol", "Kollam"));
  districtList.add(new District("ptm", "Pathanamthitta"));
  districtList.add(new District("alp", "Alappuzha"));
  districtList.add(new District("ktm", "Kottayam"));
  districtList.add(new District("idk", "Idukki"));
  districtList.add(new District("ekm", "Ernakulam"));
  districtList.add(new District("tcr", "Thrissur"));
  districtList.add(new District("pkd", "Palakkad"));
  districtList.add(new District("mpm", "Malappuram"));
  districtList.add(new District("koz", "Kozhikode"));
  districtList.add(new District("wnd", "Wayanad"));
  districtList.add(new District("knr", "Kannur"));
  districtList.add(new District("ksr", "Kasaragod"));
  return districtList;
 }

}

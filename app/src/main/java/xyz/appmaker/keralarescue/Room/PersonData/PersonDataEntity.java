package xyz.appmaker.keralarescue.Room.PersonData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "person_data")
public class PersonDataEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "camp_id")
    private String campId;

    @ColumnInfo(name = "age")
    private String age;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "district")
    private String district;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "note")
    private String note;

    public PersonDataEntity(String name, String campName, String age, String gender, String address, String district, String mobile, String note) {
        this.name = name;
        this.campId = campName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.district = district;
        this.mobile = mobile;
        this.note = note;

    }

    public String getName(){return this.name;}
    public String getCampId(){return this.campId;}
    public String getAge(){return this.age;}
    public String getGender(){return this.gender;}
    public String getAddress(){return this.address;}
    public String getDistrict(){return this.district;}
    public String getMobile(){return this.mobile;}
    public String getNote(){return this.note;}


}

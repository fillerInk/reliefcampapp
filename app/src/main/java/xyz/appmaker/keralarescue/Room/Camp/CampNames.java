package xyz.appmaker.keralarescue.Room.Camp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "camp_names")
public class CampNames {

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "camp_id")
    private int id;

    @NonNull
    @ColumnInfo(name = "district")
    private String district;

    public CampNames(String name, int id, String district) {
        this.name = name;
        this.id = id;
        this.district = district;
    }

    public String getName(){return this.name;}
    public int getId(){return this.id;}

    @Override
    public String toString() {
        return name;
    }

    public String getDistrict() {
        return district;
    }
}

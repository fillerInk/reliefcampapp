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

    public CampNames(String name, int id) {
        this.name = name;
        this.id = id;

    }

    public String getName(){return this.name;}
    public int getId(){return this.id;}

}

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
    private String campId;

    public CampNames(String name, String campId) {
        this.name = name;
        this.campId = campId;

    }

    public String getName(){return this.name;}
    public String getCampId(){return this.campId;}

}

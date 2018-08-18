package xyz.appmaker.keralarescue.Room.Camp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CampNameDao {

    @Insert
    void insert(CampNames names);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCapms(List<CampNames> campNames);

    @Query("DELETE FROM camp_names")
    void deleteAll();

    @Query("SELECT * from camp_names ORDER BY name ASC")
    List<CampNames> getAllCamps();

}

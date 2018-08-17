package xyz.appmaker.keralarescue.Room.PersonData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import xyz.appmaker.keralarescue.Room.Camp.CampNames;

@Dao
public interface PersonDataDao {
    @Insert
    void insert(PersonDataEntity personData);

    @Query("DELETE FROM person_data")
    void deleteAll();

    @Query("SELECT * from person_data ORDER BY id ASC")
    List<PersonDataEntity> getAllPersons();
}

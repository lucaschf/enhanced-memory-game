package tsi.pdmsf.memorygame.repository;

import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tsi.pdmsf.memorygame.model.Scoreboard;

@Dao
public interface ScoreboardRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scoreboard scoreboard);

    @Update
    void update (Scoreboard scoreboard);

    @Delete
    void delete(Scoreboard scoreboard);

    @Query("SELECT * FROM scoreboard")
    List<Scoreboard> findAll();

}//interface ScoreboardRepository

package tsi.pdmsf.memorygame.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tsi.pdmsf.memorygame.model.Scoreboard;
import tsi.pdmsf.memorygame.model.enums.GameLevel;

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

    @Query("DELETE FROM scoreboard")
    void deleteAll();

    @Query("SELECT * FROM scoreboard ORDER BY punctuation DESC, errors ASC, nameUser")
    List<Scoreboard> findByLevel();

    @Query("SELECT * FROM scoreboard WHERE difficulty = :difficultyFilter ORDER BY punctuation DESC, errors ASC, nameUser")
    List<Scoreboard> findAllByDifficulty(GameLevel difficultyFilter);

}//interface ScoreboardRepository

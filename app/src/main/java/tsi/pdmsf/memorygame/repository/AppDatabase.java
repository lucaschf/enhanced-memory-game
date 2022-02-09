package tsi.pdmsf.memorygame.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import tsi.pdmsf.memorygame.model.Scoreboard;

@Database(entities = {Scoreboard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreboardRepository SRDao();
}
package tsi.pdmsf.memorygame.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.jetbrains.annotations.NotNull;

import tsi.pdmsf.memorygame.model.Scoreboard;

@Database(entities = {Scoreboard.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreboardRepository SRDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(@NotNull Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "score.db")
                        .allowMainThreadQueries()
                        .build();
            }
        }

        return INSTANCE;
    }
}
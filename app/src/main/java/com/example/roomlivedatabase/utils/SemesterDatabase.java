package com.example.roomlivedatabase.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomlivedatabase.models.Semester;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Semester.class}, version = 1, exportSchema = false)
public abstract class SemesterDatabase extends RoomDatabase {
    public abstract SemesterDao semesterDao();

    private static volatile SemesterDatabase INSTANCE;   //access variable from any thread respect to any change
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    static SemesterDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SemesterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SemesterDatabase.class, "semester_database")
                            .build();


                }
            }
        }
        return INSTANCE;
    }

}

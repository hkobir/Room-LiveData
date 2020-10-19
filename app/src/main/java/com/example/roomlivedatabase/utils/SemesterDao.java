package com.example.roomlivedatabase.utils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import com.example.roomlivedatabase.models.Semester;

import java.util.List;

@Dao
public interface SemesterDao {
    @Insert
    void insertSemester(Semester semester);

    @Query("UPDATE semester set semesterName =:name, credit =:credit, result =:result WHERE id =:id")
    void updateSemester(int id, String name, double credit, double result);

    @Delete
    void deleteSemester(Semester semester);

    @Query("select * From semester order by semesterName")
    LiveData<List<Semester>> getAllSemester();

    @Query("delete from semester")
    void deleteAll();
}

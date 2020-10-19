package com.example.roomlivedatabase.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomlivedatabase.models.Semester;

import java.util.List;

public class Repository {
    public SemesterDao semesterDao;
    LiveData<List<Semester>> semesters;

    Repository(Application application) {
        SemesterDatabase db = SemesterDatabase.getDatabase(application);
        semesterDao = db.semesterDao();
        semesters = semesterDao.getAllSemester();
    }

    LiveData<List<Semester>> getAllSemester() {
        return semesters;
    }

    void insertSemester(Semester semester) {
        SemesterDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                semesterDao.insertSemester(semester);
            }
        });
    }
    void updateSemester(int id, String name, double credit, double result) {
        SemesterDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                semesterDao.updateSemester(id,name,credit,result);
            }
        });
    }
    void deleteSemester(Semester semester) {
        SemesterDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                semesterDao.deleteSemester(semester);
            }
        });
    }
}

package com.example.roomlivedatabase.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomlivedatabase.models.Semester;

import java.util.List;

public class SemesterViewModel extends AndroidViewModel {
    private Repository semesterRepository;
    private LiveData<List<Semester>> semesters;

    public SemesterViewModel(@NonNull Application application) {
        super(application);
        semesterRepository = new Repository(application);
        semesters = semesterRepository.getAllSemester();
    }

    public LiveData<List<Semester>> getAllSemester() {
        return semesters;
    }

    public void insertSemester(Semester semester) {
        semesterRepository.insertSemester(semester);
    }

    public void deleteSemester(Semester semester) {
        semesterRepository.deleteSemester(semester);
    }

    public void updateSemester(int id, String name, double credit, double result) {
        semesterRepository.updateSemester(id,name,credit,result);
    }
}

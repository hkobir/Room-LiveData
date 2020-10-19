package com.example.roomlivedatabase.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Semester {
    @PrimaryKey(autoGenerate = true)
    int id;
    String semesterName;
    double credit, result;

    public Semester(String semesterName, double credit, double result) {
        this.semesterName = semesterName;
        this.credit = credit;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

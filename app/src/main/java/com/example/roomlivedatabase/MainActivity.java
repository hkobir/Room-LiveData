package com.example.roomlivedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.roomlivedatabase.Adapters.SemesterAdapter;
import com.example.roomlivedatabase.models.Semester;
import com.example.roomlivedatabase.utils.SemesterViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView semesterRV;
    private AppCompatButton addSemesterBtn;
    private SemesterAdapter semesterAdapter;
    private AppCompatTextView gradeResultTV, emptyView;
    List<Semester> semesterList;
    SemesterViewModel semesterViewModel;
    private String gradePoint = "0.0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        semesterRV = findViewById(R.id.semesterRV);
        addSemesterBtn = findViewById(R.id.addSemesterBtn);
        emptyView = findViewById(R.id.emptyView);
        gradeResultTV = findViewById(R.id.gradeTV);

        semesterViewModel = new ViewModelProvider(this).get(SemesterViewModel.class);
        semesterList = new ArrayList<>();


        semesterRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        addSemesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddSemesterActivity.class));
            }
        });

        populateData();

        swipeToDelete();

        //get CGPA


    }


    private void populateData() {
        semesterViewModel.getAllSemester().observe(this, semesters -> {
            semesterList = semesters;

            emptyView.setVisibility(View.GONE);
            semesterAdapter = new SemesterAdapter(MainActivity.this, semesterList);
            semesterRV.setAdapter(semesterAdapter);

            //empty list
            if (semesterList.size() <= 0) {
                emptyView.setVisibility(View.VISIBLE);
            }

            //get grade
            double points = 0;
            for (Semester semester : semesterList) {
                points += semester.getResult();
            }
            gradePoint = String.format("%.2f", (points / semesterList.size()));
            gradeResultTV.setText("CGPA : "+gradePoint);

            Log.d("List Size: ", String.valueOf(semesters.size()));
            Log.d("Grade: ", gradePoint);
        });
    }

    private void swipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT
                | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                deleteSemester(viewHolder.getAdapterPosition());


            }
        }).attachToRecyclerView(semesterRV);
    }

    private void deleteSemester(int position) {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to delete the semester?")
                .setTitle("WARNING!!!")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete function called

                        Semester temp = semesterList.get(position);

                        semesterViewModel.deleteSemester(temp);
                        semesterAdapter.notifyDataSetChanged();

                        dialogInterface.dismiss();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        semesterAdapter.notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }


}
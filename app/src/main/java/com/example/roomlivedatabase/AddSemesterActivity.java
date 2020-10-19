package com.example.roomlivedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roomlivedatabase.models.Semester;
import com.example.roomlivedatabase.utils.SemesterViewModel;

public class AddSemesterActivity extends AppCompatActivity {
    private AppCompatEditText semesterNameET, creditET, resultET;
    private AppCompatButton saveBtn, cancelBtn;
    private SemesterViewModel semesterViewModel;
    private boolean isUpdate = false;
    int semesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        //initialize
        semesterNameET = findViewById(R.id.semesterNameET);
        creditET = findViewById(R.id.semesterCreditET);
        resultET = findViewById(R.id.semesterResultET);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        semesterViewModel = new ViewModelProvider(this).get(SemesterViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null) {

            isUpdate = true;
            saveBtn.setText("Update");

            semesterId = bundle.getInt("id", 0);
            semesterNameET.setText(bundle.getString("name"));
            creditET.setText(String.valueOf(bundle.getDouble("credit",0)));
            resultET.setText(String.valueOf(bundle.getDouble("result",0)));
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (semesterNameET.getText().toString().isEmpty() ||
                        creditET.getText().toString().isEmpty() ||
                        resultET.getText().toString().isEmpty()) {
                    Toast.makeText(AddSemesterActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (isUpdate) {

                        updateSemester();
                    } else {

                        saveSemester();
                    }
//                    Intent intent = new Intent(AddSemesterActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void saveSemester() {
        Semester semester = new Semester(semesterNameET.getText().toString(),
                Double.valueOf(creditET.getText().toString()),
                Double.valueOf(resultET.getText().toString()));
        semesterViewModel.insertSemester(semester);

        Toast.makeText(AddSemesterActivity.this, "Semester added", Toast.LENGTH_SHORT).show();

    }

    private void updateSemester() {
        semesterViewModel.updateSemester(
                semesterId,
                semesterNameET.getText().toString(),
                Double.valueOf(creditET.getText().toString()),
                Double.valueOf(resultET.getText().toString())
        );

        Toast.makeText(AddSemesterActivity.this, "Semester updated", Toast.LENGTH_SHORT).show();

    }
}
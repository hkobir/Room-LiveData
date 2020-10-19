package com.example.roomlivedatabase.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlivedatabase.AddSemesterActivity;
import com.example.roomlivedatabase.R;
import com.example.roomlivedatabase.models.Semester;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {
    Context context;
    List<Semester> semesterList;

    public SemesterAdapter(Context context, List<Semester> semesterList) {
        this.semesterList = semesterList;
        this.context = context;
    }

    @NonNull
    @Override
    public SemesterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_semester_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterAdapter.ViewHolder holder, int position) {

        Semester cSemester = semesterList.get(position);
        holder.name.setText(cSemester.getSemesterName());
        holder.credit.setText(String.valueOf(cSemester.getCredit()));
        holder.result.setText(String.valueOf(cSemester.getResult()));

        //update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("semester_id: ",String.valueOf(cSemester.getId()));

                Intent intent = new Intent(context, AddSemesterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", cSemester.getId());
                bundle.putString("name", cSemester.getSemesterName());
                bundle.putDouble("credit", cSemester.getCredit());
                bundle.putDouble("result", cSemester.getResult());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, credit, result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.semesterNameTV);
            credit = itemView.findViewById(R.id.semesterCreditTV);
            result = itemView.findViewById(R.id.resultTV);
        }
    }
}

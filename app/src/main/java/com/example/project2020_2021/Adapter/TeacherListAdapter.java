package com.example.project2020_2021.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2020_2021.Attribute.StudentAttr;
import com.example.project2020_2021.Attribute.TeacherAttr;
import com.example.project2020_2021.R;

import java.util.ArrayList;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {
    ArrayList<TeacherAttr> teacherAttrs;
    private Context context;

    public TeacherListAdapter(ArrayList<TeacherAttr> teacherAttrs, Context context) {
        this.context = context;
        this.teacherAttrs = teacherAttrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(teacherAttrs.get(position).getTeaname());
        holder.email.setText(teacherAttrs.get(position).getTeaemail());
        holder.phone.setText(teacherAttrs.get(position).getTeaphone());
        holder.city.setText(teacherAttrs.get(position).getTeacity());
        holder.country.setText(teacherAttrs.get(position).getTeacountry());


    }

    @Override
    public int getItemCount() {
        return teacherAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, email,city,country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtTecName);
            phone = (TextView) itemView.findViewById(R.id.txtTecPhone);
            email = (TextView) itemView.findViewById(R.id.txtTecAddress);
            city = (TextView) itemView.findViewById(R.id.txtTecCity);
            country = (TextView) itemView.findViewById(R.id.txtTecCountry);

        }
    }
}

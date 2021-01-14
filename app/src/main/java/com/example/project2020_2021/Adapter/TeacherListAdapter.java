package com.example.project2020_2021.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2020_2021.Attribute.StudentAttr;
import com.example.project2020_2021.Attribute.TeacherAttr;
import com.example.project2020_2021.R;
import com.squareup.picasso.Picasso;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(teacherAttrs.get(position).getTeaname());
        holder.type.setText(teacherAttrs.get(position).getTeatype());
       // Picasso.get().load(teacherAttrs.get(position).getImgurl()).into(holder.stuImage);
      //  holder.phone.setText(studentAttrs.get(position).getStuphone());
      //  holder.address.setText(studentAttrs.get(position).getStuaddress());
       // holder.type.setText(studentAttrs.get(position).getStutype());


    }

    @Override
    public int getItemCount() {
        return teacherAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, gender,address,type;
        ImageView tecImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtTecName);
           // gender = (TextView) itemView.findViewById(R.id.txtStuGender);
          //  phone = (TextView) itemView.findViewById(R.id.txtStuPhone);
          //  address = (TextView) itemView.findViewById(R.id.txtStuAddress);
            type = (TextView) itemView.findViewById(R.id.txtTecType);
            tecImage = (ImageView) itemView.findViewById(R.id.txtTecImage);

        }
    }
}

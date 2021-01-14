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
import com.example.project2020_2021.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    ArrayList<StudentAttr> studentAttrs;
    private Context context;

    public StudentListAdapter(ArrayList<StudentAttr> studentAttrs, Context context) {
        this.context = context;
        this.studentAttrs = studentAttrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(studentAttrs.get(position).getStuname());
        holder.type.setText(studentAttrs.get(position).getStutype());
        Picasso.get().load(studentAttrs.get(position).getImgurl()).into(holder.stuImage);
      //  holder.phone.setText(studentAttrs.get(position).getStuphone());
      //  holder.address.setText(studentAttrs.get(position).getStuaddress());
       // holder.type.setText(studentAttrs.get(position).getStutype());


    }

    @Override
    public int getItemCount() {
        return studentAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, gender,address,type;
        ImageView stuImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtStuName);
           // gender = (TextView) itemView.findViewById(R.id.txtStuGender);
          //  phone = (TextView) itemView.findViewById(R.id.txtStuPhone);
          //  address = (TextView) itemView.findViewById(R.id.txtStuAddress);
            type = (TextView) itemView.findViewById(R.id.txtStuType);
            stuImage = (ImageView) itemView.findViewById(R.id.txtStuimage);

        }
    }
}

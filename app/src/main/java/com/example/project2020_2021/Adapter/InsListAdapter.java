package com.example.project2020_2021.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2020_2021.Attribute.InstituteAttr;
import com.example.project2020_2021.Attribute.TeacherAttr;
import com.example.project2020_2021.R;

import java.util.ArrayList;

public class InsListAdapter extends RecyclerView.Adapter<InsListAdapter.ViewHolder> {
    ArrayList<InstituteAttr> instituteAttrs;
    private Context context;

    public InsListAdapter(ArrayList<InstituteAttr> instituteAttrs, Context context) {
        this.context = context;
        this.instituteAttrs = instituteAttrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(instituteAttrs.get(position).getInsname());
        holder.type.setText(instituteAttrs.get(position).getInstype());
       // Picasso.get().load(teacherAttrs.get(position).getImgurl()).into(holder.stuImage);
      //  holder.phone.setText(studentAttrs.get(position).getStuphone());
      //  holder.address.setText(studentAttrs.get(position).getStuaddress());
       // holder.type.setText(studentAttrs.get(position).getStutype());


    }

    @Override
    public int getItemCount() {
        return instituteAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, gender,address,type;
        ImageView insImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtInsName);
           // gender = (TextView) itemView.findViewById(R.id.txtStuGender);
          //  phone = (TextView) itemView.findViewById(R.id.txtStuPhone);
          //  address = (TextView) itemView.findViewById(R.id.txtStuAddress);
            type = (TextView) itemView.findViewById(R.id.txtInsType);
            insImage = (ImageView) itemView.findViewById(R.id.txtInsImage);

        }
    }
}

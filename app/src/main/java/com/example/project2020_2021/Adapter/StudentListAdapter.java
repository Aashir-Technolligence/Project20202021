package com.example.project2020_2021.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2020_2021.Databases.StuUserHelperClass;
import com.example.project2020_2021.R;
import com.example.project2020_2021.StudentDetail;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    ArrayList<StuUserHelperClass> studentAttrs;
    private Context context;

    public StudentListAdapter(ArrayList<StuUserHelperClass> studentAttrs, Context context) {
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
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Users/"+"Students/"+studentAttrs.get(position).getStuid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.stuImage);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , StudentDetail.class);
                i.putExtra("id" , studentAttrs.get(position).getStuid());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type;
        ImageView stuImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtStuName);
            type = (TextView) itemView.findViewById(R.id.txtStuType);
            stuImage = (ImageView) itemView.findViewById(R.id.txtStuimage);

        }
    }
}

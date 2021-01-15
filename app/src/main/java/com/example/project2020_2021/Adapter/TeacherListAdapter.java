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

import com.example.project2020_2021.Databases.TeaUserHelperClass;
import com.example.project2020_2021.R;
import com.example.project2020_2021.StudentDetail;
import com.example.project2020_2021.TeacherDetail;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {
    ArrayList<TeaUserHelperClass> teacherAttrs;
    private Context context;

    public TeacherListAdapter(ArrayList<TeaUserHelperClass> teacherAttrs, Context context) {
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
        holder.type.setText(teacherAttrs.get(position).getTeatype());
       // Picasso.get().load(teacherAttrs.get(position).getImgurl()).into(holder.stuImage);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Users/"+"Teachers/"+teacherAttrs.get(position).getTeaid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.tecImage);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context , TeacherDetail.class);
                i.putExtra("id" , teacherAttrs.get(position).getTeaid());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return teacherAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,type;
        ImageView tecImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtTecName);
            type = (TextView) itemView.findViewById(R.id.txtTecType);
            tecImage = (ImageView) itemView.findViewById(R.id.txtTecImage);

        }
    }
}

package com.example.project2020_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherDetail extends AppCompatActivity {
    private CircleImageView stuprofileimage;
    StorageReference storageReference;
    TextView name, gender, phone, qua;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        stuprofileimage = findViewById(R.id.teaprofileimage);
        name = findViewById(R.id.teaName);
        gender = findViewById(R.id.teaGender);
        phone = findViewById(R.id.teaPhone);
        qua = findViewById(R.id.teaQua);

        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Users/" + "Teachers/" + id + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(stuprofileimage);
            }
        });
        reference.child("Teachers").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if(snapshot.exists()){
                        String n = snapshot.child("teaname").getValue().toString();
                        String p = snapshot.child("teaphone").getValue().toString();
                        String q = snapshot.child("teaqua").getValue().toString();
                        String g = snapshot.child("teagender").getValue().toString();
                        name.setText(n);
                        phone.setText(p);
                        qua.setText(q);
                        gender.setText(g);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
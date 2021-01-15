package com.example.project2020_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project2020_2021.StudentsLogIn.StudentRegistration;
import com.example.project2020_2021.StudentsProfile.StuProfileAdapter;
import com.example.project2020_2021.StudentsProfile.StudentProfile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class StudentDetail extends AppCompatActivity {
    private CircleImageView stuprofileimage;
    StorageReference storageReference;
    TextView name, gender, phone, subject;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        stuprofileimage = findViewById(R.id.stuprofileimage);
        name = findViewById(R.id.stuName);
        gender = findViewById(R.id.stuGender);
        phone = findViewById(R.id.stuPhone);
        subject = findViewById(R.id.stuSubject);

        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Users/" + "Students/" + id + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(stuprofileimage);
            }
        });
        reference.child("Students").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if(snapshot.exists()){
                        String n = snapshot.child("stuname").getValue().toString();
                        String p = snapshot.child("stuphone").getValue().toString();
                        String s = snapshot.child("stussub").getValue().toString();
                        String g = snapshot.child("stugender").getValue().toString();
                        name.setText(n);
                        phone.setText(p);
                        subject.setText(s);
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
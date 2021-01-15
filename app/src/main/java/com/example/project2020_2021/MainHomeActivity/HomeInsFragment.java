package com.example.project2020_2021.MainHomeActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project2020_2021.Adapter.InstituteListAdapter;
import com.example.project2020_2021.Databases.UserHelperClass;
import com.example.project2020_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeInsFragment extends Fragment {
    RecyclerView listView;

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<UserHelperClass> instituteAttrs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_ins, container, false);
        listView = (RecyclerView) v.findViewById(R.id.insRecycler);
        instituteAttrs = new ArrayList<>();//constructor
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();  //database connectivity
        reference = database.getReference("Users");//table name

        reference.child("Institutes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (getActivity() != null) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            reference.child("Institutes").child(dataSnapshot1.getKey()).child("InstituteDetails").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        instituteAttrs.clear();
                                        UserHelperClass p = snapshot.getValue(UserHelperClass.class);
                                        instituteAttrs.add(p);
//                            Collections.reverse(studentAttrs);
                                        listView.setAdapter(new InstituteListAdapter(instituteAttrs, getContext()));
                                    } catch (Exception e) {
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    } else {
                        Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }
}
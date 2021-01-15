package com.example.project2020_2021.MainHomeActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project2020_2021.Adapter.StudentListAdapter;
import com.example.project2020_2021.Databases.StuUserHelperClass;
import com.example.project2020_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeStuFragment extends Fragment {
    RecyclerView listView;

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<StuUserHelperClass> studentAttrs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home_stu, container, false);

        listView = (RecyclerView) v.findViewById(R.id.recycler);
        studentAttrs = new ArrayList<>();//constructor
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();  //database connectivity
        reference = database.getReference("Users");//table name

        reference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(getActivity()!=null){
                    if (dataSnapshot.exists()) {
                        try {
                            studentAttrs.clear();
                            //profiledata.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                StuUserHelperClass p = dataSnapshot1.getValue(StuUserHelperClass.class);
                                studentAttrs.add(p);
                            }
//                            Collections.reverse(studentAttrs);
                            listView.setAdapter(new StudentListAdapter(studentAttrs, getContext()));
                        } catch (Exception e) {
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
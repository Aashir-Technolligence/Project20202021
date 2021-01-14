package com.example.project2020_2021.MainHomeActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project2020_2021.Adapter.InsListAdapter;
import com.example.project2020_2021.Adapter.StudentListAdapter;
import com.example.project2020_2021.Attribute.InstituteAttr;
import com.example.project2020_2021.Attribute.StudentAttr;
import com.example.project2020_2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeInsFragment extends Fragment {
    RecyclerView InslistView;

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<InstituteAttr> instituteAttrs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_ins, container, false);
        InslistView = (RecyclerView) view.findViewById(R.id.insRecycler);
        instituteAttrs = new ArrayList<>();//constructor
        InslistView.setLayoutManager(new LinearLayoutManager(getContext()));
        database = FirebaseDatabase.getInstance();  //database connectivity
        reference = database.getReference("Users");//table name

        reference.child("Institutes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(getActivity()!=null){
                    if (dataSnapshot.exists()) {
                        try{
                            instituteAttrs.clear();
                            //profiledata.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                InstituteAttr p = dataSnapshot1.getValue(InstituteAttr.class);
                                instituteAttrs.add(p);
                            }
//                            Collections.reverse(studentAttrs);
                            InslistView.setAdapter(new InsListAdapter(instituteAttrs, getContext()));
                        }
                        catch (Exception e){}
                    } else {
                        Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
return view;
    }
}
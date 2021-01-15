package com.example.project2020_2021.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2020_2021.Databases.UserHelperClass;
import com.example.project2020_2021.R;

import java.util.ArrayList;

public class InstituteListAdapter extends RecyclerView.Adapter<InstituteListAdapter.ViewHolder> {
    ArrayList<UserHelperClass> instituteAttrs;
    private Context context;

    public InstituteListAdapter(ArrayList<UserHelperClass> instituteAttrs, Context context) {
        this.context = context;
        this.instituteAttrs = instituteAttrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.institutelist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        holder.name.setText(instituteAttrs.get(position).getInsname());
//        holder.city.setText(instituteAttrs.get(position).getCity());
//        holder.phone.setText(instituteAttrs.get(position).getPhoneno());
//        holder.address.setText(instituteAttrs.get(position).getAddress());
//        holder.country.setText(instituteAttrs.get(position).getInscountry());


    }

    @Override
    public int getItemCount() {
        return instituteAttrs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, city,address,country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txtInsName);
            type = (TextView) itemView.findViewById(R.id.txtInsType);
//            phone = (TextView) itemView.findViewById(R.id.txtInsPhone);
//            address = (TextView) itemView.findViewById(R.id.txtInsAddress);
//            country = (TextView) itemView.findViewById(R.id.txtInsCountry);

        }
    }
}

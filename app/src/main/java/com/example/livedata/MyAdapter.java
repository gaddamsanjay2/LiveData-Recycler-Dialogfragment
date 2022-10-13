package com.example.livedata;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyModel> datamodelList;

    public MyAdapter(Context context, ArrayList<MyModel> datamodelList) {
        this.context = context;
        this.datamodelList = datamodelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler,parent,false);

        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ename.setText(datamodelList.get(position).getEname());
        holder.email.setText(datamodelList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return datamodelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ename,email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ename=itemView.findViewById(R.id.recycler_text1);
            email=itemView.findViewById(R.id.recycler_text2);

        }
    }
}

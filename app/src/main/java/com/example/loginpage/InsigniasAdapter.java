package com.example.loginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpage.models.Insignia;

import java.util.ArrayList;
import java.util.List;

public class InsigniasAdapter extends RecyclerView.Adapter<InsigniasAdapter.MyViewHolder> {
    private List<Insignia> values;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public View layout;

        public MyViewHolder(View v) {
            super(v);
            layout = v;
            name = v.findViewById(R.id.name);
        }
    }

    public void add(int position, Insignia item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public InsigniasAdapter(List<Insignia> myDataset) {
        values = myDataset;
    }

    @NonNull
    @Override
    public InsigniasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.recyclewiew_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Insignia insignia = values.get(position);
        holder.name.setText(insignia.getName());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


}

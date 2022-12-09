package com.example.adoptananimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdoptListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Pet> petList;

    public AdoptListAdapter(List<Pet> petList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.petList = petList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adopt_record_layout, parent, false);
        AdoptViewHolder vh = new AdoptViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Get pet from list
        Pet obj = petList.get(position);
        // Bind object information to view
        ((AdoptViewHolder) holder).txtPetId.setText(String.valueOf(obj.getId()));
        ((AdoptViewHolder) holder).txtPetName.setText(String.valueOf(obj.getName()));
        ((AdoptViewHolder) holder).txtPetType.setText(String.valueOf(obj.getType()));
        ((AdoptViewHolder) holder).pet = obj;
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}

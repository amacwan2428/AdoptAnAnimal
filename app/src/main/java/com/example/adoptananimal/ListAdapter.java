package com.example.adoptananimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Pet> petList;

    public ListAdapter(List<Pet> petList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.petList = petList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pet_layout, parent, false);
        ListViewHolder vh = new ListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Get pet from list
        Pet obj = petList.get(position);
        // Bind object information to view
        ((ListViewHolder) holder).txtPetId.setText(String.valueOf(obj.getId()));
        ((ListViewHolder) holder).txtPetName.setText(String.valueOf(obj.getName()));
        ((ListViewHolder) holder).txtPetType.setText(String.valueOf(obj.getType()));
        ((ListViewHolder) holder).txtStatusPet.setText(String.valueOf(obj.getStatus()));
        ((ListViewHolder) holder).pet = obj;
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

}
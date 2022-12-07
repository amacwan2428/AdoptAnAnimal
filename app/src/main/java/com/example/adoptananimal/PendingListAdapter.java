package com.example.adoptananimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PendingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Pet> petList;

    public PendingListAdapter(List<Pet> petList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.petList = petList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_record_layout, parent, false);
        PendingViewHolder vh = new PendingViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Get pet from list
        Pet obj = petList.get(position);
        // Bind object information to view
        ((PendingViewHolder) holder).txtPetId.setText(String.valueOf(obj.getId()));
        ((PendingViewHolder) holder).txtPetName.setText(String.valueOf(obj.getName()));
        ((PendingViewHolder) holder).txtPetType.setText(String.valueOf(obj.getType()));
        ((PendingViewHolder) holder).pet = obj;
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}

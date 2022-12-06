package com.example.adoptananimal;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PetListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Pet> petList;

    public PetListAdapter (List<Pet> petList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.petList = petList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}

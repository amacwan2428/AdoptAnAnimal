package com.example.adoptananimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    // PET LIST
    private List<Pet> petList;
    private List<UserInformation> ownersList;


    public HistoryListAdapter(List<Pet> petList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.petList = petList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates history record layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_record_layout, parent, false);
        // Create new viewholder object
        HistoryViewHolder vh = new HistoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Get pet from list
        Pet obj = petList.get(position);
        // Bind object information to view
        ((HistoryViewHolder) holder).txtPetId.setText(String.valueOf(obj.getId()));
        ((HistoryViewHolder) holder).txtPetName.setText(String.valueOf(obj.getName()));
        ((HistoryViewHolder) holder).txtPetType.setText(String.valueOf(obj.getType()));
        ((HistoryViewHolder) holder).pet = obj;
        if(obj.getType().trim().equals("Cat")){
            ((HistoryViewHolder) holder).imgPet.setImageResource(R.drawable.cat);
        }else{
            ((HistoryViewHolder) holder).imgPet.setImageResource(R.drawable.dog);
        }
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}

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
    //private List<Pet> petList;
    private List<Adoption> adoptionList;


    public HistoryListAdapter(List<Adoption> adoptionList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.adoptionList = adoptionList;
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
        Adoption obj = adoptionList.get(position);
        // Bind object information to view
        ((HistoryViewHolder) holder).txtPetId.setText(String.valueOf(obj.pet.getId()));
        ((HistoryViewHolder) holder).txtPetName.setText(String.valueOf(obj.pet.getName()));
        ((HistoryViewHolder) holder).txtPetType.setText(String.valueOf(obj.pet.getType()));
        ((HistoryViewHolder) holder).txtUserId.setText(String.valueOf(obj.user.getId()));
        ((HistoryViewHolder) holder).txtUserName.setText(String.valueOf(obj.user.getUname()));
        ((HistoryViewHolder) holder).txtUserAddress.setText(String.valueOf(obj.user.getUaddress()));
        ((HistoryViewHolder) holder).txtUserPhone.setText(String.valueOf(obj.user.getUphone()));
        ((HistoryViewHolder) holder).txtUserEmail.setText(String.valueOf(obj.user.getUemail()));

        ((HistoryViewHolder) holder).pet = obj.pet;
        // Put cat or dog image based on type of pet
        if(obj.pet.getType().trim().equals("Cat")){
            ((HistoryViewHolder) holder).imgPet.setImageResource(R.drawable.cat);
        }else{
            ((HistoryViewHolder) holder).imgPet.setImageResource(R.drawable.dog);
        }
    }

    @Override
    public int getItemCount() {
        return adoptionList.size();
    }
}

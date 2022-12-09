package com.example.adoptananimal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PendingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //private List<Pet> petList;
    private List<Adoption> adoptionList;

    public PendingListAdapter(List<Adoption> adoptionList, Context ctx)
    {
        super();
        // Get list of pets from constructor
        this.adoptionList = adoptionList;
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
        Adoption obj = adoptionList.get(position);
        // Bind object information to view
        ((PendingViewHolder) holder).txtPetId.setText(String.valueOf(obj.pet.getId()));
        ((PendingViewHolder) holder).txtPetName.setText(String.valueOf(obj.pet.getName()));
        ((PendingViewHolder) holder).txtPetType.setText(String.valueOf(obj.pet.getType()));
        ((PendingViewHolder) holder).txtUserId.setText(String.valueOf(obj.user.getId()));
        ((PendingViewHolder) holder).txtUserName.setText(String.valueOf(obj.user.getUname()));
        ((PendingViewHolder) holder).txtUserAddress.setText(String.valueOf(obj.user.getUaddress()));
        ((PendingViewHolder) holder).txtUserPhone.setText(String.valueOf(obj.user.getUphone()));
        ((PendingViewHolder) holder).txtUserEmail.setText(String.valueOf(obj.user.getUemail()));
        ((PendingViewHolder) holder).pet = obj.pet;
    }

    @Override
    public int getItemCount() {
        return adoptionList.size();
    }
}

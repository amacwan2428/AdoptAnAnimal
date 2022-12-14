package com.example.adoptananimal;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder extends RecyclerView.ViewHolder{

    public TextView txtPetId, txtPetName, txtPetType, txtUserId, txtUserName, txtUserAddress, txtUserPhone, txtUserEmail;
    public ImageView imgPet;
    public Pet pet;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets
        txtPetId = (TextView) itemView.findViewById(R.id.txtPetId);
        txtPetName = (TextView) itemView.findViewById(R.id.txtPetName);
        txtPetType = (TextView) itemView.findViewById(R.id.txtPetType);
        txtUserId = (TextView) itemView.findViewById(R.id.txtUserId);
        txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
        txtUserAddress = (TextView) itemView.findViewById(R.id.txtUserAddress);
        txtUserPhone = (TextView) itemView.findViewById(R.id.txtUserPhone);
        txtUserEmail = (TextView) itemView.findViewById(R.id.txtUserEmail);

        imgPet = (ImageView) itemView.findViewById(R.id.imageViewPet);

    }
}

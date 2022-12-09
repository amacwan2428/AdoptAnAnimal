package com.example.adoptananimal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    public TextView txtPetId, txtPetName, txtPetType,txtStatusPet;
    public Pet pet;
    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets
        txtPetId = (TextView) itemView.findViewById(R.id.txtIdPet);
        txtPetName = (TextView) itemView.findViewById(R.id.txtNamePet);
        txtPetType = (TextView) itemView.findViewById(R.id.txtTypePet);
        txtStatusPet = (TextView) itemView.findViewById(R.id.txtStatusPet);


    }
}

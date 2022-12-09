package com.example.adoptananimal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class AdoptViewHolder extends RecyclerView.ViewHolder{
    public TextView txtPetId, txtPetName, txtPetType;
    public Button btnApprove, btnReject;
    public Pet pet;
    public AdoptViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets
        txtPetId = (TextView) itemView.findViewById(R.id.txtPetId);
        txtPetName = (TextView) itemView.findViewById(R.id.txtPetName);
        txtPetType = (TextView) itemView.findViewById(R.id.txtPetType);
        btnApprove = (Button) itemView.findViewById(R.id.btnApprove);
        btnReject = (Button) itemView.findViewById(R.id.btnReject);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open db
                DBHelper dbh = new DBHelper(itemView.getContext());
                // Update pet status to approved
                dbh.UpdatePetStatus(pet,"APPROVED");
                // Refresh fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new PendingAdoptionFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLay, myFragment).addToBackStack(null).commit();

                Toast.makeText(itemView.getContext(), pet.getName() + " found a new home!", Toast.LENGTH_SHORT).show();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open db
                DBHelper dbh = new DBHelper(itemView.getContext());
                // Update pet status to available
                dbh.UpdatePetStatus(pet,"AVAILABLE");
                // Refresh fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new PendingAdoptionFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLay, myFragment).addToBackStack(null).commit();
                // Show message
                Toast.makeText(itemView.getContext(), pet.getName() + " adoption rejected", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

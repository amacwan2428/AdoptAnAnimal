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
    public Button btnApprove;
    public Pet pet;
    public AdoptViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets
        txtPetId = (TextView) itemView.findViewById(R.id.txtPetId);
        txtPetName = (TextView) itemView.findViewById(R.id.txtPetName);
        txtPetType = (TextView) itemView.findViewById(R.id.txtPetType);
        btnApprove = (Button) itemView.findViewById(R.id.btnApprove);


        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open db
                DBHelper dbh = new DBHelper(itemView.getContext());
                // Update pet status to approved
                dbh.UpdatePetStatus(pet,"PENDING");
                // Refresh fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new AdoptPetFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLay, myFragment).addToBackStack(null).commit();

                Toast.makeText(itemView.getContext(), "your request will be reviewed", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

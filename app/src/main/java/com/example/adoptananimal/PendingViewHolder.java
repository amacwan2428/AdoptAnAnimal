package com.example.adoptananimal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PendingViewHolder extends RecyclerView.ViewHolder {

    public TextView txtPetId, txtPetName, txtPetType;
    public Button btnApprove;
    public Pet pet;
    public PendingViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets
        txtPetId = (TextView) itemView.findViewById(R.id.txtPetId);
        txtPetName = (TextView) itemView.findViewById(R.id.txtPetName);
        txtPetType = (TextView) itemView.findViewById(R.id.txtPetType);
        btnApprove = (Button) itemView.findViewById(R.id.btnApprove);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), pet.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}

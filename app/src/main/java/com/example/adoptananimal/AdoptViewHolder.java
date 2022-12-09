package com.example.adoptananimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class AdoptViewHolder extends RecyclerView.ViewHolder{
    public TextView  txtPetNameAdopt;
    public Button btnApprove;
    public Pet pet;
    public ImageView imgPet;
    SharedPreferences sharedPreferences;

    public AdoptViewHolder(@NonNull View itemView) {
        super(itemView);
        // Reference to view widgets

        txtPetNameAdopt = (TextView) itemView.findViewById(R.id.txtPetNameAdopt);

        btnApprove = (Button) itemView.findViewById(R.id.btnApprove);
        imgPet = (ImageView) itemView.findViewById(R.id.imageViewPet);


        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open db
                DBHelper dbh = new DBHelper(itemView.getContext());
                //Get user information from shared preferences
                sharedPreferences = view.getContext().getSharedPreferences("login_activities", Context.MODE_PRIVATE);

                // Update pet status to approved
                dbh.AdoptPet(pet,sharedPreferences.getInt("USER_ID",-1));

                //dbh.UpdatePetStatus(pet,"PENDING");
                // Refresh fragment
                // Check if is admin and refresh fragment related to admin layout
                if(sharedPreferences.getString("USER_EMAIL","").equals("admin@admin.com"))
                {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new AdoptPetFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLay, myFragment).addToBackStack(null).commit();
                }
                else
                {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new AdoptPetFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayTwo, myFragment).addToBackStack(null).commit();

                }

                Toast.makeText(itemView.getContext(), "your request will be reviewed", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

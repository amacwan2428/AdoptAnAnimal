package com.example.adoptananimal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class PendingAdoptionFragment extends Fragment {
    View v;
    RecyclerView rcView;
    List<Pet> petList = new ArrayList<>();
    PendingListAdapter listAdapter;
    DBHelper dbh;

    public PendingAdoptionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pending_adoption, container, false);
        // Generate list recyclerview
        GenerateRecyclerView(v);
        return v;
    }
    public void GenerateRecyclerView(View v)
    {
        rcView = (RecyclerView) v.findViewById(R.id.rcView);
        dbh = new DBHelper(getActivity());

        // Uncomment to populate db

        // Create pets
        /*petList.add(new Pet(0, 0, "Charizard", "1990-08-04", "Cat", "PENDING"));
        petList.add(new Pet(1, 1, "Blastoise", "1990-08-05", "Cat", "PENDING"));
        petList.add(new Pet(2, 2, "Venusaur", "1990-08-06", "Dog","PENDING"));

        for (Pet p: petList
        ) {
            dbh.InsertPet(p);
        }
*/
        // List all pending approvals
        petList = dbh.ListPetsByStatus("PENDING");
        // Bind information to view
        BindAdapter();
    }

    private void BindAdapter() {
        // Get layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        // Setup recycler view layout manager
        rcView.setLayoutManager(layoutManager);

        // Create new adapter from pet list
        listAdapter = new PendingListAdapter(petList, v.getContext());
        // Feed student list to recycler view
        rcView.setAdapter(listAdapter);
        // Notify adapter change
        listAdapter.notifyDataSetChanged();

    }
}
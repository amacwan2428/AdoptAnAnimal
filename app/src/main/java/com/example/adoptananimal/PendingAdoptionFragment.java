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
        rcView = (RecyclerView) v.findViewById(R.id.rcView);
        // Create pets
        petList.add(new Pet(0, 0, "Java", "1990-08-04", "Cat"));
        petList.add(new Pet(1, 1, "Android", "1990-08-05", "Cat"));
        petList.add(new Pet(2, 2, "Windows", "1990-08-06", "Dog"));

        // Bind information to view
        BindAdapter();
        return v;
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
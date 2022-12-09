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


public class AdoptionHistoryFragment extends Fragment {

    View v;
    RecyclerView rcView;
    List<Pet> petList = new ArrayList<>();
    HistoryListAdapter listAdapter;
    DBHelper dbh;
    public AdoptionHistoryFragment() {
        // Required empty public constructor
    }

    public static AdoptionHistoryFragment newInstance(String param1, String param2) {
        AdoptionHistoryFragment fragment = new AdoptionHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adoption_history, container, false);
        // Get reference to recyclew view
        rcView = (RecyclerView) v.findViewById(R.id.rcViewHistory);
        // Init db
        dbh = new DBHelper(getActivity());
        // List all approved adoptions
        petList = dbh.ListPetsByStatus("APPROVED");
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
        listAdapter = new HistoryListAdapter(petList, v.getContext());
        // Feed student list to recycler view
        rcView.setAdapter(listAdapter);
        // Notify adapter change
        listAdapter.notifyDataSetChanged();

    }
}
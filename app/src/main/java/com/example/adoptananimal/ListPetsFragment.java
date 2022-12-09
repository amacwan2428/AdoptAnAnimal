package com.example.adoptananimal;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPetsFragment extends Fragment {

    View v;
    RecyclerView rcView;
    List<Pet> mList = new ArrayList<>();
    DBHelper dbh;
    ListAdapter mAdapter;

    public ListPetsFragment() {
        // Required empty public constructor
    }


    public static ListPetsFragment newInstance() {
        ListPetsFragment fragment = new ListPetsFragment();
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
        v = inflater.inflate(R.layout.fragment_list_pets, container, false);
        rcView = (RecyclerView) v.findViewById(R.id.rcViewListPets);
        dbh = new DBHelper(getActivity());
        //Calls the ListEmployee( ) method from the DBHelper class
        Cursor cursor1 = dbh.ListPets();
        if (cursor1.getCount() == 0) {
            Toast.makeText(getActivity(), "No Pets records found", Toast.LENGTH_LONG).show();
            return v;
        } else {
            cursor1.moveToFirst();

            do {
                Pet obj = new Pet();
                obj.setId(cursor1.getInt(0));
                obj.setUserId(cursor1.getInt(1));
                obj.setName(cursor1.getString(2));
                obj.setType(cursor1.getString(4));
                obj.setBirthdate(cursor1.getString(3));
                obj.setStatus(cursor1.getString(5));
                //Adds the employee object into the list
                mList.add(obj);
            } while (cursor1.moveToNext());
            cursor1.close();
            dbh.close();
            BindAdapter();
            return v;
        }
    }

    private void BindAdapter () {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcView.setLayoutManager(layoutManager);
        //Calls the constructor of ListAdapter and passes the list consitsing of all employee records
        mAdapter = new ListAdapter(mList, getContext());
        rcView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
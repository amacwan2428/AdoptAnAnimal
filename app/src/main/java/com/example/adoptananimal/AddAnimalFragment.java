package com.example.adoptananimal;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAnimalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAnimalFragment extends Fragment {

    View v;
    EditText edtPetName,edtBirthday;
    Spinner spinner;
    Button btnSubmit;
    DBHelper dbh; //Instance of the Database helper class 'DBHelper'
    Boolean insertStatus;
    DatePickerDialog datepicker;

    public AddAnimalFragment() {
        // Required empty public constructor
    }


    public static AddAnimalFragment newInstance(String param1, String param2) {
        AddAnimalFragment fragment = new AddAnimalFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_add_animal,container,false);

        spinner = (Spinner) v.findViewById(R.id.spTypePet);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),R.array.TypePet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edtBirthday = (EditText)v.findViewById(R.id.edtBirthday);
        edtPetName = (EditText)v.findViewById(R.id.edtPetName);
        dbh=new DBHelper(getActivity());

        edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int pday = cal.get(Calendar.DAY_OF_MONTH);
                int pmonth = cal.get(Calendar.MONTH);
                int pyear = cal.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtBirthday.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },pyear,pmonth,pday);

                datepicker.show();
            }
        });

        btnSubmit = v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pet objPet=CreatePet();
                insertStatus=dbh.InsertPet(objPet);
                if(insertStatus)
                {
                    Toast.makeText(getActivity(),"Record added successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Record insertion failure", Toast.LENGTH_LONG).show();
                }
            }
        });



        return v;
    }

    public Pet CreatePet()
    {
        Pet objPet = new Pet();
        int id=0;
        objPet.setId(id);
        objPet.setName(edtPetName.getText().toString().trim());
        objPet.setBirthdate(edtBirthday.getText().toString().trim());
        objPet.setType(spinner.getSelectedItem().toString().trim());
        objPet.setStatus("AVAILABLE");
        objPet.setUserId(1);
        return objPet;
    }
}
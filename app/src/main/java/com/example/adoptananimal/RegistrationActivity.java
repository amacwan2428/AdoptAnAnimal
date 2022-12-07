package com.example.adoptananimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtname, edtemail, edtpassword, edtaddress, edtnumber;
    Button btnRegister;
    DBHelper dbh;
    TextView txtLogin;
    Boolean insertStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtname = (EditText) findViewById(R.id.edtRegName);
        edtemail = (EditText) findViewById(R.id.edtRegEmail);
        edtpassword = (EditText) findViewById(R.id.edtRegPassword);
        edtaddress = (EditText) findViewById(R.id.edtRegAddress);
        edtnumber =(EditText) findViewById(R.id.edtRegPhone);
        btnRegister = (Button) findViewById(R.id.btnRegistration);
        txtLogin = (TextView) findViewById(R.id.txtUserLogin);
        dbh = new DBHelper(getApplicationContext());

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtname.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_LONG).show();
                    return;
                }else if(edtemail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(edtpassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(edtaddress.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Address",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(edtnumber.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Number",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    UserInformation user = CreateUser();
                    insertStatus = dbh.InsertUser(user);
                    if(insertStatus){
                        Toast.makeText(getApplicationContext(),"Record Added Successfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Record Not Added ",Toast.LENGTH_LONG).show();

                    }
                }

            }


        });


    }
    public UserInformation CreateUser(){
        UserInformation user = new UserInformation();
        int userId = 0;
        user.setId(userId);
        user.setUname(edtname.getText().toString().trim());
        user.setUemail(edtemail.getText().toString().trim());
        user.setUpassword(edtpassword.getText().toString().trim());
        user.setUaddress(edtaddress.getText().toString().trim());
        user.setUphone(edtnumber.getText().toString().trim());
        return user;
    }
}
package com.example.adoptananimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserEmail, edtUserPassword;
    Button btnLogin;
    TextView txtRegLink;
    String email,password;
    DBHelper dbh2;
    Boolean isUserFound;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserEmail = (EditText) findViewById(R.id.edtUserEmail);
        edtUserPassword = (EditText) findViewById(R.id.edtUserPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtRegLink = (TextView) findViewById(R.id.txtRegistration);
        dbh2 = new DBHelper(getApplicationContext());
        sharedPreferences = getSharedPreferences("login_activities", Context.MODE_PRIVATE);

        txtRegLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = edtUserEmail.getText().toString();
                password = edtUserPassword.getText().toString();

                isUserFound = dbh2.LoginUser(email,password);
                if(isUserFound){
                    Toast.makeText(getApplicationContext(),isUserFound.toString(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"User  found ",Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString("USER_EMAIL",email);
                    editor1.commit();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"User not found ",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
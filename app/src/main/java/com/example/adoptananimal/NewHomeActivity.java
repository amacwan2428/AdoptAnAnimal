package com.example.adoptananimal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NewHomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nDrawer;
    private ActionBarDrawerToggle drawerToggle;
    TextView txtWelcome;
    String email;
    SharedPreferences sharedPreferences;
    ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        nDrawer = (NavigationView) findViewById(R.id.nav_view_two);
        mDrawer = (DrawerLayout) findViewById(R.id.new_nav_layout);
        txtWelcome = (TextView) findViewById(R.id.txtWelcomeUser);
        imgHome = (ImageView) findViewById(R.id.imgHome);
        sharedPreferences = getSharedPreferences("login_activities", Context.MODE_PRIVATE);
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,R.string.drawer_open,R.string.drawer_close);
        }
        drawerToggle.setDrawerIndicatorEnabled(true);
        mDrawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setupDrawerContent(nDrawer);

        email =sharedPreferences.getString("USER_EMAIL",null);
        txtWelcome.setText("Welcome " + email);
    }

    private void setupDrawerContent(NavigationView nDrawer) {
        nDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }
    public void selectDrawerItem(MenuItem item){
        Fragment frag = null;
        Class fragmentClass = null;
        switch (item.getItemId()){
            case R.id.add_pets:
                fragmentClass = AddAnimalFragment.class;
                txtWelcome.setVisibility(View.INVISIBLE);
                imgHome.setVisibility(View.INVISIBLE);
                break;
            case R.id.adopt_pet:
                fragmentClass = AdoptPetFragment.class;
                txtWelcome.setVisibility(View.INVISIBLE);
                imgHome.setVisibility(View.INVISIBLE);

                break;
            case R.id.adoption_history:
                fragmentClass = AdoptionHistoryFragment.class;
                txtWelcome.setVisibility(View.INVISIBLE);
                imgHome.setVisibility(View.INVISIBLE);

                break;

            case R.id.logout:
                Intent intent = new Intent(NewHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                txtWelcome.setVisibility(View.INVISIBLE);
                imgHome.setVisibility(View.INVISIBLE);


                break;
        }
        try{
            frag = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(frag !=null)
        {
            FragmentManager fragManager = getSupportFragmentManager();
            fragManager.beginTransaction().replace(R.id.frameLayTwo,frag).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
            mDrawer.closeDrawers();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



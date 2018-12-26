package com.williams.jordan.bartenderapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActionActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_action);

       Toolbar toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new clientAdd()).commit();
                        break;
                    case  R.id.lister:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClientList()).commit();
                        break;

                }
                return true;
            }
        });


        ActionBarDrawerToggle togggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigaion_drawer_close);

        mDrawerLayout.addDrawerListener(togggle);
        togggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new clientAdd()).commit();
        navigationView.setCheckedItem(R.id.add);


    }
    @Override
    public void onBackPressed() {
        //for allowing for click back
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}

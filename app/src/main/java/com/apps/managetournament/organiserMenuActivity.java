package com.apps.managetournament;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


public class organiserMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.drawer_open,R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    /* if a default activity is needed in the main user menu
     if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TournamentFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_message);
     }*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_message:
                finish();
                startActivity(new Intent(getApplicationContext(), TournamentActivity.class));
                break;

                //Repeat this to select other activities
               //Any activity to be selected using the menu will be added here
            case R.id.nav_share:
                Toast.makeText(this,"You shared this tournament with your friends",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this,"Your message have been sent to the tournament organiser\nthank you!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_sign_out:
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();}
    }
}

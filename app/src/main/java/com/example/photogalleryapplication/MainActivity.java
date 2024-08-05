package com.example.photogalleryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for this activity

        // Initialize the Toolbar and set it as the ActionBar for the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); // Set this activity as the listener for navigation item selections

        // Setup the ActionBarDrawerToggle for handling the drawer toggle button
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Synchronize the state of the drawer toggle

        // If there's no saved instance state (i.e., first launch), set the default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home); // Set the Home item as checked
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handle navigation view item clicks
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        } else if (id == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
        } else if (id == R.id.nav_logout) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LogoutFragment()).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer when an item is selected
        return true;
    }

    @Override
    public void onBackPressed() {
        // Handle back button press: close the drawer if it’s open, otherwise use default behavior
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* create fragments */
    Fragment fragmentUserInfo = new UserInfo();
    Fragment fragmentExamineBookings = new ExamineBookings();
    Fragment fragmentOwnBookings = new OwnBookings();
    Fragment fragmentMakeBooking = new MakeBooking();

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        context = MainActivity.this;
        System.out.println(context.getFilesDir());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /*This method opens the fragment chosen in navigation view*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();
        if (id == R.id.tiedot) {
            fragment = fragmentUserInfo;
        } else if (id == R.id.tarkastele) {
            fragment = fragmentExamineBookings;
        } else if (id == R.id.bookings) {
            fragment = fragmentOwnBookings;
        } else if (id == R.id.book) {
            fragment = fragmentMakeBooking;
        }
        fragmentManager.beginTransaction().replace(R.id.fragmentView, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* Save user info */
    public void save(String n, String pno) {
        User k = User.getInstance();
        k.setInfo(n, pno);
        System.out.println(k.getName() + k.getphoneNumber());
    }

    /* Book halls */
    public void book(String hall, String day, String start, String end, String Reason) {
        BookingManager bm = BookingManager.getInstance();
        Hall h = bm.getHall(hall);
        User user = User.getInstance();
        h.bookHall(day, start, end, user, Reason);
    }

    public void createXML() {

        WriteXML writeXML = new WriteXML();

        writeXML.write(context);

    }
}

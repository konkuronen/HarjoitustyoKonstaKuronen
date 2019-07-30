package com.example.myapplication;

import android.os.Bundle;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragmentKayttajatiedot = new Kayttajatiedot();
    Fragment fragmentTarkastelevarauksia = new TarkasteleVarauksia();
    Fragment fragmentOmatvaraukset = new OmatVaraukset();
    Fragment fragmentTeevaraus = new TeeVaraus();
    private Button tallenna;
    private TextInputEditText nimi, puhelinnumero;

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
        Varaushallinta varaushallinta = Varaushallinta.getInstance();
        tallenna = findViewById(R.id.tallenna);
        nimi = findViewById(R.id.nimi);
        puhelinnumero = findViewById(R.id.puhelinnumero);



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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.tiedot) {
            fragment = fragmentKayttajatiedot;
        } else if (id == R.id.tarkastele) {
            fragment = fragmentTarkastelevarauksia;
        } else if (id == R.id.varaukset) {
            fragment = fragmentOmatvaraukset;
        } else if (id == R.id.varaa) {
            fragment = fragmentTeevaraus;
        }
        fragmentManager.beginTransaction().replace(R.id.fragmentView, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void tallenna(String n, String pno) {
        Kayttaja k = Kayttaja.getInstance();
        k.setInfo(n, pno);
        System.out.println(k.getNimi() + k.getPuhelinnumero());
    }

    public void varaa(String sali, String paiva, String alku, String loppu, String syy) {
        Varaushallinta vh = Varaushallinta.getInstance();
        /*for (int i = 0 ; i < vh.getSalilista().size() ; i++) {
            if (vh.getSalilista().get(i).getNimi().equals(sali)) {*/
        Sali s = vh.getSali(sali);
        Kayttaja k = Kayttaja.getInstance();
        s.varaaSali(paiva, alku, loppu, k, syy);
        s.tulostaVaraukset(paiva);
    }
}

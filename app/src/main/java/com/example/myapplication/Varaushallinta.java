package com.example.myapplication;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Varaushallinta {

    private static Varaushallinta vh = null;


    public static Varaushallinta getInstance() {
        if (vh == null) vh = new Varaushallinta();
        return vh;
    }

    private ArrayList<Sali> salilista = new ArrayList<>();

    public Varaushallinta() {
        for (int i = 0 ; i < 3 ; i++) {
            String s = String.valueOf(i +1);
            Sali sali = new Sali("sali " + s);
            salilista.add(sali);
            System.out.println(sali.getNimi());
        }
    }

    public ArrayList<Sali> getSalilista() {
        return salilista;
    }

    public Sali getSali(String sali) {
        for (int i = 0 ; i < salilista.size() ; i++) {
            if (salilista.get(i).getNimi().equals(sali)) {
                return salilista.get(i);
            }
        } return null;
    }


}

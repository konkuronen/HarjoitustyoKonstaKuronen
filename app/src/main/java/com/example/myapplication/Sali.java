package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sali {

    private String nimi;
    private ArrayList<Varaus> varaukset = new ArrayList<>();

    public Sali(String s) {
        nimi = s;
    }

    public String getNimi() {
        return nimi;
    }

    public void varaaSali(String paiva, String alku, String loppu, Kayttaja k, String syy) {
        Varaus v = new Varaus(paiva, alku, loppu, k, syy);
        varaukset.add(v);
    }

    public int getSize() {
        return varaukset.size();
    }

    public String getDay(int i) {
        return varaukset.get(i).getPaiva();
    }

    public Varaus getVaraus(int i) {
        return varaukset.get(i);
    }

    public void tulostaVaraukset(String paiva) {
        for (int i = 0 ; i < varaukset.size() ; i++) {
            if (varaukset.get(i).getPaiva() == paiva) {
                System.out.println("Varaaja: " + varaukset.get(i).getVaraajannimi() + ", " + varaukset.get(i).getVaraajanPno());
                System.out.println("Alku: " + varaukset.get(i).getAlku());
                System.out.println("Loppu: " + varaukset.get(i).getLoppu());
                System.out.println("Syy: " + varaukset.get(i).getSyy());
            }
        }
    }

    public boolean tarkistaSaatavuus(String paiva, String alku, String loppu) {
        SimpleDateFormat parser = new SimpleDateFormat("hh:mm");

        try {
            Date alkuaika = parser.parse(alku);
            Date loppuaika = parser.parse(loppu);

            if (varaukset.size() == 0) {
                return true;
            }
            for (int i = 0 ; i < varaukset.size() ; i++) {
                if (varaukset.get(i).getPaiva().equals(paiva)) {
                    Date varauksenAlku = parser.parse(varaukset.get(i).getAlku());
                    Date varauksenLoppu = parser.parse(varaukset.get(i).getLoppu());
                    if (varauksenAlku.before(alkuaika) && varauksenLoppu.before(alkuaika)) {
                        System.out.println("VARAUKSEN JÄLKEEN");
                        return true;
                    } if (varauksenAlku.after(loppuaika) && varauksenLoppu.after(loppuaika)) {
                        return true;
                    } else {
                        System.out.println("VARATTU");
                        return false;
                    }
                }
            } return true;

        } catch (ParseException e) {
            System.out.println("EXCEPITON");
            e.printStackTrace();
        }
        return false;
    }
}

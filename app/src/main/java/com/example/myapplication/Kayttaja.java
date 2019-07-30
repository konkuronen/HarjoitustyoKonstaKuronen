package com.example.myapplication;

public class Kayttaja {

    private String nimi;
    private String puhelinnumero;
    private static Kayttaja kayttaja = null;

    public Kayttaja() {}

    public static Kayttaja getInstance() {
        if (kayttaja == null) kayttaja = new Kayttaja();
        return kayttaja;
    }

    public void setInfo(String n, String pno) {
        nimi = n;
        puhelinnumero = pno;
    }

    public String getNimi() {
        return nimi;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }
}

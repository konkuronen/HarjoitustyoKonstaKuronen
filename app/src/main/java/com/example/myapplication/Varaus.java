package com.example.myapplication;

public class Varaus {

    private String paiva, alku, loppu;
    private String varaajanNimi, varaajanPno;
    private String syy;

    public Varaus (String spaiva, String salku, String sloppu, Kayttaja svaraaja, String ssyy) {
        paiva = spaiva;
        alku = salku;
        loppu = sloppu;
        varaajanNimi = svaraaja.getNimi();
        varaajanPno = svaraaja.getPuhelinnumero();
        syy  = ssyy;
    }

    public String getAlku() {
        return alku;
    }
    public String getLoppu() {
        return loppu;
    }

    public String getVaraajannimi() {
        return varaajanNimi;
    }

    public String getVaraajanPno() {return  varaajanPno; }

    public String getSyy() {
        return syy;
    }

    public String getPaiva() { return paiva; }
}

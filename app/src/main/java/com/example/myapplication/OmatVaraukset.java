package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OmatVaraukset extends Fragment {

    private Button paivita;
    private ScrollView sv;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_omatvaraukset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        paivita = view.findViewById(R.id.paivita);
        sv = view.findViewById(R.id.sv);
        ll = view.findViewById(R.id.ll);

        paivita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.removeAllViews();
                Varaushallinta vh = Varaushallinta.getInstance();
                Kayttaja k = Kayttaja.getInstance();
                System.out.println(k.getNimi() + k.getPuhelinnumero());
                for (int i = 0 ; i < vh.getSalilista().size() ; i++) {
                    Sali s = vh.getSalilista().get(i);
                    for (int a = 0 ; a < s.getSize() ; a++ ) {
                        Varaus v = s.getVaraus(a);
                        if (v.getVaraajannimi().equals(k.getNimi()) && v.getVaraajanPno().equals(k.getPuhelinnumero())){
                            TextView tw = new TextView(view.getContext());
                            tw.setText("\n" + vh.getSalilista().get(i).getNimi() + "\nPäivä: " + v.getPaiva() +  "\nAlku: " + v.getAlku() + "\nLoppu: " + v.getLoppu() + "\nVaraaja: Sinä" + "\nSyy: " + v.getSyy());
                            ll.addView(tw);
                        }
                    }
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}

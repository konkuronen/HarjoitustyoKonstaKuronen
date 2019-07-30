package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TarkasteleVarauksia extends Fragment {

    private Varaushallinta vh = Varaushallinta.getInstance();
    private Spinner tarkastelusalit;
    private TextInputEditText paiva;
    private Button tarkista;
    private ScrollView scrollView;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tarkastelevarauksia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tarkastelusalit = view.findViewById(R.id.salit);
        paiva = view.findViewById(R.id.paiva);
        tarkista = view.findViewById(R.id.tarkista);
        scrollView = view.findViewById(R.id.scrollView2);
        ll = view.findViewById(R.id.ll);

        final List<String> list = new ArrayList<>();
        list.add("Valitse sali...");
        for (int i = 0 ; i < vh.getSalilista().size() ; i++) {
            list.add(vh.getSalilista().get(i).getNimi());
            System.out.println(list);
        }
        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        tarkastelusalit.setAdapter(listAdapter);

        tarkista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.removeAllViews();
                String sali = tarkastelusalit.getSelectedItem().toString();
                String d = paiva.getText().toString();
                for (int i = 0 ; i < vh.getSalilista().size() ; i++) {
                    if (vh.getSalilista().get(i).getNimi().equals(sali)) {
                        System.out.println("SALI LÖYTYI");
                        Sali s = vh.getSalilista().get(i);
                        for (int a = 0 ; a < s.getSize(); a++) {
                            if (s.getDay(a).equals(d)) {
                                System.out.println("VARAUS PÄIVÄLLE LÖYTYI");
                                TextView tw = new TextView(view.getContext());
                                Varaus varaus = s.getVaraus(a);
                                tw.setText("Alku: " + varaus.getAlku() + "\nLoppu: " + varaus.getLoppu() + "\nVaraaja: " + varaus.getVaraajannimi() + ", pno: "
                                    + varaus.getVaraajanPno() + "\nSyy: " + varaus.getSyy());
                                ll.addView(tw);
                            }
                        }
                    }
                }

            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}

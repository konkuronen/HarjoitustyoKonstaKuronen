package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TeeVaraus extends Fragment {

    private Spinner salit;
    private TextInputEditText alku, loppu, syy, paiva;
    private Button varausnappi;
    private Varaushallinta vh = Varaushallinta.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_teevaraus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        paiva = view.findViewById(R.id.paiva);
        salit = view.findViewById(R.id.spinner);
        alku = view.findViewById(R.id.alku);
        loppu = view.findViewById(R.id.loppu);
        syy = view.findViewById(R.id.syy);
        varausnappi = view.findViewById(R.id.varausnappi);

        varausnappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Varaushallinta vh = Varaushallinta.getInstance();
                Sali s = vh.getSali(salit.getSelectedItem().toString());
                String d = paiva.getText().toString();
                String start = alku.getText().toString();
                String end = loppu.getText().toString();
                System.out.println(d);
                System.out.println(start);
                System.out.println(end);

                    if (!s.tarkistaSaatavuus(d, start, end)) {
                        Toast.makeText(getActivity(), "Haluttu aika on jo varattu!", Toast.LENGTH_SHORT).show();
                    } else {
                        ((MainActivity) getActivity()).varaa(salit.getSelectedItem().toString(), paiva.getText().toString(), alku.getText().toString(),
                                loppu.getText().toString(), syy.getText().toString());
                    }
            }
        });

        final List<String> list = new ArrayList<>();
        list.add("Valitse sali...");
        for (int i = 0 ; i < vh.getSalilista().size() ; i++) {
            list.add(vh.getSalilista().get(i).getNimi());
            System.out.println(list);
        }
        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        salit.setAdapter(listAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

}

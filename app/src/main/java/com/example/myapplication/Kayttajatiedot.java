package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class Kayttajatiedot extends Fragment {

    private TextInputEditText nimi, puhelinnumero;
    private Button nappi;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kayttajatiedot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        nimi = view.findViewById(R.id.nimi);
        puhelinnumero = view.findViewById(R.id.puhelinnumero);
        nappi = view.findViewById(R.id.tallenna);
        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).tallenna(nimi.getText().toString(), puhelinnumero.getText().toString());
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}

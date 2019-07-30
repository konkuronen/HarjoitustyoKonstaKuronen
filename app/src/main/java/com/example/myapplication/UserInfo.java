package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class UserInfo extends Fragment {

    private TextInputEditText name, phoneNumber;
    private Button button, createXML;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userinfo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        name = view.findViewById(R.id.name);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        button = view.findViewById(R.id.save);
        createXML = view.findViewById(R.id.createXML);

        /* Set OnClickListener to save input into the User class */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).save(name.getText().toString(), phoneNumber.getText().toString());
                Toast.makeText(getActivity(), "User info saved!", Toast.LENGTH_SHORT).show();
            }
        });

        createXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).createXML();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}

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

public class MakeBooking extends Fragment {

    private Spinner halls;
    private TextInputEditText start, end, Reason, day;
    private Button book;
    private BookingManager bm = BookingManager.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_makebooking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        day = view.findViewById(R.id.day);
        halls = view.findViewById(R.id.spinner);
        start = view.findViewById(R.id.start);
        end = view.findViewById(R.id.end);
        Reason = view.findViewById(R.id.Reason);
        book = view.findViewById(R.id.book);

        /* Set the OnClickListener to book the selected hall on the selected day at the selected hours */
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingManager bm = BookingManager.getInstance();
                Hall h = bm.getHall(halls.getSelectedItem().toString());
                String d = day.getText().toString();
                String starts = start.getText().toString();
                String ends = end.getText().toString();

                    if (!h.checkIfAvailable(d, starts, ends)) { //Check if the hall is already booked in the Hall class
                        Toast.makeText(getActivity(), "The selected time is already booked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Hall booked!", Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).book(halls.getSelectedItem().toString(), day.getText().toString(), start.getText().toString(),
                                end.getText().toString(), Reason.getText().toString());
                    }
            }
        });

        /* Create the spinner objects from the list of halls in the BookingManager class */
        final List<String> list = new ArrayList<>();
        list.add("Choose Hall...");
        for (int i = 0 ; i < bm.getHallList().size() ; i++) {
            list.add(bm.getHallList().get(i).getName());
            System.out.println(list);
        }
        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        halls.setAdapter(listAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

}

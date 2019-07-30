package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ExamineBookings extends Fragment {

    private BookingManager bm = BookingManager.getInstance();
    private Spinner halls;
    private TextInputEditText day;
    private Button examine;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_examinebookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        halls = view.findViewById(R.id.halls);
        day = view.findViewById(R.id.day);
        examine = view.findViewById(R.id.examine);
        ll = view.findViewById(R.id.ll);

        /* Create spinner objects from the list of halls in the BookingManager class */
        final List<String> list = new ArrayList<>();
        list.add("Choose Hall...");
        for (int i = 0 ; i < bm.getHallList().size() ; i++) {
            list.add(bm.getHallList().get(i).getName());
            System.out.println(list);
        }
        ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        halls.setAdapter(listAdapter);

        /* Set the OnClickListener to show bookings in the selected hall on the selected day */
        examine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.removeAllViews();
                String hall = halls.getSelectedItem().toString();
                String d = day.getText().toString();
                Hall h = bm.getHall(hall);
                for (int a = 0 ; a < h.getSize(); a++) { //find all bookings for the selected day and add them to the layout inside the scrollview
                    if (h.getDay(a).equals(d)) {
                        TextView tw = new TextView(view.getContext());
                        Booking booking = h.getBooking(a);
                        tw.setText("start: " + booking.getstart() + "\nend: " + booking.getend() + "\nbooker: " + booking.getBookerName() + ", phone: "
                                + booking.getBookerPhone() + "\nReason: " + booking.getReason());
                        ll.addView(tw);
                    }
                }
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }
}

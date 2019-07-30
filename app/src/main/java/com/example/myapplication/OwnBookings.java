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

public class OwnBookings extends Fragment {

    private Button update;
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ownbookings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        update = view.findViewById(R.id.update);
        ll = view.findViewById(R.id.ll);

        /* Set the OnClickListener to show all bookings made by the saved User */
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.removeAllViews();
                BookingManager bm = BookingManager.getInstance();
                User user = User.getInstance();
                System.out.println(user.getName() + user.getphoneNumber());
                for (int i = 0 ; i < bm.getHallList().size() ; i++) {   // Go through each hall
                    Hall s = bm.getHallList().get(i);
                    for (int a = 0 ; a < s.getSize() ; a++ ) {  // Go through each booking
                        Booking booking = s.getBooking(a);
                        if (booking.getBookerName().equals(user.getName()) && booking.getBookerPhone().equals(user.getphoneNumber())){ //Check if the booking was made by the saved User
                            TextView tw = new TextView(view.getContext());
                            tw.setText("\n" + bm.getHallList().get(i).getName() + "\nDay: " + booking.getDay() +
                                    "\nStart: " + booking.getstart() + "\nEnd: " + booking.getend() + "\nReason: " + booking.getReason());
                            ll.addView(tw);
                        }
                    }
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}

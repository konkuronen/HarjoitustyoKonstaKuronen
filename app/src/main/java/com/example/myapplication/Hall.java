package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Hall {

    private String name;
    private ArrayList<Booking> bookings = new ArrayList<>();

    public Hall(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public int getSize() { return bookings.size(); }

    public String getDay(int i) { return bookings.get(i).getDay(); }

    public Booking getBooking(int i) { return bookings.get(i); }

    /* Add a booking to the list of bookings */
    public void bookHall(String day, String start, String end, User k, String Reason) {
        Booking booking = new Booking(day, start, end, k, Reason);
        bookings.add(booking);
    }

    /* Check if the hall is available on the selected day at the requested time */
    public boolean checkIfAvailable(String day, String start, String end) {
        SimpleDateFormat parser = new SimpleDateFormat("hh:mm");

        try {
            Date starttime = parser.parse(start);
            Date endtime = parser.parse(end);

            if (bookings.size() == 0) {
                return true;
            }

            for (int i = 0 ; i < bookings.size() ; i++) {
                if (bookings.get(i).getDay().equals(day)) {
                    Date bookingStrart = parser.parse(bookings.get(i).getstart());
                    Date bookingEnd = parser.parse(bookings.get(i).getend());
                    if (bookingStrart.before(starttime) && bookingEnd.before(starttime)) {
                        return true;
                    } if (bookingStrart.after(endtime) && bookingEnd.after(endtime)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } return true;

        } catch (ParseException e) {
            System.out.println("EXCEPITON");
            e.printStackTrace();
        }

        return false;
    }
}

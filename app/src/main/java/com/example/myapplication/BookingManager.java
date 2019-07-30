package com.example.myapplication;

import java.util.ArrayList;

public class BookingManager {


    private static BookingManager bm = null;
    public static BookingManager getInstance() {
        if (bm == null) bm = new BookingManager();
        return bm;
    }

    private ArrayList<Hall> HallList = new ArrayList<>();

    public BookingManager() {
        for (int i = 0 ; i < 3 ; i++) {
            String s = String.valueOf(i +1);
            Hall Hall = new Hall("Hall " + s);
            HallList.add(Hall);
            System.out.println(Hall.getName());
        }
    }

    public ArrayList<Hall> getHallList() {
        return HallList;
    }

    public Hall getHall(String Hall) {
        for (int i = 0 ; i < HallList.size() ; i++) {
            if (HallList.get(i).getName().equals(Hall)) {
                return HallList.get(i);
            }
        } return null;
    }


}

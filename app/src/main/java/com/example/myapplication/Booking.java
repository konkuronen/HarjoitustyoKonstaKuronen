package com.example.myapplication;

public class Booking {

    private String day, start, end;
    private String bookerName, bookerPhone;
    private String Reason;

    public Booking(String sDay, String sStart, String sEnd, User user, String sReason) {
        day = sDay;
        start = sStart;
        end = sEnd;
        bookerName = user.getName();
        bookerPhone = user.getphoneNumber();
        Reason  = sReason;
    }

    public String getstart() {
        return start;
    }

    public String getend() {
        return end;
    }

    public String getBookerName() {
        return bookerName;
    }

    public String getBookerPhone() { return  bookerPhone; }

    public String getReason() {
        return Reason;
    }

    public String getDay() { return day; }
}

package com.example.scams;

import java.util.Date;

public class Meeting {
    private String Meeting_ID;
    private String Meeting_name;
    private Date date;
    private int club_ID; //Change the variable type to club
    Meeting(String Meeting_ID,String Meeting_name,Date date,int club_Id)
    {
        this.Meeting_ID=Meeting_ID;
        this.Meeting_name=Meeting_name;
        this.date=date;
        this.club_ID=club_Id;
    }

    public String getMeeting_ID() {
        return Meeting_ID;
    }

    public String getMeeting_name() {
        return Meeting_name;
    }

    public Date getDate() {
        return date;
    }

    public int getClub_ID() {
        return club_ID;
    }

}

package com.example.scams;

public class Meeting {
    private String MeetingId;
    private String MeetingName;
    private String MeetingDate;
    private String  ClubID;
    Meeting(String MeetingId,String MeetingName,String MeetingDate,String ClubID)
    {
        this.MeetingId=MeetingId;
        this.MeetingName=MeetingName;
        this.MeetingDate=MeetingDate;
        this.ClubID=ClubID;
    }

    public String getMeetingId() {
        return MeetingId;
    }

    public String getMeetingName() {
        return MeetingName;
    }

    public String getMeetingDate() {
        return MeetingDate;
    }

    public String getClubID() {
        return ClubID;
    }
}


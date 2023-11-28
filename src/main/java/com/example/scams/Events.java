package com.example.scams;

public class Events {
    private String EventId;
    private String EventName;
    private String EventDate;
    private String  ClubID;
    Events(String EventId,String EventName,String EventDate,String ClubID)
    {
        this.EventId=EventId;
        this.EventName=EventName;
        this.EventDate=EventDate;
        this.ClubID=ClubID;
    }

    public String getEventId() {
        return EventId;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventDate() {
        return EventDate;
    }

    public String getClubID() {
        return ClubID;
    }
}

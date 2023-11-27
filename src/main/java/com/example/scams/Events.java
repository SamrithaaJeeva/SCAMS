package com.example.scams;

import java.util.Date;

public class Events {
    private String EventId;
    private String EventName;
    private Date EventDate;
    private String  Club_name;
    Events(String EventId,String EventName,Date EventDate,String Club_name)
    {
        this.EventId=EventId;
        this.EventName=EventName;
        this.EventDate=EventDate;
        this.Club_name=Club_name;
    }

    public String getEventId() {
        return EventId;
    }

    public String getEventName() {
        return EventName;
    }

    public Date getEventDate() {
        return EventDate;
    }

    public String getClub_name() {
        return Club_name;
    }
}

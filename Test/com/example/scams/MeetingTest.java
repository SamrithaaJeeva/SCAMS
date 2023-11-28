package com.example.scams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingTest {

    @Test
    void getMeetingId()
    {
        Meeting meeting= new Meeting("201","Opening Moves Meetup","2023-11-21", "401");
        String expected_output= meeting.getMeetingId();
        String actual_output= "201";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getMeetingName()
    {
        Meeting meeting= new Meeting("201","Opening Moves Meetup","2023-11-21", "401");
        String expected_output= meeting.getMeetingName();
        String actual_output= "Opening Moves Meetup";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getMeetingDate()
    {
        Meeting meeting= new Meeting("201","Opening Moves Meetup","2023-11-21", "401");
        String expected_output= meeting.getMeetingDate();
        String actual_output= "2023-11-21";
        assertEquals(expected_output,actual_output);

    }

    @Test
    void getClubID()
    {
        Meeting meeting= new Meeting("201","Opening Moves Meetup","2023-11-21", "401");
        String expected_output= meeting.getClubID();
        String actual_output= "401";
        assertEquals(expected_output,actual_output);
    }
}
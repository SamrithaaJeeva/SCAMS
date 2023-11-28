package com.example.scams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {
    @Test
    void getEventId()
    {
        Events event= new Events("102","Video Games Night","2023-11-24", "402");
        String expected_output= event.getEventId();
        String actual_output= "102";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getEventName()
    {
        Events event= new Events("102","Video Games Night","2023-11-24", "402");
        String expected_output= event.getEventName();
        String actual_output= "Video Games Night";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getEventDate()
    {
        Events event= new Events("102","Video Games Night","2023-11-24", "402");
        String expected_output= event.getEventDate();
        String actual_output= "2023-11-24";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getClubID()
    {
        Events event= new Events("102","Video Games Night","2023-11-24", "402");
        String expected_output= event.getClubID();
        String actual_output= "402";
        assertEquals(expected_output,actual_output);

    }
}
package com.example.scams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubsTest {
    @Test
    void getClubName()
    {
        Clubs club= new Clubs("401","Music Club","Mathew","Connect through music, explore genres, and enjoy shared musical experiences.");
        String expected_output= club.getClubName();
        String actual_output= "Music Club";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getAdvisorName()
    {
            Clubs club= new Clubs("401","Music Club","Mathew","Connect through music, explore genres, and enjoy shared musical experiences.");
            String expected_output= club.getAdvisorName();
            String actual_output= "Mathew";
            assertEquals(expected_output,actual_output);
    }


    @Test
    void getDescription()
    {
        Clubs club= new Clubs("401","Music Club","Mathew","Connect through music, explore genres, and enjoy shared musical experiences.");
        String expected_output= club.getDescription();
        String actual_output= "Connect through music, explore genres, and enjoy shared musical experiences.";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getClubID()
    {
        Clubs club= new Clubs("401","Music Club","Mathew","Connect through music, explore genres, and enjoy shared musical experiences.");
        String expected_output= club.getClubID();
        String actual_output= "401";
        assertEquals(expected_output,actual_output);

    }
}
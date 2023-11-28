package com.example.scams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class studenttest {

    @Test
    void getStudentID()
    {
        student Student= new student("301","Daniel","Dan123", "Music Club");
        String expected_output= Student.getStudentID();
        String actual_output= "301";
        assertEquals(expected_output,actual_output);
    }


    @Test
    void getStudentName()
    {
        student Student= new student("301","Daniel","Dan123", "Music Club");
        String expected_output= Student.getStudentName();
        String actual_output= "Daniel";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getStudentPass()
    {
        student Student= new student("301","Daniel","Dan123", "Music Club");
        String expected_output= Student.getStudentPass();
        String actual_output= "Dan123";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getClubName()
    {
        student Student= new student("301","Daniel","Dan123", "Music Club");
        String expected_output= Student.getClubName();
        String actual_output= "Music Club";
        assertEquals(expected_output,actual_output);
    }
}
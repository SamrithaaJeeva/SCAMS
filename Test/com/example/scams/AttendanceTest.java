package com.example.scams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    @Test
    void getStudentName()
    {
        Attendance attendance= new Attendance("Daniel","Present","Meeting","Opening Moves Meetup");
        String expected_output= String.valueOf(attendance.getStudentName());
        String actual_output= "Daniel";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getAttendance()
    {
        Attendance attendance= new Attendance("Daniel","Present","Meeting","Opening Moves Meetup");
        String expected_output= attendance.getAttendance();
        String actual_output= "Present";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getType()
    {
        Attendance attendance= new Attendance("Daniel","Present","Meeting","Opening Moves Meetup");
        String expected_output= attendance.getType();
        String actual_output= "Meeting";
        assertEquals(expected_output,actual_output);
    }

    @Test
    void getActivityName()
    {
        Attendance attendance= new Attendance("Daniel","Present","Meeting","Opening Moves Meetup");
        String expected_output= attendance.getActivityName();
        String actual_output= "Opening Moves Meetup";
        assertEquals(expected_output,actual_output);
    }
}
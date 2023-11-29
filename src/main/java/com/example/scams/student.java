package com.example.scams;

public class student {
    private String studentID;
    private String studentName;
    private String studentPass;
    private Clubs clubName; //change to club



    public student(String studentID, String studentName, String studentPass, Clubs clubName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentPass= studentPass;
        this.clubName=clubName;
    }
    student(String studentName)
    {
        this.studentName=studentName; //needed for the attendance class
    }
    @Override
    public String toString() {
        return studentName; //needed for attendance
    }

    // Getters and setters

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }
    public String getStudentPass(){return studentPass;}
    public Clubs getClubName() { return clubName;}


    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentPass(String studentPass) {
        this.studentName = studentPass;
    }
    public void setClubName(Clubs clubName) { this.clubName = clubName; }








}







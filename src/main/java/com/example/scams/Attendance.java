package com.example.scams;

public class Attendance {

       private String studentID;
       private String studentName;
       private String attendance;
       private String type; // "Meeting" or "Event"
       private String activityName; // Meeting name or Event name

       public Attendance(String studentID, String studentName, String attendance, String type, String activityName) {
           this.studentID = studentID;
           this.studentName = studentName;
           this.attendance = attendance;
           this.type = type;
           this.activityName = activityName;
       }

       // Getters and setters

       public String getStudentID() {
           return studentID;
       }

       public String getStudentName() {
           return studentName;
       }

       public String getAttendance() {
           return attendance;
       }

       public String getType() {
           return type;
       }

       public String getActivityName() {
           return activityName;
       }

       public void setStudentID(String studentID) {
           this.studentID = studentID;
       }

       public void setStudentName(String studentName) {
           this.studentName = studentName;
       }

       public void setAttendance(String attendance) {
           this.attendance = attendance;
       }

       public void setType(String type) {
           this.type = type;
       }

       public void setActivityName(String activityName) {
           this.activityName = activityName;
       }
   }





package com.example.scams;

import java.util.Objects;

public class Attendance {
       private student studentName;
       private String attendance;
       private String type; //
       private String activityName; //

       public Attendance( String studentName, String attendance, String type, String activityName) {
           this.studentName = new student(studentName);
           this.attendance = attendance;
           this.type = type;
           this.activityName = activityName;
       }

       public student getStudentName() {
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


       public void setStudentName(student studentName) {
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






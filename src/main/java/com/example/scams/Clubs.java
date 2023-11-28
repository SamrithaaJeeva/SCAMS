package com.example.scams;

public class Clubs {

    private String ClubID;
    private String ClubName;
    private String AdvisorName;

    public void setClubID(String clubID) {
        ClubID = clubID;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getAdvisorName() {
        return AdvisorName;
    }

    public void setAdvisorName(String advisorName) {
        AdvisorName = advisorName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description;
    Clubs(String ClubID, String ClubName, String AdvisorName , String Description)
    {
        this.ClubID=ClubID;
        this.ClubName =ClubName;
        this.AdvisorName=AdvisorName;
        this.Description=Description;

    }

    public String getClubID() {
        return ClubID;
    }

}


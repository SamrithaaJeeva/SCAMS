package com.example.scams;

public class Clubs {

    //Create club.txt profile variables
    private int ClubID;
    private String ClubName;
    private String AdvisorName;
    private String Description;

    //Edit club.txt profile variables
    private int EditID;
    private String EditName;
    private String EditAdvisorName;
    private String EditDescription;

    //Delete club.txt profile Variables

    private String DeleteClubID;

    // Getter and setters of deleteClub
    public String getDeleteClubID() {
        return DeleteClubID;
    }

    public void setDeleteClubID(String deleteClubID) {
        DeleteClubID = deleteClubID;
    }

    //Edit club.txt profile getters and setters
   public int getEditID(){return EditID;}

    public void setEditID(int editID) {
        EditID = editID;
    }

    public String getEditName() {
        return EditName;
    }

    public void setEditClubName(String EditName) {
        EditName = EditName;
    }

    public String getEditAdvisorName() {
        return EditAdvisorName;
    }

    public void setEditAdvisorName(String EditAdvisorName) {
        EditAdvisorName = EditAdvisorName;
    }

    public String getEditDescription() {
        return EditDescription;
    }

    public void setEditDescription(String EditDescription) {
        EditDescription = EditDescription;
    }




    //Create club.txt profile getters and setters

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int clubID) {
        ClubID = clubID;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        ClubName = ClubName;
    }

    public String getAdvisorName() {
        return AdvisorName;
    }

    public void setAdvisorName(String AdvisorName) {
        AdvisorName = AdvisorName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        Description = Description;
    }

    public Clubs(int clubID,String ClubName, String AdvisorName, String Description) {
        this.ClubID=clubID;
        this.ClubName = ClubName;
        this.AdvisorName = AdvisorName;
        this.Description = Description;
    }
    public Clubs(String ClubName){
        this.ClubName=ClubName;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clubs clubs = (Clubs) o;
        return ClubName.equals(clubs.ClubName);
    }


    @Override
    public String toString() {
        return "Clubs{" +
                "ClubName='" + ClubName + '\'' +
                ", AdvisorName='" + AdvisorName + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}


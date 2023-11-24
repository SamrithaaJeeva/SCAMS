package com.example.scams;

public class Clubs {

    //Create club.txt profile variables
    private String ClubName;
    private String AdvisorName;
    private String Description;

    //Edit club.txt profile variables
    private String EditName;
    private String EditAdvisorName;
    private String EditDescription;

    //Delete club.txt profile Variables

    private String DeleteClubName;

    // Getter and setters of deleteClub
    public String getDeleteClubName() {
        return DeleteClubName;
    }

    public void setDeleteClubName(String deleteClubName) {
        DeleteClubName = deleteClubName;
    }




    //Edit club.txt profile getters and setters
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

    public Clubs(String ClubName, String AdvisorName, String Description) {
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


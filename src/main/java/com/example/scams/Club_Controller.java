package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;
import java.io.*;

public class Club_Controller
{
    @FXML
    private TextField ClubID;
    @FXML
    private TextField ClubName;
    @FXML
    private TextField AdvisorName;
    @FXML
    private TextField Description;

    @FXML
    private Button CreateClubProfileBtn;

    public static void main(String[] args) {
    }

    public void setClubs(Clubs clubs) {
        if(ClubID != null)
        {
            ClubID.setText(String.valueOf(clubs.getClubID()));
        }
        if (ClubName != null) {
            ClubName.setText(clubs.getClubName());
        }
        if (AdvisorName != null) {
            AdvisorName.setText(String.valueOf(clubs.getAdvisorName()));
        }
        if (Description != null) {
            Description.setText(clubs.getDescription());
        }

    }
    @FXML
    public void CreateClubProfileBtn(ActionEvent actionEvent) {
        if (ClubID==null||ClubName == null || AdvisorName == null || Description == null) {
            System.err.println("Error: text fields are null.");
            return;
        }

        int ID= Integer.parseInt(ClubID.getText());
        String name = ClubName.getText();
        String advisor_name = AdvisorName.getText();
        String description = Description.getText();


        if (name.isBlank() || advisor_name.isBlank() || description.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incomplete details. Please fill in all fields.");
            alert.showAndWait();
            return;
        }


        // Save the data to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("club.txt", true));
            writer.write(name + "," + advisor_name+ "," + description);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Club Details");
        alert.setHeaderText(null);
        alert.setContentText("Club profile details successfully added");
        alert.showAndWait();

        // Clear the text fields after saving
        ClubID.clear();
        AdvisorName.clear();
        Description.clear();
        ClubName.clear();

    }

   @FXML
   private TextField EditID;
    @FXML
    private TextField EditName;
    @FXML
    private TextField EditAdvisorName;
    @FXML
    private TextField EditDescription;
    @FXML
    private Button editClubProfileBtn;

    @FXML
    private void editClubProfileBtn(ActionEvent event) {
        int ID= Integer.parseInt(EditID.getText());
        String name =EditName.getText();
        String advisorName = EditAdvisorName.getText();
        String description = EditDescription.getText();

        if (name.isBlank() || advisorName.isBlank() || description.isBlank()) {
            showErrorAlert("Incomplete details. Please fill in all fields.");
            return;
        }


        showInfoAlert("Club profile details successfully added");

        // Clear the text fields after saving
        EditID.clear();
        EditName.clear();
        EditAdvisorName.clear();
        EditDescription.clear();
    }

    @FXML
    private void handleEditClubProfile(ActionEvent event) {
        int ID= Integer.parseInt(EditID.getText().trim());
        String name = EditName.getText().trim();
        String advisorName = EditAdvisorName.getText();
        String description = EditDescription.getText();

        if (name.isBlank() || advisorName.isBlank() || description.isBlank()) {
            showErrorAlert("Incomplete details. Please fill in all fields.");
            return;
        }

        try {
            File inputFile = new File("club.txt");
            File tempFile = new File("tempClub.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("Comparing: " + parts[0] + " with " + name);
                if (parts.length == 3 && parts[0].trim().equals(name)) {

                    line = name + "," + advisorName + "," + description;
                    found = true;
                }
                writer.write(line + System.lineSeparator());
            }


            reader.close();
            writer.close();

            if (!found) {
                showErrorAlert("Club not found for editing.");
                return;
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                showErrorAlert("Error updating club details.");
                return;
            }

            showInfoAlert("Club profile details successfully updated");

            // Clear the text fields after updating
            EditID.clear();
            EditName.clear();
            EditAdvisorName.clear();
            EditDescription.clear();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            showErrorAlert("Error reading/writing club details.");
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Club Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //Deleteclub
    @FXML
    private TextField DeleteID;

    public void DeleteClub(ActionEvent event) {
        String deleteclub = DeleteID.getText().trim();
        if (deleteclub.isEmpty() || !deleteclub.matches("\\d+")) {
            System.out.println("Not a valid Club ID");
            return;
        }
    }

}

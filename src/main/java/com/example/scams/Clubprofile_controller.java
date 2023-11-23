package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class Clubprofile_controller
{
    @FXML
    private TextField ClubName;
    @FXML
    private TextField AdvisorName;
    @FXML
    private TextField Description;

    @FXML
    private Button CreateClubProfileBtn;


    // Manage Club profiles
    @FXML
    private Button ManageeditClubProfileButton;

    @FXML
    private Button ManagedeleteClubProfileButton;

    // End Of manage UI


    // Manage Profile
    @FXML
    private void handleEditClubProfile(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditClub.fxml"));
            Parent root = loader.load();
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the stage from the current event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }


    @FXML
    private void handleDeleteClubProfile(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete.fxml"));
            Parent root = loader.load();
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the stage from the current event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // Set the new scene on the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    // End of Navigate Profile

    public static void main(String[] args) {
    }

    public void setClubs(Clubs clubs) {
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
        if (ClubName == null || AdvisorName == null || Description == null) {
            System.err.println("Error: text fields are null.");
            return;
        }

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
        AdvisorName.clear();
        Description.clear();
        ClubName.clear();

    }
    public Button getCreateClubProfileBtn() {
        return CreateClubProfileBtn;
    }

    public void setCreateClubProfileBtn(Button createClubProfileBtn) {
        CreateClubProfileBtn = createClubProfileBtn;
    }
/*
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
        String name =EditName.getText();
        String advisorName = EditAdvisorName.getText();
        String description = EditDescription.getText();

        if (name.isBlank() || advisorName.isBlank() || description.isBlank()) {
            showErrorAlert("Incomplete details. Please fill in all fields.");
            return;
        }


        showInfoAlert("Club profile details successfully added");

        // Clear the text fields after saving
        EditName.clear();
        EditAdvisorName.clear();
        EditDescription.clear();
    }

    @FXML
    private void handleEditClubProfile(ActionEvent event) {
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
        String name =EditName.getText();
        String advisorName = EditAdvisorName.getText();
        String description = EditDescription.getText();

        if (name.isBlank() || advisorName.isBlank() || description.isBlank()) {
            showErrorAlert("Incomplete details. Please fill in all fields.");
            return;
        }


        showInfoAlert("Club profile details successfully added");

        // Clear the text fields after saving
        EditName.clear();
        EditAdvisorName.clear();
        EditDescription.clear();
    }

    @FXML
    private void handleEditClubProfile(ActionEvent event) {
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
            EditName.clear();
            EditAdvisorName.clear();
            EditDescription.clear();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            showErrorAlert("Error reading/writing club details.");
        }
    }
*/
}
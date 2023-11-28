package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.scams.Connector.password;

public class Club_Controller

{
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private ChoiceBox<String> EditID;
    @FXML
    private TextField EditName;
    @FXML
    private TextField EditAdvisorName;
    @FXML
    private TextField EditDescription;

    private Connection con;  // Assuming con is initialized elsewhere in your code

    public void Connect() {
        con = Connector.connection();
    }

    @FXML
    private void initialize() {
        Connect();
        updateclubID();
    }

        private void updateclubID() {
        try {
            String query = "SELECT Club_ID FROM club";
            PreparedStatement pat = con.prepareStatement(query);
            ResultSet resultSet = pat.executeQuery();

            // Clear existing items before adding new ones
            EditID.getItems().clear();

            while (resultSet.next()) {
                String EditId = resultSet.getString("Club_ID");
                EditID.getItems().add(EditId);
            }
        } catch (SQLException e) {
            System.out.println("Not printed event names");
            throw new RuntimeException(e);

        }
    }
    private void showAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.showAndWait();
    }


    @FXML
    private void editClubProfileBtn(ActionEvent event) {
        try {
            // Ensure that the connection is not null
            if (con == null) {
                Connect();
                if (con == null) {
                    System.err.println("Failed to establish a database connection.");
                    return;
                }
            }

            String clubIdText = EditID.getValue();
            if (clubIdText.isEmpty()) {
                showAlert("Club ID is not valid");
                return;
            }

            String clubNameText = EditName.getText();
            if (clubNameText.isEmpty()) {
                showAlert("Club Name is required");
                return;
            }

            String advisorNameText = EditAdvisorName.getText();
            if (advisorNameText.isEmpty()) {
                showAlert("Advisor Name is required");
                return;
            }

            String descriptionText = EditDescription.getText();
            if (descriptionText.isEmpty()) {
                showAlert("Description is required");
                return;
            }

            String updateQuery = "UPDATE club SET Club_Name = ?, ClubAdvisor_Name = ?, Club_Description = ? WHERE Club_ID = ?";
            PreparedStatement pat = con.prepareStatement(updateQuery);

            pat.setString(1, clubNameText);
            pat.setString(2, advisorNameText);
            pat.setString(3, descriptionText);
            pat.setString(4, clubIdText);

            int rowsAffected = pat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("No record updated");
            }

            showAlert("Club Edited Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //Delete club Profile
    @FXML
        private void DeleteClub(ActionEvent event) {
            try {
                // Ensure that the connection is not null and is open
                if (con == null || con.isClosed()) {
                    Connect();
                    if (con == null) {
                        System.err.println("Failed to establish a database connection.");
                        return;
                    }
                }

                // Assuming you have a ChoiceBox for club ID named deleteClubIDChoiceBox
                String selectedClubId = EditID.getValue();

                // Check if a club ID is selected
                if (selectedClubId == null || selectedClubId.isEmpty()) {
                    showAlert("Please select a club to delete.");
                    return;
                }

                String deleteQuery = "DELETE FROM club WHERE Club_ID = ?";
                PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);

                // Set the value for the parameter in the DELETE query
                deleteStatement.setString(1, selectedClubId);

                // Execute the DELETE query
                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Record deleted successfully");
                } else {
                    System.out.println("No record deleted");
                }
                showAlert("Club Deleted successfully");

                // Close resources
                deleteStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        //Navigation bar
        public void gotocreateclub (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("CreateClub.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoeditclub (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("EditClub.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoDeleteclub (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoviewclub (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoschedulemeeting (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("AddMeetings.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoviewmeeting (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("ViewMeetings.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoschedulevent (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("AddEvents.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoattendance (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public void gotoviewevents (ActionEvent event)throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("ViewEvents.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        public Button exit;
        public void exit () {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        }

    }

package com.example.scams;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;


public class RegisterController {

    @FXML
    private TextField studentID;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentPass;

    @FXML
    private CheckBox showPass;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private ChoiceBox<String> clubChoiceBox;


    private String[] Clubs = {"Music Club", "ToastMaster Club", "Chess Club", "Drama Club", "Video Game Club"};

    @FXML
    private Button register;

    private Connection connection = Connector.connection();
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void initialize() {
        if (clubChoiceBox != null) {
            clubChoiceBox.getItems().addAll(Clubs);
        } else {
            System.err.println("clubChoiceBox is null. Check your FXML file and controller association.");
        }
    }

    @FXML
    private void seePassword(ActionEvent event) {
        if (showPass.isSelected()) {
            // If selected, show the password
            confirmPass.setPromptText(confirmPass.getText());
            studentPass.setPromptText(studentPass.getText());
            confirmPass.clear();
            studentPass.clear();
        } else {
            // If not selected, hide the password
            confirmPass.setText(confirmPass.getPromptText());
            studentPass.setText(studentPass.getPromptText());
            confirmPass.setPromptText("");
            studentPass.setPromptText("");
        }

    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        // Get data from the form
        String id = studentID.getText();
        String name = studentName.getText();
        String password = studentPass.getText();
        String confirmPassword = confirmPass.getText();
        String selectedClub = clubChoiceBox.getValue();





        // Validate passwords
        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match. Please re-enter.");
            return;
        }

        // Validate club selection
        if (selectedClub == null || selectedClub.isEmpty()) {
            showAlert("Error", "Please select a club.");
            return;
        }
        // Validate other fields as needed (e.g., non-empty, length, format, etc.)
        if (id.isEmpty() || name.isEmpty()) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }

        // Store the data in the database
        saveToDatabase(id, name, password, selectedClub);
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


    }


    private void saveToDatabase(String id, String name, String password, String club) {
        // SQL query to insert data into the table
        String insertQuery = "INSERT INTO student (Student_ID, Student_Name, Password, Club_Name) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set values for the prepared statement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, club);

            // Execute the query
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void signInLink(ActionEvent event) {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignWithSCAMS.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current FXML window
            Stage currentStage = (Stage) register.getScene().getWindow();
            currentStage.close();

            // Show the new FXML window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToHome(ActionEvent event)throws IOException {
        // Load the new FXML file (assuming "home.fxml")

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    @FXML
    private void viewClub(ActionEvent event) {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewClub.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current FXML window
            Stage currentStage = (Stage) register.getScene().getWindow();
            currentStage.close();

            // Show the new FXML window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }





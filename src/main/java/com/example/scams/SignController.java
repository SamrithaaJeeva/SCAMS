package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class SignController {

    @FXML
    private TextField studentID;

    @FXML
    private PasswordField studentPass;

    @FXML
    private Button signIn;

    // Assuming you have a Connection object for database connectivity
    private Connection connection = Connector.connection();

    @FXML
    private void handleSignInButton(ActionEvent event) {
        // Get data from the form
        String id = studentID.getText();
        String password = studentPass.getText();

        // Validate inputs (example: non-empty fields)
        if (id.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both ID and password.");
            return;
        }

        // Additional validation logic can be added here based on your requirements


        // Authenticate user against the database
        authenticateUser(id, password);
    }

    private void showAlert(String title, String message ) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void authenticateUser(String id, String password) {
        // SQL query to check if the user credentials are valid
        String selectQuery = "SELECT * FROM student WHERE Student_ID = ? AND Password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            // Set values for the prepared statement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Authentication successful
                showAlert("Success", "Authentication successful!");
            } else {
                // Authentication failed
                showAlert("Error", "Invalid credentials. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void registerLink(ActionEvent event) {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterWithSCAMS.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Close the current FXML window
            Stage currentStage = (Stage) signIn.getScene().getWindow();
            currentStage.close();

            // Show the new FXML window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class RegisterController {

    @FXML
    private TextField studentID;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentPass;

    @FXML
    private ChoiceBox<String> clubChoiceBox;

    private String[] Clubs = {"Club 1" , "Club 2" , "Club 3"};

    @FXML
    private Button register;

    private Connection connection = Connector.connection();

    @FXML
    private void initialize() {
        clubChoiceBox.getItems().addAll(Clubs);
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        // Get data from the form
        String id = studentID.getText();
        String name = studentName.getText();
        String password = studentPass.getText();
        String selectedClub = clubChoiceBox.getValue();

        // Store the data in the database
        saveToDatabase(id, name, password, selectedClub);
    }

    private void saveToDatabase(String id, String name, String password, String club) {
        // SQL query to insert data into the table
        String insertQuery = "INSERT INTO members (stud_id, stud_name, stud_pass, stud_club) VALUES (?, ?, ?, ?)";

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
}

package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Scheduling_Controller {
    @FXML
    private TextField EventName;
    @FXML
    private DatePicker EventDate;

    @FXML
    public void setEventSubmit(ActionEvent meeting) {
        if (EventName == null || EventDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incomplete details. Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        String meetingNameText = EventName.getText();
        String meetingDateText = EventDate.getValue().toString();


        // Save the data to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Events.txt", true));
            writer.write(meetingNameText + "," + meetingDateText);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear the text fields after saving
        EventName.clear();
        EventDate.setValue(null);


        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Event Details");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Event details successfully added");

        // Wait for the alert to be closed
        Optional<ButtonType> result = successAlert.showAndWait();

        // Check if the "OK" button was clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Load the ViewEvents.fxml file and close the current scene
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEvents.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) EventName.getScene().getWindow(); // MeetingName is part of the current scene
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openScheduleMeetings(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMeetings.fxml"));
            Parent root = loader.load();
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the stage from the tableView
            Stage stage = (Stage) EventName.getScene().getWindow();
            // Set the new scene
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openScheduleEvents(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEvents.fxml"));
            Parent root = loader.load();
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the stage from the tableView
            Stage stage = (Stage) EventName.getScene().getWindow();
            // Set the new scene
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField MeetingName;
    @FXML
    private DatePicker MeetingDate;

    @FXML
    public void setMeetingSubmit(ActionEvent meeting) {
        if (MeetingName == null || MeetingDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Incomplete details. Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        String meetingNameText = MeetingName.getText();
        String meetingDateText = MeetingDate.getValue().toString();


        // Save the data to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Meetings.txt", true));
            writer.write(meetingNameText + "," + meetingDateText);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clear the text fields after saving
        MeetingName.clear();
        MeetingDate.setValue(null);


        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Meeting Details");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Meeting details successfully added");

        // Wait for the alert to be closed
        Optional<ButtonType> result = successAlert.showAndWait();

        // Check if the "OK" button was clicked
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Load the ViewMeetings.fxml file and close the current scene
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMeetings.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) MeetingName.getScene().getWindow(); // MeetingName is part of the current scene
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.example.scams;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Schmeeting_Controller {

    //Navigation bar
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void gotocreateclub(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CreateClub.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoeditclub(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("EditClub.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoDeleteclub(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Delete.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoviewclub(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoschedulemeeting(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddMeetings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoviewmeeting(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ViewMeetings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoschedulevent(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddEvents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoviewevents(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ViewEvents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoattendance(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public Button exit;
    public void exit(){
        Stage stage=(Stage)exit.getScene().getWindow();
        stage.close();
    }
    //Content

    @FXML
    private TextField meetingId;;
    @FXML
    private TextField meetingName;
    @FXML
    private DatePicker meetingDate;
    @FXML
    private ChoiceBox<String> clubID;

    private Connection con;  // Assuming con is initialized elsewhere in your code

    public void Connect() {
        con = Connector.connection();
    }

    @FXML
    private void initialize() {
        Connect();
        updateclubid();

    }

    @FXML
    private void submit(ActionEvent event) {
        try {
            // Ensure that the connection is not null
            if (con == null) {
                Connect();
                if (con == null) {
                    System.err.println("Failed to establish a database connection.");
                    return;
                }
            }

            String MeetingId = meetingId.getText();
            if (MeetingId.isEmpty()||!MeetingId.matches("\\d+")) {
                showAlert("Meeting ID is not valid");
                return;
            }
            String MeetingName = meetingName.getText();
            if (MeetingName.isEmpty()) {
                showAlert("Meeting Name is not valid");
                return;
            }

            String MeetingDate = String.valueOf(meetingDate.getValue());
            if (MeetingDate.isEmpty()) {
                showAlert("Meeting Date is required");
                return;
            }

            String ClubID = clubID.getValue();
            if(ClubID.isEmpty())
            {
                showAlert("ClubID is required");
            }

            PreparedStatement pat = con.prepareStatement( "INSERT INTO meetings(Meeting_ID, Meeting_Name, Meeting_Date, Club_ID) VALUES(?,?,?,?)");
            pat.setString(1, MeetingId);
            pat.setString(2, MeetingName);
            pat.setString(3, MeetingDate);
            pat.setString(4, ClubID);

            pat.executeUpdate();

            System.out.println("Record added");

            // Show a success popup
            showAlert("Meeting added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.showAndWait();
    }


    private void updateclubid() {
        try {
            String query = "SELECT Club_ID FROM club";
            PreparedStatement pat = con.prepareStatement(query);
            ResultSet resultSet = pat.executeQuery();

            // Clear existing items before adding new ones
            clubID.getItems().clear();

            while (resultSet.next()) {
                String eventName = resultSet.getString("Club_ID");
                clubID.getItems().add(eventName);
            }
        } catch (SQLException e) {
            System.out.println("Not printed meeting names");
            throw new RuntimeException(e);

        }
    }
}

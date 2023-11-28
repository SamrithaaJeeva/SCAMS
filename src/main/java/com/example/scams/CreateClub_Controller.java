package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateClub_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField ClubID;
    @FXML
    private TextField ClubName;
    @FXML
    private TextField AdvisorName;
    @FXML
    private TextField Description;

    private Connection con;  // Assuming con is initialized elsewhere in your code

    public void Connect() {
        con = Connector.connection();
    }


    @FXML
    private void CreateClubProfileBtn (ActionEvent event) {
        try {
            // Ensure that the connection is not null
            if (con == null) {
                Connect();
                if (con == null) {
                    System.err.println("Failed to establish a database connection.");
                    return;
                }
            }

            String clubIDText= ClubID.getText();
            if (clubIDText.isEmpty()||!clubIDText.matches("\\d+")) {
                showAlert("Club ID is not valid");
                return;
            }
            String clubNameText = ClubName.getText();
            if (clubNameText.isEmpty()) {
                showAlert("Club Name is required");
                return;
            }

            String Advisorname = AdvisorName.getText();
            if (Advisorname.isEmpty()) {
                showAlert("Advisor name is required");
                return;
            }

            String descriptionValue = Description.getText();
            if(descriptionValue.isEmpty()) {
                showAlert("Club description is required");
                return;
            }

            if (clubIDText!=null&&clubNameText!=null&&Advisorname!=null&& descriptionValue!=null)
            {
                PreparedStatement pat = con.prepareStatement( "INSERT INTO `club`(`Club_ID`, `Club_Name`, `ClubAdvisor_Name`, `Club_Description`)  VALUES(?,?,?,?)");
                pat.setString(1, clubIDText);
                pat.setString(2, clubNameText);
                pat.setString(3, Advisorname);
                pat.setString(4, descriptionValue);
                pat.executeUpdate();
                System.out.println("Record added");
                showAlert("Club created successfully");
            } else{System.out.println("Error");}
            ClubID.clear();
            ClubName.clear();
            AdvisorName.clear();
            Description.clear();

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
        Parent root = FXMLLoader.load(getClass().getResource("ViewClub.fxml"));
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


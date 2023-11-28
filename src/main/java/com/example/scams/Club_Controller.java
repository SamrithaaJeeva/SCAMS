package com.example.scams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField ClubID;
    @FXML
    private TextField ClubName;
    @FXML
    private TextField AdvisorName;
    @FXML
    private TextField Description;


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
                showAlert("Event ID is not valid");
                return;
            }
            String clubNameText = ClubName.getText();
            if (clubNameText.isEmpty()) {
                showAlert("Event Name is not valid");
                return;
            }

            String Advisorname = AdvisorName.getText();
            if (Advisorname.isEmpty()) {
                showAlert("Event date is required");
                return;
            }

            String descriptionValue = Description.getText();
            if(descriptionValue.isEmpty())
            {
                showAlert("ClubID is required");
            }

            PreparedStatement pat = con.prepareStatement( "INSERT INTO `club`(`Club_ID`, `Club_Name`, `ClubAdvisor_Name`, `Club_Description`)  VALUES(?,?,?,?)");
            pat.setString(1, clubIDText);
            pat.setString(2, clubNameText);
            pat.setString(3, Advisorname);
            pat.setString(4, descriptionValue);

            pat.executeUpdate();

            System.out.println("Record added");

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

            String EditText= EditID.getValue();
            if (EditText.isEmpty()) {
                showAlert("Event ID is not valid");
                return;
            }
            String EditNameText = EditName.getText();
            if (EditNameText.isEmpty()) {
                showAlert("Event Name is not valid");
                return;
            }

            String EditAdvisorText=EditAdvisorName.getText();
            if (EditAdvisorText.isEmpty()) {
                showAlert("Event date is required");
                return;
            }

            String EditDescriptionText = EditDescription.getText();
            if(EditDescriptionText.isEmpty())
            {
                showAlert("ClubID is required");
            }
            String updateQuery = "UPDATE club SET Club_Name = ?, ClubAdvisor_Name = ?, Club_Description = ? WHERE Club_ID = ?";
            PreparedStatement pat = con.prepareStatement(updateQuery);

           /* PreparedStatement pat = con.prepareStatement( "UPDATE club SET Club_Name = ?, ClubAdvisor_Name = ?, Club_Description = ? WHERE Club_ID = ?");
            PreparedStatement updateStatement = con.prepareStatement(pat);*/
            pat.setString(1,EditText);
            pat.setString(2, EditNameText);
            pat.setString(3, EditAdvisorText);
            pat.setString(4, EditDescriptionText);

            pat.executeUpdate();

            System.out.println("Record added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*
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

        String ID= ClubID.getText();
        String name = ClubName.getText();
        String advisor_name = AdvisorName.getText();
        String description = Description.getText();


        if (ID.isBlank()||name.isBlank() || advisor_name.isBlank() || description.isBlank()) {

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


    private Connection connection = Connector.connection();
    private void saveToDatabase(String ClubID, String ClubName, String AdvisorName , String Description) {
        // SQL query to insert data into the table
        String insertQuery = "INSERT INTO `club`(`Club_ID`, `Club_Name`, `ClubAdvisor_Name`, `Club_Description`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set values for the prepared statement
            preparedStatement.setString(1, ClubID);
            preparedStatement.setString(2, ClubName);
            preparedStatement.setString(3,AdvisorName );
            preparedStatement.setString(4,Description );

            // Execute the query
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        saveToDatabase( ClubID, ClubName, AdvisorName, Description);
    }



    @FXML
    private void editClubProfileBtn(ActionEvent event) {
        String ID= EditID.getText();
        String name =EditName.getText();
        String advisorName = EditAdvisorName.getText();
        String description = EditDescription.getText();

        if (ID.isBlank()||name.isBlank() || advisorName.isBlank() || description.isBlank()) {
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
        String ID= EditID.getText().trim();
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
    */

    //Navigation bar
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
    public void gotoattendance(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
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
    public Button exit;
    public void exit(){
        Stage stage=(Stage)exit.getScene().getWindow();
        stage.close();
    }

}

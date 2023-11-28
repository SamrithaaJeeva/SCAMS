package com.example.scams;

import java.awt.*;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static java.lang.Class.forName;

public class Attendance_Controller extends Component {
    private Stage stage;
    private Scene scene;

    //Navigation bar
    public void gotocreateclub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateClub.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoeditclub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditClub.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoDeleteclub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Delete.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoviewclub(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewClub.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoschedulemeeting(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddMeetings.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoviewmeeting(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewMeetings.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoschedulevent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddEvents.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoviewevents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewEvents.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Button exit;

    public void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TableView<Attendance> table1;

    @FXML
    private TableColumn<Attendance, String> Studentnamecolumn;

    @FXML
    private TableColumn<Attendance, String> eventnamecolumn;

    @FXML
    private TableColumn<Attendance, String> attendanceStringTableColumn;
    @FXML
    private TableColumn<Attendance, String> typecolumn;

    @FXML
    private ObservableList<Attendance> data = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> activityname;
    @FXML
    private ChoiceBox<String> names;

    @FXML
    private ChoiceBox<String> Type; //Stores the activity type
    private String[] type = {"Meeting", "Event"};

    @FXML
    private ChoiceBox<String> Attendance; //Stores the type of attendance

    private String[] presence = {"Present", "Absent"};

    private Connection con;

    public void Connect() {
        con = Connector.connection();
    }
    private String selectedEvent; //stores the selected event from the activityname choice box
    private String selectedMeeting; //stores the selected meeting from the activityname choice box

    @FXML
    private void initialize() {
        Connect();
        Type.getItems().addAll(type);
        Attendance.getItems().addAll(presence);

        Type.valueProperty().addListener((observable, oldValue, newValue) -> {
            //checks if the activity type is meeting or event if so it updates the activityname choice box with the meeting names or event names and the student name choice box with the relevant student names
            if ("Meeting".equals(newValue)) {
                updateMeetingNames();
                updatestudentmeetingNames();

            } else {
                updateEventNames();
                updatestudentNames();

            }
        });// updates when the type choice box changes


        //updates when the activity name choice box changes
        activityname.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Meeting".equals(Type.getValue())) {
                selectedMeeting = newValue;
                updatestudentmeetingNames();
            } else {
                selectedEvent = newValue;
                updatestudentNames();
            }
        });
    }

    @FXML
    private void submit(ActionEvent event)  {
        try {

            if (con == null) {
                Connect();

                if (con == null) {
                    System.err.println("Failed to establish a database connection.");
                    return;
                }
            }
            String studnames=names.getValue();
            if(studnames==null||studnames.isEmpty()){showAlert("Not a valid student name");
            return;}

            String type = Type.getValue();
            if (type == null || type.isEmpty())
            {
                showAlert("Not a valid type");
                return;
            }
            String activitynameValue = activityname.getValue();
            if (activitynameValue == null || activitynameValue.isEmpty()) {
                showAlert("Activity name is required");
                return;
            }

            String attendanceValue = Attendance.getValue();
            if(attendanceValue == null||attendanceValue.isEmpty())
            {
                showAlert("Attendance is required");
                return;
            }


            PreparedStatement pat = con.prepareStatement("INSERT INTO `attendance`(`Activity_Name`, `Student_Name`, `Attendance`, `Type`)VALUES(?,?,?,?)");
            pat.setString(1, activitynameValue);
            pat.setString(2,studnames );
            pat.setString(3, attendanceValue);
            pat.setString(4, type);

            int rowsAffected = pat.executeUpdate();
            if (rowsAffected == 1) {
                // Update the table data
                loadDataToTable();
                System.out.println("Record added");
                showAlert("Student is marked");
            } else {
                System.out.println("Error, please try again");
            }
            names.setValue(null);
            activityname.setValue(null);
            Type.setValue(null);
            Attendance.setValue(null);

        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { //this is used for unique validations
                showAlert("This student has already been marked for this activity");
                names.setValue(null);
                activityname.setValue(null);
                Type.setValue(null);
                Attendance.setValue(null);
            }else{
                e.printStackTrace();
            }
        }
    }
    private void showAlert(String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("IMPORTANT");
        alert.setHeaderText(title);
        alert.showAndWait();
    }

    private void loadDataToTable() {
        try {
            // Clear existing data
            data.clear();

            PreparedStatement pat = con.prepareStatement("SELECT * FROM attendance ORDER BY Activity_Name;");
            ResultSet rs = pat.executeQuery();

            while (rs.next()) {
                Attendance attendance = new Attendance(
                        rs.getString("Student_Name"),
                        rs.getString("Activity_Name"),
                        rs.getString("Attendance"),
                        rs.getString("Type"));

                // Add the new data to the list
                data.add(attendance);
            }

            // Set the items in data to the table view
            Platform.runLater(() -> table1.setItems(data));

            //the cell values are initiated
            Studentnamecolumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
            attendanceStringTableColumn.setCellValueFactory(new PropertyValueFactory<>("attendance"));
            eventnamecolumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
            typecolumn.setCellValueFactory(new PropertyValueFactory<>("type"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEventNames() {
        try {
            String query = "SELECT Event_Name FROM events";
            PreparedStatement pat = con.prepareStatement(query);
            ResultSet resultSet = pat.executeQuery();

            activityname.getItems().clear();

            while (resultSet.next()) {
                String eventName = resultSet.getString("Event_Name");
                activityname.getItems().add(eventName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    private void updateMeetingNames() {
        try {
            String query = "SELECT Meeting_Name FROM meetings";
            PreparedStatement pat = con.prepareStatement(query);
            ResultSet resultSet = pat.executeQuery();

            activityname.getItems().clear();

            while (resultSet.next()) {
                String meetingName = resultSet.getString("Meeting_Name");
                activityname.getItems().add(meetingName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updatestudentNames() {
        try {
            if (selectedEvent == null) {
                return;
            }
            String query = "SELECT DISTINCT s.Student_Name FROM student s JOIN club c ON s.Club_Name = c.Club_Name JOIN events e ON c.Club_ID = e.Club_ID WHERE e.Event_Name = ?";
            PreparedStatement pat = con.prepareStatement(query);
            pat.setString(1, selectedEvent);
            ResultSet resultSet = pat.executeQuery();
            names.getItems().clear();

            while (resultSet.next()) {
                String studentName = resultSet.getString("Student_Name");
                names.getItems().add(studentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Student names are not loaded");
        }
    }

    private void updatestudentmeetingNames() {
        try {
            if (selectedMeeting== null) {
                return;
            }

            String query = "SELECT DISTINCT s.Student_Name FROM student s JOIN club c ON s.Club_Name = c.Club_Name JOIN meetings m ON c.Club_ID = m.Club_ID WHERE m.Meeting_Name = ?";
            PreparedStatement pat = con.prepareStatement(query);
            pat.setString(1, selectedMeeting);
            ResultSet resultSet = pat.executeQuery();
            names.getItems().clear();

            while (resultSet.next()) {
                String studentName = resultSet.getString("Student_Name");
                names.getItems().add(studentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Student names are not loaded");
        }
    }


}



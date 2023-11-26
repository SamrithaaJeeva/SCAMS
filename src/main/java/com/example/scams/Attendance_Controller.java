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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View.fxml")));
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
    private TableColumn<Attendance, String> StudentIDcolumn;

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
    private TextField studid;

    @FXML
    private ChoiceBox<String> activityname;
    @FXML
    private ChoiceBox<String> Type;
    private String[] type = {"Meeting", "Event"};

    @FXML
    private TextField studname;

    @FXML
    private ChoiceBox<String> Attendance;

    private String[] presence = {"Present", "Absent"};

    private Connection con;  // Assuming con is initialized elsewhere in your code

    public void Connect() {
        con = Connector.connection();
    }

    @FXML
    private void initialize() {
        Connect();
        Type.getItems().addAll(type);
        Attendance.getItems().addAll(presence);

        // Set up a listener for the Type ChoiceBox value
        Type.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Meeting".equals(newValue)) {
                updateMeetingNames();
            } else {
                updateEventNames();
            }
        });
    }

    @FXML
    private void button(ActionEvent event) {
        try {
            // Ensure that the connection is not null
            if (con == null) {
                // Attempt to connect (you might want to handle this more gracefully in a real application)
                Connect();

                if (con == null) {
                    System.err.println("Failed to establish a database connection.");
                    return;
                }
            }

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

            String studentID = studid.getText();
            if (studentID.isEmpty()||!studentID.matches("\\d+")) {
                showAlert("Student ID is not valid");
                return;
            }
            String studentName = studname.getText();
            if(studentName.isEmpty())
            {
                showAlert("Student Name is required");
                return;
            }
            String attendanceValue = Attendance.getValue();
            if(attendanceValue.isEmpty())
            {
                showAlert("Attendance is required");
            }


            PreparedStatement pat = con.prepareStatement("INSERT INTO `attendance`(`Student_ID`, `Activity_Name`, `Student_Name`, `Attendance`, `Type`) VALUES(?,?,?,?,?)");
            pat.setString(1, studentID);
            pat.setString(2, activitynameValue);
            pat.setString(3, studentName);
            pat.setString(4, attendanceValue);
            pat.setString(5, type);

            int rowsAffected = pat.executeUpdate();
            if (rowsAffected == 1) {
                // Update the table data
                loadDataToTable();
                System.out.println("Record added");
            } else {
                System.out.println("Error, please try again");
            }
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
    public void newbutton(ActionEvent event) {
        studid.setText("");
        studname.setText("");
    }

    private void loadDataToTable() {
        try {
            // Clear existing data
            data.clear();

            PreparedStatement pat = con.prepareStatement("SELECT * FROM attendance");
            ResultSet rs = pat.executeQuery();

            while (rs.next()) {
                String studentID = rs.getString("Student_ID");
                String activityName = rs.getString("Activity_Name");
                String studentName = rs.getString("Student_Name");
                String attendanceValue = rs.getString("Attendance");
                String type = rs.getString("Type");
                // Assuming you have an Attendance class, replace with your actual class
                Attendance attendance = new Attendance(
                        rs.getString("Student_ID"),
                        rs.getString("Activity_Name"),
                        rs.getString("Student_Name"),
                        rs.getString("Attendance"),
                        rs.getString("Type"));

                // Add the new data to the observable list
                data.add(attendance);
            }

            // Set the items (data) to the table on the JavaFX Application Thread
            Platform.runLater(() -> table1.setItems(data));

            // Set the cell value factories for each column
            StudentIDcolumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
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

            // Clear existing items before adding new ones
            activityname.getItems().clear();

            while (resultSet.next()) {
                String eventName = resultSet.getString("Event_Name");
                activityname.getItems().add(eventName);
            }
        } catch (SQLException e) {
            System.out.println("Not printed event names");
            throw new RuntimeException(e);

        }
    }

    private void updateMeetingNames() {
        try {
            String query = "SELECT Meeting_Name FROM meetings";
            PreparedStatement pat = con.prepareStatement(query);
            ResultSet resultSet = pat.executeQuery();

            // Clear existing items before adding new ones
            activityname.getItems().clear();

            while (resultSet.next()) {
                String meetingName = resultSet.getString("Meeting_Name");
                activityname.getItems().add(meetingName);
            }
        } catch (SQLException e) {
            System.out.println("Not printed event meetings");
            throw new RuntimeException(e);
        }
    }

}



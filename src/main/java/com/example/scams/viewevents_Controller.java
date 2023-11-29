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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Neri
public class viewevents_Controller {
    //Navigation bar
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void gotocreateclub(ActionEvent event)throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource("ViewClub.fxml"));
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
    @FXML
    private TableView<Events> eventtable;

    @FXML
    private TableColumn<Events, String> eventIDcolumn;

    @FXML
    private TableColumn<Events, String> eventnamecolumn;

    @FXML
    private TableColumn<Events, String> eventdatecolumn;

    @FXML
    private TableColumn<Events, String> clubIDcolumn;


    @FXML
    private ObservableList<Events> data = FXCollections.observableArrayList();

    private Connection con;  // Assuming con is initialized elsewhere in your code

    public void Connect() {
        con = Connector.connection();
    }

    @FXML
    private void initialize() {
        Connect();
    }
    @FXML
    private void loadDataToTable(ActionEvent event) {
        try {
            // Clear existing data
            data.clear();

            PreparedStatement pat = con.prepareStatement("SELECT * FROM events");
            ResultSet rs = pat.executeQuery();

            while (rs.next()) {
                Events events = new Events(
                        rs.getString("Event_ID"),
                        rs.getString("Event_Name"),
                        rs.getString("Event_Date"),
                        rs.getString("Club_ID"));

                // Add the new data to the observable list
                data.add(events);
            }

            // Set the items (data) to the table on the JavaFX Application Thread
            Platform.runLater(() -> eventtable.setItems(data));

            // Set the cell value factories for each column
            eventIDcolumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
            eventnamecolumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
            eventdatecolumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
            clubIDcolumn.setCellValueFactory(new PropertyValueFactory<>("clubID"));

        } catch (SQLException e) {
            System.out.println("Error here");
            e.printStackTrace();
        }
    }


}
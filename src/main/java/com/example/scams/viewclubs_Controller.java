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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class viewclubs_Controller {
    @FXML
    private TableView<Clubs> clubtable;

    @FXML
    private TableColumn<Clubs, String> clubIDcolumn;

    @FXML
    private TableColumn<Clubs, String> clubnamecolumn;

    @FXML
    private TableColumn<Clubs, String> descriptioncolumn;

    @FXML
    private TableColumn<Clubs, String> advisornameolumn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ObservableList<Clubs> data = FXCollections.observableArrayList();

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

            PreparedStatement pat = con.prepareStatement("SELECT * FROM club");
            ResultSet rs = pat.executeQuery();

            while (rs.next()) {
                Clubs clubs = new Clubs(
                        rs.getString("Club_ID"),
                        rs.getString("Club_Name"),
                        rs.getString("ClubAdvisor_Name"),
                        rs.getString("Club_Description"));

                // Add the new data to the observable list
                data.add(clubs);
            }

            // Set the items (data) to the table on the JavaFX Application Thread
            Platform.runLater(() -> clubtable.setItems(data));

            // Set the cell value factories for each column
            clubIDcolumn.setCellValueFactory(new PropertyValueFactory<>("ClubID"));
            clubnamecolumn.setCellValueFactory(new PropertyValueFactory<>("ClubName"));
            advisornameolumn.setCellValueFactory(new PropertyValueFactory<>("AdvisorName"));
            descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

        } catch (SQLException e) {
            System.out.println("Error here");
            e.printStackTrace();
        }
    }
    @FXML
    private void navigateToHome(ActionEvent event)throws IOException {
        // Load the new FXML file (assuming "home.fxml")

        Parent root = FXMLLoader.load(getClass().getResource("SignWithSCAMS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}

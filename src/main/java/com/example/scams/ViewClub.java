package com.example.scams;

import com.example.scams.Connector;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewClub {

    @FXML
    private TextArea textArea;

    // Assuming Connector.connection() returns a valid Connection object
    private Connection connection = Connector.connection();

    @FXML
    public void initialize() {
        // Call this method when the FXML is loaded
        fetchAndDisplayData();
    }

    @FXML
    public void onClose() {
        // Close the database connection when needed (e.g., when the application is closing)
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    private void fetchAndDisplayData() {
        // SQL query to retrieve data from a table
        String sqlQuery = "SELECT Club_ID, Club_Name, ClubAdvisor_Name, Club_Description FROM club";

        try {
            // Get the database connection from the Connector
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            textArea.appendText("Club ID\tClub Name\tClub Advisor Name\tClub Description\n");

            // Process the result set and append data to the TextArea
            while (resultSet.next()) {
                String column1Value = resultSet.getString("Club_ID");
                String column2Value = resultSet.getString("Club_Name");
                String column3Value = resultSet.getString("ClubAdvisor_Name");
                String column4Value = resultSet.getString("Club_Description");

                // Append the data to the TextArea with column headings
                textArea.appendText(String.format("%-8s\t%-25s\t%-25s\t%s\n", column1Value, column2Value, column3Value, column4Value));
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., show an error message)
        }
    }
}


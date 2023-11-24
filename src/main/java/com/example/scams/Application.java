package com.example.scams;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
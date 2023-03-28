package com.example.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginRegist.fxml"));
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Quiz game");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(Parent p){
        scene.setRoot(p);
    }
    public static void main(String[] args) {
        launch();
    }
}
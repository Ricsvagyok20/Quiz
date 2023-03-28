package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController {
    public Button btnEditData;
    @FXML
    private Label lblMenu;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnMoreInfo;
    @FXML
    private Button btnStartQuiz;
    @FXML
    private Button btnLeaderboard;

    public void loadProfile(ActionEvent event) {
    }

    public void loadMoreInfo(ActionEvent event) {
    }

    public void loadStartQuiz(ActionEvent event) {

    }
    public void loadLeaderboard(ActionEvent event) {

    }

    public void loadAdminCRUD(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }
}

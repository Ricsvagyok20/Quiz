package com.example.quiz;

import com.example.quiz.modules.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController{
    @FXML
    public Button btnEditData;
    @FXML
    private Label lblMenu;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnStatistics;
    @FXML
    private Button btnStartQuiz;
    @FXML
    private Button btnLeaderboard;

    private Player currentPlayer;

    public void loadProfile(ActionEvent event) {
    }

    public void loadStatistics(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("statistics.fxml"));
        Parent fxml = fxmlLoader.load();

        StatisticsController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        HelloApplication.setRoot(fxml);
    }

    public void loadStartQuiz(ActionEvent event) {

    }
    public void loadLeaderboard(ActionEvent event) {

    }

    public void setPlayer(Player player){
        this.currentPlayer = player;
        if(currentPlayer.getUserName().equals("admin")){
            btnEditData.setVisible(true);
        }
        else{
            btnEditData.setVisible(false);
        }
    }

    public void loadAdminCRUD(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent fxml = fxmlLoader.load();

        adminListController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        HelloApplication.setRoot(fxml);
    }
}

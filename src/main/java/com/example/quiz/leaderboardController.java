package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class leaderboardController implements Initializable {
    @FXML private TableView<Player> leaderboardTable;
    @FXML private TableColumn<Player, String> playerNameColumn;
    @FXML private TableColumn<Player, Float> averageScoreColumn;
    @FXML private Button btnBack;
    private Player currentPlayer;
    IQuizDAO dao;

    public void Back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();

        controller.setPlayer(currentPlayer);

        HelloApplication.setRoot(fxml);
    }

    public void setData(Player player){
        this.currentPlayer = player;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        averageScoreColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));


        dao = new QuizDAO();

        try {
            Map<String, Float> ranking = dao.ranking();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

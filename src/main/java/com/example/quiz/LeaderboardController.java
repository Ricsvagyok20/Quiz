package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
import com.example.quiz.modules.RankingByUser;
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
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML private TableView<Player> leaderboardTable;
    @FXML private TableColumn<Player, String> playerNameColumn;
    @FXML private TableColumn<Player, Float> averageScoreColumn;
    @FXML private Button btnBack;
    private Player currentPlayer;
    IQuizDAO dao;

    public void Back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();

        controller.setPlayer(currentPlayer);

        QuizApp.setRoot(fxml);
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
            List<RankingByUser> ranking = dao.ranking();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

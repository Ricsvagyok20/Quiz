package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{
    @FXML public ComboBox<Integer> quizIdComboBox;
    @FXML public ListView<String> questionsListView;
    @FXML private Label lblStatistics;
    @FXML private Button btnBack;
    private IQuizDAO dao;
    private Player currentPlayer;
    private ObservableList<Integer> quizIds = FXCollections.observableArrayList();

    public void loadBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();
        controller.setPlayer(currentPlayer);

        HelloApplication.setRoot(fxml);
    }



    public void listQuestions(ActionEvent actionEvent) {
        try{
            var results = dao.questionsOfPlayedQuiz(quizIdComboBox.getValue());
            questionsListView.getItems().setAll(results);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setData(Player player){
        this.currentPlayer = player;
        List<Integer> quizzes = new ArrayList<>();
        try {
            quizzes = dao.playedQuizId(currentPlayer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        quizIds.addAll(quizzes);
        quizIdComboBox.getItems().setAll(quizIds);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
    }
}

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
    private URL url;
    private ResourceBundle resourceBundle;

    public void loadBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
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
        initialize(url, resourceBundle);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.currentPlayer != null) {
            dao = new QuizDAO();
            ObservableList<Integer> QuizId = FXCollections.observableArrayList();
            List<Integer> quiids;
            try {
                quiids = dao.playedQuizId(currentPlayer);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
            for (int i : quiids) {
                QuizId.add(i);
                quizIdComboBox.setValue(i);
            }
            quizIdComboBox.getItems().setAll(QuizId);
        } else {
            this.url = url;
            this.resourceBundle = resourceBundle;
        }
    }
}

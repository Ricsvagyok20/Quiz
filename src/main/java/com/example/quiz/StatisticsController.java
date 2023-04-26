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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{
    @FXML public ComboBox<Integer> quizIdComboBox;
    @FXML public ListView<String> questionsListView;
    @FXML private Label lblStatistics;
    @FXML private Button btnBack;
    private IQuizDAO dao;
    private Player currentPlayer;

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<Integer> QuizId = FXCollections.observableArrayList();
        var quiids = dao.getQuizzes();
        for (var i: quiids){
            QuizId.add(i.getQuizId());
            quizIdComboBox.setValue(i.getQuizId());
        }
        quizIdComboBox.getItems().setAll(QuizId);
    }
}

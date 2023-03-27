package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.quiz.modules.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCRUDController implements Initializable {

    @FXML private TableView<Quiz> tableViewQuiz;
    @FXML private TableColumn<Quiz, Integer> quizIdColumn;
    @FXML private TableColumn<Quiz, String> topicNameQuizColumn;
    @FXML private Button btnAddQuiz;
    @FXML private Button btnDeleteQuiz;
    @FXML private Button btnModifyQuiz;
    @FXML
    private TableView<Player> tableViewPlayer;
    @FXML
    private TableColumn<Player, String> userNameColumn;
    @FXML
    private TableColumn<Player, String> passwordColumn;
    @FXML
    private TableColumn<Player, String> emailColumn;
    @FXML
    private TableColumn<Player, Integer> rankingPointColumn;
    @FXML
    private TableColumn<Player, String> topicNameColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    private IQuizDAO dao;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        rankingPointColumn.setCellValueFactory(new PropertyValueFactory<>("rankingPoints"));
        topicNameColumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        quizIdColumn.setCellValueFactory(new PropertyValueFactory<>("quizId"));
        topicNameQuizColumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        dao = new QuizDAO();
        var players = dao.getPlayers();
        var quizes = dao.getQuizes();
        tableViewQuiz.getItems().setAll(quizes);
        tableViewPlayer.getItems().setAll(players);
    }

    public void btnAddAction(ActionEvent event) {

    }

    public void btnDeleteAction(ActionEvent event) {
    }

    public void btnModifyAction(ActionEvent event) {
    }

    public void btnAddQuizAction(ActionEvent event) {
    }

    public void btnDeleteQuizAction(ActionEvent event) {
    }

    public void btnModifyquizAction(ActionEvent event) {
    }
}

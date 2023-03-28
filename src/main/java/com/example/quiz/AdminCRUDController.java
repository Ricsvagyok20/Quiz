package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.*;
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
import java.util.PropertyPermission;
import java.util.ResourceBundle;

public class AdminCRUDController implements Initializable {

    public Button btnBack;
    @FXML private TableView<Play> tableViewPlay;
    @FXML private TableColumn<Play, String> userNamePlayColumn;
    @FXML private TableColumn<Play, Integer> quizIdPlayColumn;
    @FXML private Button btnAddPlay;
    @FXML private Button btnDeletePlay;
    @FXML private Button btnModifyPlay;
    @FXML private TableView<Question> tableViewQuestion;
    @FXML private TableColumn<Question, Integer> questionIdColumn;
    @FXML private TableColumn<Question, String> questionContentColumn;
    @FXML private TableColumn<Question, String> subtopicNameColumn;
    @FXML private Button btnAddQuestion;
    @FXML private Button btnDeleteQuestion;
    @FXML private Button btnModifyQuestion;
    @FXML private TableView<Answer> tableViewAnswer;
    @FXML private TableColumn<Answer, Integer> answerIdColumn;
    @FXML private TableColumn<Answer, String> answerContentColumn;
    @FXML private TableColumn<Answer, String> correctColumn;
    @FXML private Button btnAddAnswer;
    @FXML private Button btnDeleteAnswer;
    @FXML private Button btnModifyAnswer;
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

        userNamePlayColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        quizIdPlayColumn.setCellValueFactory(new PropertyValueFactory<>("quizId"));

        questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        questionContentColumn.setCellValueFactory(new PropertyValueFactory<>("questionContent"));
        subtopicNameColumn.setCellValueFactory(new PropertyValueFactory<>("subtopicName"));

        answerIdColumn.setCellValueFactory(new PropertyValueFactory<>("answerId"));
        answerContentColumn.setCellValueFactory(new PropertyValueFactory<>("answerContent"));
        correctColumn.setCellValueFactory(new PropertyValueFactory<>("correct"));
        dao = new QuizDAO();
        var players = dao.getPlayers();
        var quizzes = dao.getQuizzes();
        var plays = dao.getPlays();
        var questions = dao.getQuestions();
        var answers = dao.getAnswers();
        tableViewQuiz.getItems().setAll(quizzes);
        tableViewPlayer.getItems().setAll(players);
        tableViewPlay.getItems().setAll(plays);
        tableViewQuestion.getItems().setAll(questions);
        tableViewAnswer.getItems().setAll(answers);
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

    public void btnModifyquizAction(javafx.event.ActionEvent event) {
    }

    public void btnAddPlayAction(ActionEvent event) {
    }

    public void btnDeletePlayAction(ActionEvent event) {
    }

    public void btnModifyPlayAction(ActionEvent event) {
    }

    public void btnAddQuestionAction(ActionEvent event) {
    }

    public void btnDeleteQuestionAction(ActionEvent event) {
    }

    public void btnModifyQuestionAction(ActionEvent event) {
    }

    public void btnAddAnswerAction(ActionEvent event) {
    }

    public void btnDeleteAnswerAction(ActionEvent event) {
    }

    public void btnModifyAnswerAction(ActionEvent event) {
    }

    public void btnBackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }
}

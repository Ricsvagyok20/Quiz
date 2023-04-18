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
import java.util.ResourceBundle;

public class adminListController implements Initializable {

    public Button btnBack;
    public TableView<Ask> tableViewAsk;
    public TableColumn<Ask, Integer> questionIdAskColumn;
    public TableColumn<Ask, Integer> quizIdAskColumn;
    public Button btnAddAsk;
    public Button btnDeleteAsk;
    public Button btnModifyAsk;
    public TableView<Belong> tableViewBelong;
    public TableColumn<Belong, Integer> quizIdBelongColumn;
    public TableColumn<Belong, Integer> answerIdBelongColumn;
    public Button btnAddBelong;
    public Button btnDeleteBelong;
    public Button btnModifyBelong;
    public TableView<Topic> tableViewTopic;
    public TableColumn<Topic, String> topicNameColumn;
    public Button btnAddTopic;
    public Button btnDeleteTopic;
    public Button btnModifyTopic;
    public TableView<Subtopic> tableViewSubTopic;
    public TableColumn<Subtopic, String> subtopicNameColumn;
    public TableColumn<Subtopic, String> descriptionColumn;
    public TableColumn<Subtopic, String> topicNameSubtopicColumn;
    public Button btnAddSubtopic;
    public Button btnDeleteSubtopic;
    public Button btnModifySubtopic;
    @FXML private TableView<Play> tableViewPlay;
    @FXML private TableColumn<Play, String> userNamePlayColumn;
    @FXML private TableColumn<Play, Integer> quizIdPlayColumn;
    @FXML private Button btnAddPlay;
    @FXML private Button btnDeletePlay;
    @FXML private Button btnModifyPlay;
    @FXML private TableView<Question> tableViewQuestion;
    @FXML private TableColumn<Question, Integer> IdColumn;
    @FXML private TableColumn<Question, String> questionContentColumn;
    @FXML private TableColumn<Question, String> subtopicNameQuestionColumn;
    @FXML private Button btnAddQuestion;
    @FXML private Button btnDeleteQuestion;
    @FXML private Button btnModifyQuestion;
    @FXML private TableView<Answer> tableViewAnswer;
    @FXML private TableColumn<Answer, Integer> answerIdColumn;
    @FXML private TableColumn<Answer, Integer> questionIdColumn;
    @FXML private TableColumn<Answer, String> correctAnswerColumn;
    @FXML private TableColumn<Answer, String> answerContentColumn;
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
    private TableColumn<Player, String> topicNamePlayerColumn;
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
        topicNamePlayerColumn.setCellValueFactory(new PropertyValueFactory<>("topicNamePlayer"));

        quizIdColumn.setCellValueFactory(new PropertyValueFactory<>("quizId"));
        topicNameQuizColumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        userNamePlayColumn.setCellValueFactory(new PropertyValueFactory<>("userNamePlay"));
        quizIdPlayColumn.setCellValueFactory(new PropertyValueFactory<>("quizIdPlay"));

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        questionContentColumn.setCellValueFactory(new PropertyValueFactory<>("questionContent"));
        subtopicNameQuestionColumn.setCellValueFactory(new PropertyValueFactory<>("subtopicNameQuestion"));

        answerIdColumn.setCellValueFactory(new PropertyValueFactory<>("answerId"));
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        answerContentColumn.setCellValueFactory(new PropertyValueFactory<>("answerContent"));
        correctAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

        questionIdAskColumn.setCellValueFactory(new PropertyValueFactory<>("questionIdAsk"));
        quizIdAskColumn.setCellValueFactory(new PropertyValueFactory<>("quizIdAsk"));

        quizIdBelongColumn.setCellValueFactory(new PropertyValueFactory<>("quizIdBelong"));
        answerIdBelongColumn.setCellValueFactory(new PropertyValueFactory<>("answerIdBelong"));

        topicNameColumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));

        subtopicNameColumn.setCellValueFactory(new PropertyValueFactory<>("subtopicName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        topicNameSubtopicColumn.setCellValueFactory(new PropertyValueFactory<>("topicNameSubtopic"));

        dao = new QuizDAO();

        var players = dao.getPlayers();
        var quizzes = dao.getQuizzes();
        var plays = dao.getPlays();
        var questions = dao.getQuestions();
        var answers = dao.getAnswers();
        var asks = dao.getAsks();
        var belongs = dao.getBelongs();
        var topics = dao.getTopics();
        var subtopics = dao.getSubtopics();

        tableViewQuiz.getItems().setAll(quizzes);
        tableViewPlayer.getItems().setAll(players);
        tableViewPlay.getItems().setAll(plays);
        tableViewQuestion.getItems().setAll(questions);
        tableViewAnswer.getItems().setAll(answers);
        tableViewAsk.getItems().setAll(asks);
        tableViewBelong.getItems().setAll(belongs);
        tableViewTopic.getItems().setAll(topics);
        tableViewSubTopic.getItems().setAll(subtopics);
    }

    public void btnBackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnAddPlayerAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playerAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeletePlayerAction(ActionEvent event) {
        dao = new QuizDAO();
        Player tmp = tableViewPlayer.getSelectionModel().getSelectedItem();
        try{
            dao.deletePlayer(tmp.getUserName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnModifyPlayerAction(ActionEvent event) {
    }

    public void btnAddQuizAction(ActionEvent event) {
    }

    public void btnDeleteQuizAction(ActionEvent event) {
    }

    public void btnModifyQuizAction(javafx.event.ActionEvent event) {
    }

    public void btnAddPlayAction(ActionEvent event) {
    }

    public void btnDeletePlayAction(ActionEvent event) {
    }

    public void btnModifyPlayAction(ActionEvent event) {
    }

    public void btnAddQuestionAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questionAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
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

    public void btnAddAskAction(ActionEvent actionEvent) {
    }

    public void btnDeleteAskAction(ActionEvent actionEvent) {
    }

    public void btnModifyAskAction(ActionEvent actionEvent) {
    }

    public void btnAddBelongAction(ActionEvent actionEvent) {
    }

    public void btnDeleteBelongAction(ActionEvent actionEvent) {
    }

    public void btnModifyBelongAction(ActionEvent actionEvent) {
    }

    public void btnAddTopicAction(ActionEvent actionEvent) {
    }

    public void btnDeleteTopicAction(ActionEvent actionEvent) {
    }

    public void btnModifyTopicAction(ActionEvent actionEvent) {
    }

    public void btnAddSubtopicAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSubtopicAction(ActionEvent actionEvent) {
    }

    public void btnModifySubtopicAction(ActionEvent actionEvent) {
    }
}

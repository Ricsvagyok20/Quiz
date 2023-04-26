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

    @FXML private Button btnBack;
    @FXML private TableView<Ask> tableViewAsk;
    @FXML private TableColumn<Ask, Integer> questionIdAskColumn;
    @FXML private TableColumn<Ask, Integer> quizIdAskColumn;
    @FXML private Button btnAddAsk;
    @FXML private Button btnDeleteAsk;
    @FXML private Button btnModifyAsk;
    @FXML private TableView<RankingPoint> tableViewRankingPoint;
    @FXML private TableColumn<RankingPoint, String> userNameRankingPoint;
    @FXML private TableColumn<RankingPoint, String> topicNameRankingPoint;
    @FXML private TableColumn<RankingPoint, Integer> rankingPointColumn;
    @FXML private Button btnAddRankingPoint;
    @FXML private Button btnDeleteRankingPoint;
    @FXML private Button btnModifyRankingPoint;
    @FXML private TableView<Topic> tableViewTopic;
    @FXML private TableColumn<Topic, String> topicNameColumn;
    @FXML private Button btnAddTopic;
    @FXML private Button btnDeleteTopic;
    @FXML private Button btnModifyTopic;
    @FXML private TableView<Subtopic> tableViewSubTopic;
    @FXML private TableColumn<Subtopic, String> subtopicNameColumn;
    @FXML private TableColumn<Subtopic, String> descriptionColumn;
    @FXML private TableColumn<Subtopic, String> topicNameSubtopicColumn;
    @FXML private Button btnAddSubtopic;
    @FXML private Button btnDeleteSubtopic;
    @FXML private Button btnModifySubtopic;
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
    private TableColumn<Player, String> topicNamePlayerColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    private IQuizDAO dao;
    private Player currentPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
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

        userNameRankingPoint.setCellValueFactory(new PropertyValueFactory<>("userNameRankingPoint"));
        topicNameRankingPoint.setCellValueFactory(new PropertyValueFactory<>("topicNameRankingPoint"));
        rankingPointColumn.setCellValueFactory(new PropertyValueFactory<>("rankingPoint"));

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
        var rankingPoints = dao.getRankingPoints();
        var topics = dao.getTopics();
        var subtopics = dao.getSubtopics();

        tableViewQuiz.getItems().setAll(quizzes);
        tableViewPlayer.getItems().setAll(players);
        tableViewPlay.getItems().setAll(plays);
        tableViewQuestion.getItems().setAll(questions);
        tableViewAnswer.getItems().setAll(answers);
        tableViewAsk.getItems().setAll(asks);
        tableViewRankingPoint.getItems().setAll(rankingPoints);
        tableViewTopic.getItems().setAll(topics);
        tableViewSubTopic.getItems().setAll(subtopics);
    }

    public void setData(Player player){
        this.currentPlayer = player;
    }

    public void btnBackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();
        controller.setPlayer(currentPlayer);

        HelloApplication.setRoot(fxml);
    }

    public void btnAddPlayerAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playerAdd.fxml"));
        Parent fxml = fxmlLoader.load();

        PlayerAddController controller = fxmlLoader.getController();
        controller.setData(null);

        HelloApplication.setRoot(fxml);
    }

    public void btnDeletePlayerAction(ActionEvent event) throws IOException {
        dao = new QuizDAO();
        Player tmp = tableViewPlayer.getSelectionModel().getSelectedItem();
        try{
            dao.deletePlayer(tmp.getUserName());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyPlayerAction(ActionEvent event) throws IOException {
        dao = new QuizDAO();
        Player tmp = tableViewPlayer.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playerAdd.fxml"));
        Parent fxml = fxmlLoader.load();

        PlayerAddController controller = fxmlLoader.getController();
        controller.setData(tmp);

        HelloApplication.setRoot(fxml);
    }

    public void btnAddQuizAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteQuizAction(ActionEvent event) throws IOException {
        dao = new QuizDAO();
        Quiz tmp = tableViewQuiz.getSelectionModel().getSelectedItem();
        try{
            dao.deleteQuiz(tmp.getQuizId());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyQuizAction(javafx.event.ActionEvent event) throws IOException {
        /*dao = new QuizDAO();
        Quiz tmp = tableViewQuiz.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("quizAdd.fxml"));
        Parent fxml = fxmlLoader.load();

        QuizAddController controller = fxmlLoader.getController();
        controller.setData(tmp);

        HelloApplication.setRoot(fxml);*/
    }

    public void btnAddPlayAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addPlay.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeletePlayAction(ActionEvent event) throws IOException {
        dao = new QuizDAO();
        Play tmp = tableViewPlay.getSelectionModel().getSelectedItem();
        try{
            dao.deletePlay(tmp.getUserNamePlay(), tmp.getQuizIdPlay());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyPlayAction(ActionEvent event) {
    }

    public void btnAddQuestionAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("questionAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteQuestionAction(ActionEvent event) throws IOException{
        dao = new QuizDAO();
        Question tmp = tableViewQuestion.getSelectionModel().getSelectedItem();
        try{
            dao.deleteQuestion(tmp.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyQuestionAction(ActionEvent event) {
    }

    public void btnAddAnswerAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("answerAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteAnswerAction(ActionEvent event) throws IOException {
        dao = new QuizDAO();
        Answer tmp = tableViewAnswer.getSelectionModel().getSelectedItem();
        try{
            dao.deleteAnswer(tmp.getAnswerId());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyAnswerAction(ActionEvent event) {
    }

    public void btnAddAskAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addAsk.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteAskAction(ActionEvent actionEvent) throws IOException {
        dao = new QuizDAO();
        Ask tmp = tableViewAsk.getSelectionModel().getSelectedItem();
        try{
            dao.deleteAsk(tmp.getQuestionIdAsk(), tmp.getQuizIdAsk());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyAskAction(ActionEvent actionEvent) {
    }

    public void btnAddRankingPointAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addRankingPoint.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteRankingPointAction(ActionEvent actionEvent) throws IOException {
        dao = new QuizDAO();
        RankingPoint tmp = tableViewRankingPoint.getSelectionModel().getSelectedItem();
        try{
            dao.deleteRankingPoint(tmp.getUserNameRankingPoint(), tmp.getTopicNameRankingPoint());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyRankingPointAction(ActionEvent actionEvent) {
    }

    public void btnAddTopicAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("topicAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteTopicAction(ActionEvent actionEvent) throws IOException {
        dao = new QuizDAO();
        Topic tmp = tableViewTopic.getSelectionModel().getSelectedItem();
        try{
            dao.deleteTopic(tmp.getTopicName());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifyTopicAction(ActionEvent actionEvent) {
    }

    public void btnAddSubtopicAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("subtopicAdd.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnDeleteSubtopicAction(ActionEvent actionEvent) throws IOException {
        dao = new QuizDAO();
        Subtopic tmp = tableViewSubTopic.getSelectionModel().getSelectedItem();
        try{
            dao.deleteSubtopic(tmp.getSubtopicName());
        }catch (Exception e){
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void btnModifySubtopicAction(ActionEvent actionEvent) {
    }
}

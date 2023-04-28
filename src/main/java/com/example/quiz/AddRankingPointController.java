package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.RankingPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRankingPointController implements Initializable {
    @FXML private ChoiceBox<String> choicebUsername;
    @FXML private ChoiceBox<String> choicebTopic;
    @FXML private Spinner<Integer> spnPoints;
    @FXML private Label label;
    private IQuizDAO dao;
    private RankingPoint rpToModify = null;

    public void btnSaveAction(ActionEvent event) {
        String username = choicebUsername.getValue();
        String topic = choicebTopic.getValue();
        int rp = spnPoints.getValue();
        RankingPoint tmp = new RankingPoint(username, topic, rp);
        if(rpToModify == null) {
            try {
                dao.addRankingPoint(tmp);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            } catch (Exception e) {
                e.printStackTrace();
                label.setText(e.getMessage());
            }
        }else{
            try {
                dao.updateRankingPoints(tmp, rpToModify);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            } catch (Exception e) {
                e.printStackTrace();
                label.setText(e.getMessage());
            }
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        QuizApp.setRoot(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<String> topicsList = FXCollections.observableArrayList();
        var topics = dao.getTopics();
        for (var i: topics){
            topicsList.add(i.getTopicName());
            choicebTopic.setValue(i.getTopicName());
        }
        choicebTopic.getItems().setAll(topicsList);
        ObservableList<String> playerList = FXCollections.observableArrayList();
        var players = dao.getPlayers();
        for (var i: players){
            playerList.add(i.getUserName());
            choicebUsername.setValue(i.getUserName());
        }
        choicebUsername.getItems().setAll(playerList);
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        spnPoints.setValueFactory(spinner);
    }

    public void setData(RankingPoint tmp) {
        this.rpToModify = tmp;
    }
}

package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Ask;
import com.example.quiz.modules.Play;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPlayController implements Initializable {
    @FXML private ChoiceBox<String> choicebUsername;
    @FXML private ChoiceBox<Integer> choicebQuizid;
    @FXML private Label label;
    private IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) {
        String name = choicebUsername.getValue();
        int quiz = choicebQuizid.getValue();
        try {
            Play tmp = new Play(name, quiz);
            dao = new QuizDAO();
            dao.addPlay(tmp);
        } catch (Exception e) {
            e.printStackTrace();
            label.setText(e.getMessage());
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<String> Username = FXCollections.observableArrayList();
        var names = dao.getPlayers();
        for (var i: names){
            Username.add(i.getUserName());
            choicebUsername.setValue(i.getUserName());
        }
        ObservableList<Integer> QuizId = FXCollections.observableArrayList();
        var quiids = dao.getQuizzes();
        for (var i: quiids){
            QuizId.add(i.getQuizId());
            choicebQuizid.setValue(i.getQuizId());
        }
        choicebUsername.getItems().setAll(Username);
        choicebQuizid.getItems().setAll(QuizId);
    }
}

package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Ask;
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

public class AddAskController implements Initializable {
    @FXML private ChoiceBox<Integer> choicebQuestionid;
    @FXML private ChoiceBox<Integer> choicebQuizid;
    @FXML private Label label;

    private IQuizDAO dao;


    public void btnSaveAction(ActionEvent event) {
        int quest = choicebQuestionid.getValue();
        int quiz = choicebQuizid.getValue();
        try {
            Ask tmp = new Ask(quest, quiz);
            dao.addAsk(tmp);

            FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
            Parent p = fxmlLoader.load();
            QuizApp.setRoot(p);
        } catch (Exception e) {
            e.printStackTrace();
            label.setText(e.getMessage());
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
        ObservableList<Integer> QuestionId = FXCollections.observableArrayList();
        var ids = dao.getQuestions();
        for (var i: ids){
            QuestionId.add(i.getId());
            choicebQuestionid.setValue(i.getId());
        }
        ObservableList<Integer> QuizId = FXCollections.observableArrayList();
        var quiids = dao.getQuizzes();
        for (var i: quiids){
            QuizId.add(i.getQuizId());
            choicebQuizid.setValue(i.getQuizId());
        }
        choicebQuestionid.getItems().setAll(QuestionId);
        choicebQuizid.getItems().setAll(QuizId);
    }
}

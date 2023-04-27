package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Quiz;
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

public class QuizAddController implements Initializable {
    @FXML private ChoiceBox<String> choicebTopicname;
    @FXML private Label label;
    IQuizDAO dao;
    private Quiz quiz;

    public void btnSaveAction(ActionEvent event) {
        if(quiz == null) {
            try {
                Quiz tmp = new Quiz(choicebTopicname.getValue());
                dao.addQuiz(tmp);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            } catch (Exception e) {
                e.printStackTrace();
                label.setText(e.getMessage());
            }
        }
        else{
            try {
                Quiz tmp = new Quiz(quiz.getQuizId(), choicebTopicname.getValue());
                dao.updateQuiz(tmp);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            }catch (Exception e){
                label.setText(e.getMessage());
            }
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        QuizApp.setRoot(p);
    }

    public void setData(Quiz quiz){
        this.quiz = quiz;
        if(quiz != null){
            choicebTopicname.setValue(quiz.getTopicName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<String> topicsList = FXCollections.observableArrayList();
        var topics = dao.getTopics();
        for (var i: topics){
            topicsList.add(i.getTopicName());
            choicebTopicname.setValue(i.getTopicName());
        }
        choicebTopicname.getItems().setAll(topicsList);
    }
}

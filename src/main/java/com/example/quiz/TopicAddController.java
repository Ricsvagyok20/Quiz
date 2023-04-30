package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
import com.example.quiz.modules.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TopicAddController {
    @FXML private Label label;
    @FXML private TextField txtfTopicName;
    private IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) {
        String topic = txtfTopicName.getText();
        if(topic.equals("")){
            label.setText("The Topic name can not be null");
        }else {
                try {
                    Topic tmp = new Topic(topic);
                    dao = new QuizDAO();
                    dao.addTopic(tmp);
                    FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                    Parent p = fxmlLoader.load();
                    QuizApp.setRoot(p);
                } catch (Exception e) {
                    label.setText(e.getMessage());
                }
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        QuizApp.setRoot(p);
    }
}

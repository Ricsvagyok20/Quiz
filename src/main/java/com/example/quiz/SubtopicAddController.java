package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Subtopic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SubtopicAddController implements Initializable {
    @FXML private TextField txtfTopicName;
    @FXML private TextField txtfDescription;
    @FXML private ChoiceBox<String> choicebtopic;
    @FXML private Label label;
    IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) throws SQLException {
        String name = txtfTopicName.getText();
        String desc = txtfDescription.getText();
        if(name.equals("")){
            label.setText("The topic must have a name");
        }else{
            Subtopic tmp = new Subtopic(name, desc, choicebtopic.getValue());
            try {
                dao.addSubTopic(tmp);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<String> topicsList = FXCollections.observableArrayList();
        var topics = dao.getTopics();
        for (var i: topics){
            topicsList.add(i.getTopicName());
        }
        choicebtopic.getItems().setAll(topicsList);
    }
}

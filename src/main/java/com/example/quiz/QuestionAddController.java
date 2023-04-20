package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuestionAddController implements Initializable {
    public ComboBox<String> cboxSubtopic;
    @FXML private TextField txtfQuestionContent;
    @FXML private Label label;
    @FXML private Button btnCancel;
    @FXML private Button btnSave;

    private IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) throws SQLException {
        String content = txtfQuestionContent.getText();
        String subtopic = cboxSubtopic.getValue();
        try {
            Question question = new Question(content, subtopic);
            dao = new QuizDAO();
            dao.addQuestion(question);
        }catch (Exception e){
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
        ObservableList<String> subtopics = FXCollections.observableArrayList();
        var topics = dao.getSubtopics();
        for (var i: topics){
            subtopics.add(i.getSubtopicName());
        }
        cboxSubtopic.getItems().setAll(subtopics);
    }
}

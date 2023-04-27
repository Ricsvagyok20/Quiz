package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Question;
import com.example.quiz.modules.Quiz;
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
    private Question question;

    public void btnSaveAction(ActionEvent event) throws SQLException {
        String content = txtfQuestionContent.getText();
        String subtopic = cboxSubtopic.getValue();
        if(question == null) {
            try {
                Question tmp = new Question(content, subtopic);
                dao.addQuestion(tmp);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            } catch (Exception e) {
                label.setText(e.getMessage());
            }
        }
        else{
            try{
                Question tmp = new Question(question.getId(), content, subtopic);
                dao.updateQuestion(tmp);
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            } catch (Exception e){
                label.setText(e.getMessage());
            }
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        QuizApp.setRoot(p);
    }

    public void setData(Question question){
        this.question = question;
        if(question != null){
            txtfQuestionContent.setText(question.getQuestionContent());
            cboxSubtopic.setValue(question.getSubtopicNameQuestion());
        }
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

package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class QuestionAddController{
    @FXML private TextField txtfQuestionContent;
    @FXML private TextField txtfSubtopicName;
    @FXML private Label label;
    @FXML private Button btnCancel;
    @FXML private Button btnSave;

    private IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) throws SQLException {
        String content = txtfQuestionContent.getText();
        String subtopic = txtfSubtopicName.getText();
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


}

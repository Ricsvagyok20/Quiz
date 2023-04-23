package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Answer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnswerAddController implements Initializable {
    @FXML private Label label;
    @FXML private ChoiceBox<String> choiceBQuestionId;
    @FXML private TextField txtfAnswerContent;
    @FXML private CheckBox checkBCorrect;
    private IQuizDAO dao;

    public void btnSaveAction(ActionEvent event) throws SQLException, IOException {
        Answer ans = null;
        String QuestionContent = choiceBQuestionId.getValue();
        String AnswerContent = txtfAnswerContent.getText();
        String Correct = (checkBCorrect.isSelected() ? "Y" : "N");
        if(QuestionContent != null && !QuestionContent.equals("")) {
            var question = dao.getQuestions();
            for (var i : question) {
                if (Objects.equals(i.getQuestionContent(), QuestionContent)){
                    ans = new Answer(i.getId(), AnswerContent, Correct);
                    break;
                }
            }
            if(ans != null) {
                try{
                    dao.addAnswer(ans);
                }catch (Exception e){
                    label.setText(e.getMessage());
                }


                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                HelloApplication.setRoot(p);
            }else{
                label.setText("Something went wrong, this might not be a proper answer");
            }
        }else{
            label.setText("You must choose a question that the answer belongs to");
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
        ObservableList<String> questionsContent = FXCollections.observableArrayList();
        var question = dao.getQuestions();
        for (var i: question){
            questionsContent.add(i.getQuestionContent());
        }
        choiceBQuestionId.getItems().setAll(questionsContent);
    }
}

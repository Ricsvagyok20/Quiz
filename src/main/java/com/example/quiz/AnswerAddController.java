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
import java.util.ResourceBundle;

public class AnswerAddController implements Initializable {
    @FXML private Label label;
    @FXML private ChoiceBox<String> choiceBQuestionContent;
    @FXML private TextField txtfAnswerContent;
    @FXML private CheckBox checkBCorrect;
    private IQuizDAO dao;
    private Answer answer;

    public void btnSaveAction(ActionEvent event) throws SQLException, IOException {
        Answer ans = null;
        String questionContent = choiceBQuestionContent.getValue();
        String answerContent = txtfAnswerContent.getText();
        String correct = (checkBCorrect.isSelected() ? "Y" : "N");
        if(questionContent != null && !questionContent.equals("")) {
            var question = dao.getQuestions();
            for (var i : question) {
                if (i.getId()==Integer.parseInt(questionContent)){
                    if(answer == null){
                        ans = new Answer(i.getId(), answerContent, correct);
                    }
                    else{
                        ans = new Answer(answer.getAnswerId(), i.getId(), answerContent, correct);
                    }
                    break;
                }
            }
            if(ans != null) {
                try{
                    if(answer == null){
                        dao.addAnswer(ans);
                    }
                    else{
                        dao.updateAnswer(ans);
                    }
                }catch (Exception e){
                    label.setText(e.getMessage());
                }
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                Parent p = fxmlLoader.load();
                QuizApp.setRoot(p);
            }else{
                label.setText("Something went wrong, this might not be a proper answer");
            }
        }else{
            label.setText("You must choose a question that the answer belongs to");
        }

    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent p = fxmlLoader.load();
        QuizApp.setRoot(p);
    }

    public void setData(Answer answer){
        this.answer = answer;
        if(answer != null){
            choiceBQuestionContent.setValue(Integer.toString(answer.getQuestionId()));
            txtfAnswerContent.setText(answer.getAnswerContent());
            checkBCorrect.setSelected(answer.getCorrectAnswer().equalsIgnoreCase("Y"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
        ObservableList<String> questionsContent = FXCollections.observableArrayList();
        var question = dao.getQuestions();
        for (var i: question){
            questionsContent.add(i.getQuestionContent());
        }
        choiceBQuestionContent.getItems().setAll(questionsContent);
    }
}

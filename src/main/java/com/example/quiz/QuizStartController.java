package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class QuizStartController {
    @FXML
    private Button btnProfile;

    @FXML
    private Label lblStartQuiz;

    @FXML
    private Label lblTopic;

    @FXML
    private Label lblNumberOfQuestions;

    @FXML
    private ChoiceBox chbChosenTopic;

    @FXML
    private Spinner spnNumberOfQuestions;

    @FXML
    private Button btnPlay;


    public void loadProfile(ActionEvent event) {
    }

    public void play(ActionEvent event) {
    }
}

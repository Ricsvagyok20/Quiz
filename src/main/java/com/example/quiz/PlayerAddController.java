package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
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
import java.util.ResourceBundle;

public class PlayerAddController implements Initializable {

    private IQuizDAO dao;

    @FXML private ComboBox<String> cboxTopicName;
    @FXML private TextField txtfUsername;
    @FXML private TextField txtfPassword;
    @FXML private TextField txtfEmail;
    @FXML private TextField txtfRankingPoints;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private Label label;

    public void btnSaveAction(ActionEvent event) {
        String username = txtfUsername.getText();
        String password = txtfPassword.getText();
        String email = txtfEmail.getText();
        String rpString = txtfRankingPoints.getText();
        int rp = 0;
        if(rpString != ""){
            rp = Integer.parseInt(rpString);
        }
        String topicName = cboxTopicName.getValue();
        if(username == ""){
            label.setText("The userName can not be null");
        }else {
            Player player = new Player(username, password, email, rp, topicName);
            dao = new QuizDAO();
            dao.addPlayer(player);
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
        ObservableList<String> topicsList = FXCollections.observableArrayList();
        var topics = dao.getTopics();
        for (var i: topics){
            topicsList.add(i.getTopicName());
        }
        cboxTopicName.getItems().setAll(topicsList);
    }
}

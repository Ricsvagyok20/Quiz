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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayerAddController implements Initializable {

    @FXML private Label topiclbl;
    private IQuizDAO dao;

    @FXML private ComboBox<String> cboxTopicName;
    @FXML private TextField txtfUsername;
    @FXML private TextField txtfPassword;
    @FXML private TextField txtfEmail;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private Label label;
    private Player player;
    private boolean from = false;

    public void btnSaveAction(ActionEvent event) throws SQLException, IOException {
        String username = txtfUsername.getText();
        String password = txtfPassword.getText();
        String email = txtfEmail.getText();
        String topicName = cboxTopicName.getValue();
        int rp = 0;
        if(player == null){
            if(username.equals("")){
                label.setText("The userName can not be null");
            }else {
                try {
                    Player tmp;
                    if(topicName != null && !topicName.equals("")){
                        tmp = new Player(username, password, email, topicName);
                    }
                    else{
                        tmp = new Player(username, password, email, null);
                    }
                    dao.addPlayer(tmp);
                    FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                    Parent p = fxmlLoader.load();
                    QuizApp.setRoot(p);
                }catch (Exception e){
                    label.setText(e.getMessage());
                }
            }
        }
        else{
            if(!this.from) {
                try {
                    Player tmp = new Player(player.getUserName(), password, email, topicName);
                    dao.updatePlayerAdmin(tmp);
                    FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
                    Parent p = fxmlLoader.load();
                    QuizApp.setRoot(p);
                } catch (Exception e) {
                    label.setText(e.getMessage());
                }
            }else{
                try{
                    Player tmp = new Player(player.getUserName(), password, email, topicName);
                    dao.updatePlayer(tmp);
                    player = tmp;
                    FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("profile.fxml"));
                    Parent p = fxmlLoader.load();

                    ProfileController controllerProfile = fxmlLoader.getController();
                    controllerProfile.setData(player);

                    QuizApp.setRoot(p);
                } catch (Exception e) {
                    label.setText(e.getMessage());
                }
            }
        }
    }

    public void btnCancelAction(ActionEvent event) throws IOException {
        if(!from){
            FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
            Parent p = fxmlLoader.load();
            QuizApp.setRoot(p);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("profile.fxml"));
            Parent fxml = fxmlLoader.load();

            ProfileController controller = fxmlLoader.getController();
            controller.setData(player);

            QuizApp.setRoot(fxml);
        }
    }

    public void setData(Player player){
        this.player = player;
        if(player != null){
            txtfUsername.setText(player.getUserName());
            txtfEmail.setText(player.getEmail());
            txtfPassword.setText(player.getPassword());
            if(!from){
                if(player.getTopicNamePlayer() == "" || player.getTopicNamePlayer() == null){
                    cboxTopicName.setValue(dao.getTopics().get(0).getTopicName());
                }else{
                    cboxTopicName.setValue(player.getTopicNamePlayer());
                }
            }else{
                cboxTopicName.setVisible(false);
                topiclbl.setVisible(false);
                txtfUsername.setEditable(false);
            }
        }
    }

    public void setFrom(boolean bo){
        this.from = bo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = null;
        dao = new QuizDAO();
        ObservableList<String> topicsList = FXCollections.observableArrayList();
        var topics = dao.getTopics();
        for (var i: topics){
            topicsList.add(i.getTopicName());
        }
        cboxTopicName.getItems().setAll(topicsList);
    }
}

package com.example.quiz;

import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginRegistController implements Initializable{
    @FXML private Label lblError;
    @FXML
    private TextField txtfUsername;

    @FXML
    private TextField txtfPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtfUsernameRegist;

    @FXML
    private TextField txtfEmailRegist;

    @FXML
    private TextField txtfPasswordRegist;

    @FXML
    private TextField txtfPasswordRegistAgain;

    @FXML
    private CheckBox chbUA;

    @FXML
    private Button btnRegister;

    private IQuizDAO dao;


    public void Login(ActionEvent event) throws IOException {
        String username = txtfUsername.getText();
        String password = txtfPassword.getText();
        var players = dao.getPlayers();
        for (Player p: players){
            if(username.equals(p.getUserName()) && password.equals(p.getPassword())){
                FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("menu.fxml"));
                Parent parent = fxmlLoader.load();

                MenuController controller = fxmlLoader.getController();
                controller.setPlayer(p);

                QuizApp.setRoot(parent);
                return;
            }
        }
        lblError.setText("Username or password is incorrect!");
    }

    public void register(ActionEvent event) throws IOException{
        String username = txtfUsernameRegist.getText();
        String email = txtfEmailRegist.getText();
        String password = txtfPasswordRegist.getText();
        String passwordAgain = txtfPasswordRegistAgain.getText();
        if(!password.equals(passwordAgain)){
            lblError.setText("The passwords don't match!");
            return;
        }
        Player player = new Player(username, password, email, null);
        try{
            dao.addPlayer(player);
        }
        catch (Exception e){
            e.printStackTrace();
            lblError.setText(e.toString());
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("menu.fxml"));
        Parent parent = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();
        controller.setPlayer(player);

        QuizApp.setRoot(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();
    }
}

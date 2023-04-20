package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginRegistController {
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


    public void Login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }

    public void register(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Parent p = fxmlLoader.load();
        HelloApplication.setRoot(p);
    }
}

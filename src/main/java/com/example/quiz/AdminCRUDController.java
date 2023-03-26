package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.quiz.modules.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCRUDController implements Initializable {

     @FXML
     private TableView<Player> tableViewPlayer;
     @FXML
     private TableColumn<Player, String> userNameColumn;
     @FXML
     private TableColumn<Player, String> passwordColumn;
     @FXML
     private TableColumn<Player, String> emailColumn;
     @FXML
     private TableColumn<Player, Integer> rankingPointColumn;
     @FXML
     private Button btnAdd;
     @FXML
     private Button btnDelete;
     @FXML
     private Button btnModify;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        rankingPointColumn.setCellValueFactory(new PropertyValueFactory<>());
    }

    public void btnAddAction(ActionEvent event) {

    }

    public void btnDeleteAction(ActionEvent event) {
    }

    public void btnModifyAction(ActionEvent event) {
    }
}
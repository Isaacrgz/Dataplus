package com.dataplus.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.dataplus.models.AccountModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;

public class ListViewAccController implements Initializable {
    @FXML private Label accountLB = new Label();
    @FXML private Label emailLB = new Label();
    @FXML private Label passLB = new Label();
    @FXML private Button copyEmailBT = new Button();
    @FXML private Button copyPassBT = new Button();
    @FXML private VBox backgroundVB = new VBox();

    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ListViewAccController(){
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        backgroundVB.setOnMouseClicked((event) -> {
        });

        copyEmailBT.setOnAction((event) -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(emailLB.getText());
            clipboard.setContent(content);
        });

        copyPassBT.setOnAction((event) -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(passLB.getText());
            clipboard.setContent(content);
        });
    }

    public void setData(AccountModel accountModel){
        accountLB.setText(accountModel.getAccount());
        emailLB.setText(accountModel.getEmail());
        passLB.setText(accountModel.getPassword());
    }

    public void setNewText(int id){
        accountLB.setText("entre");
        System.out.println(id);
        backgroundVB.setStyle("-fx-background-color: black");
    }

}

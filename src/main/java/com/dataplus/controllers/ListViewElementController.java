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

public class ListViewElementController implements Initializable {
    @FXML private Label accountLB = new Label();
    @FXML private Label emailLB = new Label();
    @FXML private Label passLB = new Label();
    @FXML private Button copyEmailBT = new Button();
    @FXML private Button copyPassBT = new Button();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        copyEmailBT.setOnAction((event) ->{
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(emailLB.getText());
            clipboard.setContent(content);
        });

        copyPassBT.setOnAction((event) ->{
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(copyPassBT.getText());
            clipboard.setContent(content);
        });

    }

    public void setData(AccountModel accountModel){
        accountLB.setText(accountModel.getAccount());
        emailLB.setText(accountModel.getEmail());
        passLB.setText(accountModel.getPassword());
    }



    
    
}

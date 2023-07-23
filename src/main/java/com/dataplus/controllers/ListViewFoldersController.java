package com.dataplus.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.dataplus.models.AccountModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ListViewFoldersController implements Initializable {

    
    @FXML private Label folderLB = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setData(AccountModel accountModel){
        folderLB.setText(accountModel.getAccount());
    }
    
}

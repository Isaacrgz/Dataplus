package com.dataplus.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.dataplus.AccountDAO;
import com.dataplus.App;
import com.dataplus.interfaces.ImplementacionMySQL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainMenuController implements Initializable {

    @FXML private Button folderBT = new Button();
    @FXML private Button accountBT = new Button();
    @FXML private Button folderSaveBT = new Button();
    @FXML private Button accSaveBT = new Button();
    @FXML private VBox folderVB = new VBox();
    @FXML private VBox accountsVB = new VBox();
    @FXML private TextField folderNameTF = new TextField();
    @FXML private TextField accTF = new TextField();
    @FXML private TextField emailTF = new TextField();
    @FXML private TextField passTF = new TextField();
    @FXML private Label noteFolderLB = new Label();
    @FXML private Label noteAccLB = new Label();
    
    AccountDAO accountDAO = new AccountDAO();
    ImplementacionMySQL imsql = new ImplementacionMySQL();

    public MainMenuController() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        selectData();

        accSaveBT.setOnAction((event) -> {
            if(accTF.getText()!="" && emailTF.getText()!="" && passTF.getText()!=""){

                noteAccLB.setText("RIGH");
                imsql.insertar(accTF.getText(), emailTF.getText(), passTF.getText());
                
            }else{
                noteAccLB.setText("empty");
            }
        });

        folderSaveBT.setOnAction((event) -> {
            if(folderNameTF.getText()!=""){
                noteFolderLB.setText(folderNameTF.getText());
            }else{
                noteFolderLB.setText("empty");
            }
        }); 
    }

    public void selectData(){
        for (int i=0; i<imsql.listar().size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("listViewElement.fxml"));

            try {
                VBox vBox = fxmlLoader.load();
                ListViewElementController lvec = fxmlLoader.getController();
                lvec.setData(imsql.listar().get(i)); 
                accountsVB.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
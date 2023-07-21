package com.dataplus.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.dataplus.AccountDAO;
import com.dataplus.App;
import com.dataplus.interfaces.ImplementacionMySQL;
import com.dataplus.models.AccountModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuController implements Initializable {

    @FXML private Button folderBT = new Button();
    @FXML private Button accEditBT = new Button();
    @FXML private Button folderSaveBT = new Button();
    @FXML private Button accSaveBT = new Button();
    @FXML private Button accDeleteBT = new Button();
    @FXML private VBox folderVB = new VBox();
    @FXML private VBox accNewVB = new VBox();
    @FXML private VBox accEditVB = new VBox();
    @FXML private VBox editorSP = new VBox();
    @FXML private AnchorPane centerAP = new AnchorPane();
    @FXML private BorderPane mainMenuBP = new BorderPane();
    @FXML private ListView<Node> accountsLV = new ListView<Node>();
    @FXML private ScrollPane accountsSP = new ScrollPane();
    @FXML private TextField folderNameTF = new TextField();
    @FXML private TextField accTF = new TextField();
    @FXML private TextField emailTF = new TextField();
    @FXML private TextField passTF = new TextField();
    @FXML private TextField accEditTF = new TextField();
    @FXML private TextField emailEditTF = new TextField();
    @FXML private TextField passEditTF = new TextField();
    @FXML private Label noteFolderLB = new Label();
    @FXML private Label noteAccLB = new Label();

    private boolean minState = false; //false = show new account menu, true = show edit menu
    private boolean firtClicked = false;
    private boolean newEditState = false; //false = save new account, true = save edited account
    
    AccountDAO accountDAO = new AccountDAO();
    ImplementacionMySQL imsql = new ImplementacionMySQL();
    AccountModel aModel = new AccountModel();
    ListViewElementController lvec = new ListViewElementController();
    VBox vBox;

    public MainMenuController() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        selectData();
        hideVBox(minState);
        accDeleteBT.setManaged(false);

        folderBT.setOnAction((event) -> {
        });

        accEditBT.setOnAction((event) -> {
            if((!firtClicked && newEditState) || (firtClicked && !newEditState || (firtClicked && newEditState))){            
                accSaveBT.setDisable(false);
                accDeleteBT.setDisable(false);
                System.out.println("deshabilitar: " + accSaveBT.isDisable() + " NES: " + newEditState + " FC " + firtClicked);
            }else if(!firtClicked && !newEditState){
                noteAccLB.setText("Set a account");
                accDeleteBT.setDisable(true);
                accSaveBT.setDisable(true);
                System.out.println("deshabilitar: " + accSaveBT.isDisable() + " NES: " + newEditState + " FC " + firtClicked);
            }

            minState = !minState;
            hideVBox(minState);
            newEditState = !newEditState;
            
        });

        accDeleteBT.setOnAction((event) -> {
            System.out.println("delete");
            imsql.eliminar(aModel.getId());
            selectData();
        });

        accountsLV.setOnMouseClicked((event) -> {
            firtClicked = true;
            noteAccLB.setText("Selected");
            accSaveBT.setDisable(false);
            accDeleteBT.setDisable(false);
            int accId = accountsLV.getSelectionModel().getSelectedIndex();
            if(accId >= 0){
                
                aModel = imsql.listar().get(accId);
                accountsLV.getItems().get(accId);
                lvec.setNewText(aModel.getId());
                System.out.println(aModel.getId() + " " + aModel.getAccount() + " " + aModel.getEmail() + " " + aModel.getPassword());
                
            }
        });

        accSaveBT.setOnAction((event) -> {
            if(newEditState){                                   // edit account
                System.out.println(newEditState + " edit");
                getData();
                imsql.actualizar(aModel.getId(), aModel.getAccount(), aModel.getEmail(), aModel.getPassword());
                selectData();
            }else{                                              // new account
                System.out.println(newEditState + " new");
                if(!accTF.getText().isEmpty() && !emailTF.getText().isEmpty() && !passTF.getText().isEmpty()){
                    noteAccLB.setText("RIGH");
                    imsql.insertar(accTF.getText(), emailTF.getText(), passTF.getText());
                    selectData();

                }else{
                    noteAccLB.setText("empty");
                }
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
        accountsLV.getItems().clear();
        for (int i=0; i<imsql.listar().size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("listViewElement.fxml"));

            try {
                vBox = fxmlLoader.load();
                //ListViewElementController lvec = fxmlLoader.getController();
                lvec = fxmlLoader.getController();
                lvec.setData(imsql.listar().get(i)); 
                accountsLV.getItems().add(vBox);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void hideVBox(boolean state){
        accEditVB.setVisible(state);
        accEditVB.setManaged(state);
        accNewVB.setVisible(!state);
        accNewVB.setManaged(!state);
        accDeleteBT.setVisible(state);
        accDeleteBT.setManaged(state);
    }

    public void getData(){
        if(!accEditTF.getText().isEmpty()){
            aModel.setAccount(accEditTF.getText());
        }

        if(!emailEditTF.getText().isEmpty()){
            aModel.setEmail(emailEditTF.getText());
        }

        if(!passEditTF.getText().isEmpty()){
            aModel.setPassword(passEditTF.getText());
        }

        if(accEditTF.getText().isEmpty() && emailEditTF.getText().isEmpty() && passEditTF.getText().isEmpty()){
            noteAccLB.setText("Enter a data at least");
        }
        
    }

    // @Override
    // protected void updateItem(Note note, boolean isEmpty){
    //     if(isEmpty|| note == null){
    //         setGraphic(null);
    //     }
    //     else {
    //         // configure based on note:
    //         controller.setText(...);
    //         controller.setXXX(...);
    //         setGraphic(graphic);
    //     }

    // }

}
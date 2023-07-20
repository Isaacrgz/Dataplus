package com.dataplus.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.dataplus.models.AccountModel;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ListViewElementController implements Initializable {
    @FXML private Label accountLB = new Label();
    @FXML private Label emailLB = new Label();
    @FXML private Label passLB = new Label();
    @FXML private Button copyEmailBT = new Button();
    @FXML private Button copyPassBT = new Button();
    @FXML private VBox backgroundVB = new VBox();

    private final Background focusBackground = new Background( new BackgroundFill( Color.web("#bee9e9"), 
        new CornerRadii(15,15,15,15,false), Insets.EMPTY ) );
    private final Background unfocusBackground = new Background( new BackgroundFill( Color.web("#eeeeee"), 
        CornerRadii.EMPTY, Insets.EMPTY ) );
    private final Border hoverBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
        new CornerRadii(15,15,15,15,false), BorderWidths.DEFAULT));
    private final Border unhoverBorder = new Border(new BorderStroke( Color.web("#eeeeee"), BorderStrokeStyle.SOLID, 
        CornerRadii.EMPTY, BorderWidths.DEFAULT));

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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

        backgroundVB.setOnMouseClicked((event) -> {
            backgroundVB.requestFocus();
        });

        backgroundVB.setOnMouseEntered((event) -> {
            backgroundVB.isHover();
        });

        backgroundVB.backgroundProperty().bind( Bindings
                    .when( backgroundVB.focusedProperty() )
                    .then( focusBackground )
                    .otherwise( unfocusBackground ));

        backgroundVB.borderProperty().bind(Bindings
                    .when( backgroundVB.hoverProperty() )
                    .then( hoverBorder )
                    .otherwise( unhoverBorder ));
        
    }

    public void setData(AccountModel accountModel){
        accountLB.setText(accountModel.getAccount());
        emailLB.setText(accountModel.getEmail());
        passLB.setText(accountModel.getPassword());
    }
    
}

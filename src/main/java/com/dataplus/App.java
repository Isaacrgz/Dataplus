package com.dataplus;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    
    private Stage stage;
    private double decorationWidth;
    private double decorationHeight;

    AccountDAO accountDAO = new AccountDAO();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        final double initialSceneWidth = 700;
        final double initialSceneHeight = 480;
        final Parent root = createRoot();
        final Scene scene = new Scene(root, initialSceneWidth, initialSceneHeight);

        this.stage.setResizable(true);
        this.stage.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    setCurrentHeightToStage(number2);
                }
            });
        
        this.stage.setMinWidth(700);
        this.stage.setMinHeight(480);
        
        this.stage.setScene(scene);
        this.stage.show();

        this.decorationWidth = initialSceneWidth - scene.getWidth();
        this.decorationHeight = initialSceneHeight - scene.getHeight();
    }

    public static void main(String[] args) {
        launch();
    }

    public void resizeScene(double width, double height) {
        this.stage.setWidth(width + this.decorationWidth);
        this.stage.setHeight(height + this.decorationHeight);
        if((height % 5) == 0){
            System.out.println("stage resize: " + height);
        }
        
    }

    public Parent createRoot() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("mainMenu.fxml"));
        AnchorPane root = loader.<AnchorPane>load();
        return root;
    }

    private void setCurrentHeightToStage(Number number2) {
        stage.setHeight((double) number2);
        if(((double) number2 % 5) == 0){
            System.out.println("stage setCrr: " + number2);
        }
    }
}
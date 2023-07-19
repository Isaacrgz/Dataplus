package com.dataplus;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("mainMenu.fxml"));
        BorderPane root = loader.<BorderPane>load();
        scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
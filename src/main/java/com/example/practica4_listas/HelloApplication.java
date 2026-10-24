package com.example.practica4_listas;

import com.example.practica4_listas.ui.GameScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        GameScreen screen=new GameScreen();
        Scene scene=new Scene(screen,1200,800);

        stage.setTitle("Eight Off");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.practica4_listas.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {

    private Button newButton;
    private Button undoButton;
    private Button hintButton;
    private Label moves;

    public ControlPanel() {
        setSpacing(18);
        setPadding(new Insets(20,15,20,15));
        setAlignment(Pos.TOP_CENTER);
        setPrefWidth(150);
        setStyle("-fx-background-color: black;");

        Label title = new Label("Eight Off");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        newButton = new Button("⟳  New");
        undoButton = new Button("↶  Undo");
        hintButton = new Button("  Hint");

        moves = new Label("Moves: 0");
        moves.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        styleButton(newButton);
        styleButton(undoButton);
        styleButton(hintButton);

        Region space = new Region();
        space.setMinHeight(20);

        getChildren().addAll(title,space,newButton,undoButton,hintButton,moves);
    }

    private void styleButton(Button b) {
        b.setPrefWidth(110);
        b.setPrefHeight(40);

        b.setStyle("-fx-background-color: #2a2a2a;" + "-fx-text-fill: white;" + "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" + "-fx-background-radius: 10;" + "-fx-border-color: #555;" + "-fx-border-radius: 10;"
        );

        b.setOnMouseEntered(e ->
                b.setStyle("-fx-background-color: #3c3c3c;" + "-fx-text-fill: white;" + "-fx-font-size: 15px;" + "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" + "-fx-border-color: white;" + "-fx-border-radius: 10;"
                )
        );

        b.setOnMouseExited(e ->
                b.setStyle("-fx-background-color: #2a2a2a;" + "-fx-text-fill: white;" + "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" + "-fx-background-radius: 10;" + "-fx-border-color: #555;" +
                        "-fx-border-radius: 10;"
                )
        );
    }

    public void updateMoves(int total) {
        moves.setText("Moves: " + total);
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getHintButton() {
        return hintButton;
    }
}
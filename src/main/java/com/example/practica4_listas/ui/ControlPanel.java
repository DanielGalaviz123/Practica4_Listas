
package com.example.practica4_listas.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {

    private Button newButton;
    private Button undoButton;
    private Button hintButton;
    private Label movesLabel;

    public ControlPanel() {

        setPrefWidth(165);
        setMinWidth(165);
        setSpacing(15);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(25,20,0,20));
        setStyle("-fx-background-color: black;");

        Label title=new Label("Eight Off");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

        newButton=createButton(" New");
        undoButton=createButton(" Undo");
        hintButton=createButton("Hint");

        movesLabel=new Label("Moves: 0");
        movesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        VBox.setMargin(newButton,new Insets(65,0,0,0));

        getChildren().addAll(title,newButton,undoButton,hintButton,movesLabel);
    }


    private Button createButton(String text) {
        Button button=new Button(text);

        button.setPrefSize(122,45);
        button.setMinSize(122,45);

        button.setStyle("-fx-background-color: #292929;" + "-fx-text-fill: white;" + "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" + "-fx-background-radius: 11;" + "-fx-border-color: #484848;" + "-fx-border-radius: 11;"
        );

        return button;
    }


    public void setActions(Runnable newGame,Runnable undo,Runnable hint) {

        newButton.setOnAction(e -> newGame.run());
        undoButton.setOnAction(e -> undo.run());
        hintButton.setOnAction(e -> hint.run());
    }


    public void updateMoves(int moves) {
        movesLabel.setText("Moves: " + moves);
    }
}

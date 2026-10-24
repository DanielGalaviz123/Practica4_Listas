
package com.example.practica4_listas.ui;

import javafx.scene.layout.BorderPane;

public class GameScreen extends BorderPane {

    private GameBoard board;
    private ControlPanel controls;

    public GameScreen() {
        board = new GameBoard();
        controls = new ControlPanel();

        setLeft(controls);
        setCenter(board);
    }

    public void updateScreen() {
        board.updateBoard();
    }

    public void selectCard() {

    }
}

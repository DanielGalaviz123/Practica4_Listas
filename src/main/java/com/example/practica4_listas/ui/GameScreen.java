
package com.example.practica4_listas.ui;

import com.example.practica4_listas.logic.Card;
import com.example.practica4_listas.logic.GameFlow;
import com.example.practica4_listas.logic.Move;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

public class GameScreen extends BorderPane {

    private GameFlow game;
    private GameBoard board;
    private ControlPanel controls;

    private int selectedPosition=-1;
    private int selectedCount=1;
    private int selectedIndex=-1;

    public GameScreen() {
        game=new GameFlow();
        game.startGame();

        board=new GameBoard(game,this::selectCard);
        controls=new ControlPanel();

        controls.setActions(this::newGame,this::undoMove,this::showHint);

        setLeft(controls);
        setCenter(board);

        updateScreen();
    }


    public void updateScreen() {
        board.updateBoard();
        controls.updateMoves(game.getMoves());
    }


    private boolean hasCard(int position) {

        if(position >= 0 && position < 8) {
            Card[] cards=game.getColumn(position);
            return cards.length > 0;
        }

        if(position >= 8 && position < 16) {
            return game.getReserve(position-8) != null;
        }

        return false;
    }


    private void selectSource(int position,int index) {

        selectedPosition=position;
        selectedCount=1;
        selectedIndex=index;

        if(position < 8) {
            Card[] cards=game.getColumn(position);

            if(index >= 0 && index < cards.length) {
                selectedCount=cards.length-index;
            }
            else {
                selectedIndex=cards.length-1;
            }
        }

        board.showSelection(position);
    }


    private void clearSelection() {
        selectedPosition=-1;
        selectedCount=1;
        selectedIndex=-1;

        board.showSelection(-1);
    }


    public void selectCard(int position,int cardIndex) {

        if(selectedPosition == -1) {

            if(hasCard(position)) {
                selectSource(position,cardIndex);
            }
            else {
                board.showSelection(position);
            }
            return;
        }

        if(selectedPosition == position) {

            if(position < 8 && cardIndex >= 0 &&
                    cardIndex != selectedIndex) {

                selectSource(position,cardIndex);
            }
            else {
                clearSelection();
            }
            return;
        }


        boolean moved=game.moveCard(selectedPosition,position,selectedCount);

        if(moved) {
            clearSelection();
            updateScreen();

            if(game.isWon()) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Eight Off");
                alert.setHeaderText("Ganaste la partida");
                alert.setContentText("Completaste las cuatro fundaciones.");
                alert.showAndWait();
            }
        }
        else {
            if(hasCard(position)) {
                selectSource(position,cardIndex);
            }
            else {
                board.showSelection(selectedPosition);
            }
        }
    }


    public void newGame() {
        game.startGame();
        clearSelection();
        updateScreen();
    }


    public void undoMove() {

        if(game.undo()) {
            clearSelection();
            updateScreen();
        }
    }


    public void showHint() {

        // buscar una sugerencia
        Move move=game.hint();

        if(move == null) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eight Off");
            alert.setHeaderText("No hay sugerencias");
            alert.setContentText("No se encontro un movimiento disponible.");
            alert.showAndWait();
            return;
        }

        selectedPosition=move.getFrom();
        selectedCount=move.getCount();
        selectedIndex=-1;

        board.showHint(move.getFrom(),move.getTo());
    }
}

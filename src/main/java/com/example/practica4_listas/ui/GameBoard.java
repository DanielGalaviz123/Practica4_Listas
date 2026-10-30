
package com.example.practica4_listas.ui;

import com.example.practica4_listas.logic.Card;
import com.example.practica4_listas.logic.GameFlow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.function.BiConsumer;

public class GameBoard extends BorderPane {

    private GameFlow game;
    private ColumnView[] columns;
    private StackPane[] reserveSpaces;
    private StackPane[] foundationSpaces;

    private HBox reserves;
    private VBox foundations;
    private BiConsumer<Integer,Integer> onSelect;

    public GameBoard(GameFlow game, BiConsumer<Integer,Integer> onSelect) {
        this.game=game;
        this.onSelect=onSelect;

        columns=new ColumnView[8];
        reserveSpaces=new StackPane[8];
        foundationSpaces=new StackPane[4];

        setPadding(new Insets(15));
        setStyle("-fx-background-color: #453A54;");

        reserves=new HBox(10);
        reserves.setAlignment(Pos.CENTER_LEFT);
        reserves.setPadding(new Insets(0,0,15,0));

        HBox columnBox=new HBox(8);
        columnBox.setAlignment(Pos.TOP_LEFT);
        columnBox.setStyle("-fx-background-color: #453A54;");

        foundations=new VBox(10);
        foundations.setPadding(new Insets(0,0,0,15));


        for(int i=0; i<8; i++) {
            columns[i]=new ColumnView();

            int position=i;

            columns[i].setOnCardSelect(index -> {
                onSelect.accept(position,index);
            });

            columns[i].setOnMouseClicked(e -> {
                onSelect.accept(position,-1);
            });

            columnBox.getChildren().add(columns[i]);
        }

        for(int i=0; i<8; i++) {
            reserveSpaces[i]=createSpace();

            int position=i+8;
            reserveSpaces[i].setOnMouseClicked(e -> {
                onSelect.accept(position,-1);
            });

            reserves.getChildren().add(reserveSpaces[i]);
        }

        for(int i=0; i<4; i++) {
            foundationSpaces[i]=createSpace();

            int position=i+16;
            foundationSpaces[i].setOnMouseClicked(e -> {
                onSelect.accept(position,-1);
            });

            foundations.getChildren().add(foundationSpaces[i]);
        }

        ScrollPane scroll=new ScrollPane(columnBox);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setStyle("-fx-background: #453A54; -fx-background-color: #453A54; -fx-border-color: transparent;");

        setTop(reserves);
        setCenter(scroll);
        setRight(foundations);

        updateBoard();
    }


    private StackPane createSpace() {
        StackPane space=new StackPane();

        space.setPrefSize(95,130);
        space.setMinSize(95,130);
        space.setMaxSize(95,130);

        space.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
        return space;
    }


    public void updateBoard() {

        for(int i=0; i<8; i++) {
            columns[i].updateColumn(game.getColumn(i));
        }

        for(int i=0; i<8; i++) {
            reserveSpaces[i].getChildren().clear();

            Card card=game.getReserve(i);

            if(card != null) {
                reserveSpaces[i].getChildren().add(new CardView(card));
            }
        }

        for(int i=0; i<4; i++) {
            foundationSpaces[i].getChildren().clear();

            Card card=game.getFoundation(i);

            if(card != null) {
                foundationSpaces[i].getChildren().add(new CardView(card));
            }
        }
    }


    public void showSelection(int position) {

        for(int i=0; i<8; i++) {
            columns[i].setStyle("");
        }

        for(int i=0; i<8; i++) {
            reserveSpaces[i].setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
            reserveSpaces[i].setEffect(null);
        }

        for(int i=0; i<4; i++) {
            foundationSpaces[i].setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
            foundationSpaces[i].setEffect(null);
        }

        if(position >= 0 && position < 20) {
            markPosition(position,"#F4D06F");
        }
    }


    private void markPosition(int position,String color) {

        if(position >= 0 && position < 8) {
            columns[position].setStyle("-fx-border-color: " + color + "; -fx-border-width: 2;");
        }
        else if(position >= 8 && position < 16) {
            StackPane space=reserveSpaces[position-8];

            space.setStyle("-fx-border-color: " + color + "; -fx-border-width: 3; -fx-border-radius: 5;");
            space.setEffect(new DropShadow(10,Color.web(color)));
        }
        else if(position >= 16 && position < 20) {
            StackPane space=foundationSpaces[position-16];

            space.setStyle("-fx-border-color: " + color + "; -fx-border-width: 3; -fx-border-radius: 5;");
            space.setEffect(new DropShadow(10,Color.web(color)));
        }
    }


    public void showHint(int from,int to) {
        showSelection(-1);

        markPosition(from,"#F4D06F");
        markPosition(to,"#80D5FF");
    }
}

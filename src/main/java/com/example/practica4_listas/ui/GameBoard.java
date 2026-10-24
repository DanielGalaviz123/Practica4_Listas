
package com.example.practica4_listas.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class GameBoard extends BorderPane {

    private ColumnView[] columns;
    private HBox reserves;
    private VBox foundations;

    public GameBoard() {
        columns = new ColumnView[8];

        setPadding(new Insets(15));
        setStyle("-fx-background-color: #453A54;");

        reserves=new HBox(10);
        reserves.setAlignment(Pos.CENTER_LEFT);
        reserves.setPadding(new Insets(0,0,15,0));

        HBox columnBox = new HBox(8);
        columnBox.setAlignment(Pos.TOP_LEFT);

        foundations=new VBox(10);
        foundations.setPadding(new Insets(0,0,0,15));

        for(int i=0;i<8;i++) {
            columns[i]=new ColumnView();
            columnBox.getChildren().add(columns[i]);
        }

        for (int i=0; i<8; i++) {
            StackPane space=createSpace();
            reserves.getChildren().add(space);
        }

        for(int i=0;i<4;i++){
            foundations.getChildren().add(createSpace());
        }

        setTop(reserves);
        setCenter(columnBox);
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

        for(int i=0;i<8;i++) {
            columns[i].clearColumn();
        }
    }
}

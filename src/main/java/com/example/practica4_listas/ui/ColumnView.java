
package com.example.practica4_listas.ui;

import com.example.practica4_listas.logic.Card;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.List;

public class ColumnView extends Pane {

    public ColumnView() {
        setPrefWidth(105);
        setMinWidth(105);
        setPrefHeight(460);

        clearColumn();
    }

    public void clearColumn() {
        getChildren().clear();

        StackPane space=new StackPane();
        space.setPrefSize(95,130);
        space.setLayoutX(5);
        space.setLayoutY(0);

        space.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
        getChildren().add(space);
    }

    public void updateColumn(List<Card> cards) {

        if(cards == null || cards.isEmpty()) {
            clearColumn();
            return;
        }

        getChildren().clear();

        for(int i=0;i<cards.size();i++) {
            CardView view = new CardView(cards.get(i));

            view.setLayoutX(5);
            view.setLayoutY(i*32);
            getChildren().add(view);
        }
    }
}

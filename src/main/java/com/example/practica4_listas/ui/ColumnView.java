
package com.example.practica4_listas.ui;

import com.example.practica4_listas.logic.Card;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.function.IntConsumer;

public class ColumnView extends Pane {

    private IntConsumer onCardSelect;

    public ColumnView() {
        setPrefWidth(105);
        setMinWidth(105);
        setPrefHeight(460);

        clearColumn();
    }

    public void setOnCardSelect(IntConsumer onCardSelect) {
        this.onCardSelect=onCardSelect;
    }

    public void clearColumn() {
        getChildren().clear();

        StackPane space=new StackPane();
        space.setPrefSize(95,130);
        space.setLayoutX(5);
        space.setLayoutY(0);

        space.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
        getChildren().add(space);

        setPrefHeight(460);
    }


    public void updateColumn(Card[] cards) {

        if(cards == null || cards.length == 0) {
            clearColumn();
            return;
        }

        getChildren().clear();

        for(int i=0; i<cards.length; i++) {
            CardView view=new CardView(cards[i]);

            view.setLayoutX(5);
            view.setLayoutY(i*75);

            int index=i;

            view.setOnMouseClicked(e -> {
                if(onCardSelect != null) {
                    onCardSelect.accept(index);
                }
                e.consume();
            });

            getChildren().add(view);
        }

        int height=(cards.length-1)*75+130;
        setPrefHeight(Math.max(460,height));
    }
}

package com.example.practica4_listas.ui;

import com.example.practica4_listas.logic.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {

    private Card card;
    private boolean selected;
    private Label label;

    public CardView(Card card) {
        this.card = card;
        selected=false;

        label=new Label();
        label.setAlignment(Pos.TOP_LEFT);

        setPrefSize(95,130);
        setMinSize(95,130);
        setMaxSize(95,130);
        setPadding(new Insets(5));
        setAlignment(Pos.TOP_LEFT);

        getChildren().add(label);
        updateCard();
    }

    public void updateCard() {
        String symbol="";

        switch(card.getSuit()) {
            case "Hearts": symbol="♥"; break;
            case "Diamonds": symbol="♦"; break;
            case "Clubs": symbol="♣"; break;
            case "Spades": symbol="♠"; break;
        }

        String value = String.valueOf(card.getValue());

        if(card.getValue()==1) value="A";
        if(card.getValue()==11) value="J";
        if(card.getValue()==12) value="Q";
        if(card.getValue()==13) value="K";

        label.setText(value+"\n"+symbol);

        if(symbol.equals("♥") || symbol.equals("♦")) {
            label.setStyle("-fx-text-fill: red; -fx-font-size: 23px; -fx-font-weight: bold;");
        }
        else {
            label.setStyle("-fx-text-fill: black; -fx-font-size: 23px; -fx-font-weight: bold;");
        }
        setSelected(selected);
    }

    public void setSelected(boolean selected) {
        this.selected=selected;

        if(selected) {
            setStyle("-fx-background-color: #fff3a5; -fx-border-color: orange; -fx-border-width: 3; -fx-background-radius: 5;");
        }
        else
            setStyle("-fx-background-color: white; -fx-border-color: #444; -fx-border-width: 2; -fx-background-radius: 5;");
    }
}
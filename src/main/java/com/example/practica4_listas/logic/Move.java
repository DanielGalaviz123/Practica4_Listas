package com.example.practica4_listas.logic;

public class Move {

    private Card card;
    private int from;
    private int to;
    private int count;

    public Move(Card card, int from, int to) {
        this(card,from,to,1);
    }

    public Move(Card card, int from, int to, int count) {
        this.card=card;
        this.from=from;
        this.to=to;
        this.count=count;
    }

    public Card getCard() {
        return card;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }
}
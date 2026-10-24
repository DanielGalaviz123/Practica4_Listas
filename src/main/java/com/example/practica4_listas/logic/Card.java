package com.example.practica4_listas.logic;

public class Card implements Comparable<Card> {

    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value=value;
        this.suit=suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        int result = suit.compareTo(other.getSuit());

        if(result == 0) {
            return Integer.compare(value, other.getValue());
        }
        return result;
    }

    @Override
    public String toString() {
        String name;

        switch(value) {
            case 1: name="A"; break;
            case 11: name="J"; break;
            case 12: name="Q"; break;
            case 13: name="K"; break;
            default: name=String.valueOf(value);
        }

        return name + " of " + suit;
    }
}
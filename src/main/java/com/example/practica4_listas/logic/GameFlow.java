
package com.example.practica4_listas.logic;

import java.util.Random;

public class GameFlow {

    private CircularList<Card> deck;
    private LinkedList<Card>[] columns;
    private Card[] reserves;
    private LinkedList<Card>[] foundations;
    private LinkedList<Move> history;
    private int moves;

    public GameFlow() {
        deck = new CircularList<>();
        columns = new LinkedList[8];
        reserves = new Card[8];
        foundations = new LinkedList[4];
        history=new LinkedList<>();
        moves=0;

        for (int i = 0; i < 8; i++) {
            columns[i] = new LinkedList<>();
        }

        for(int j = 0; j < 4; j++) {
            foundations[j]=new LinkedList<>();
        }
    }

    public void startGame() {

        deck=new CircularList<>();
        history = new LinkedList<>();
        moves=0;

        for (int i=0; i<8; i++) {
            columns[i]=new LinkedList<>();
            reserves[i] = null;
        }

        for(int i = 0; i<4; i++) {
            foundations[i]=new LinkedList<>();
        }

        createDeck();
        shuffle();
        deal();
    }


    // crear las 52 cartas
    private void createDeck() {
        String[] suits={"Hearts","Diamonds","Clubs","Spades"};

        for (int i = 0; i < 4; i++) {
            for(int j=1; j<=13; j++) {
                Card card = new Card(j,suits[i]);
                deck.addLast(card);
            }
        }
    }


    public void shuffle() {
        Card[] cards=new Card[52];
        int total = 0;

        while (total < 52) {
            Card card=deck.removeFirst();

            if(card == null) {
                break;
            }

            cards[total]=card;
            total++;
        }

        // mezclar las cartas
        Random random=new Random();

        for (int i = total-1; i > 0; i--) {
            int j=random.nextInt(i+1);

            Card aux = cards[i];
            cards[i] = cards[j];
            cards[j]=aux;
        }

        for(int i=0; i<total; i++) {
            deck.addLast(cards[i]);
        }
    }

    public void deal() {

        // repartir las cartas 48
        for (int i = 0; i < 6; i++) {
            for(int j=0; j<8; j++) {
                Card card=deck.removeFirst();
                columns[j].addFirst(card);
            }
        }

        for(int i=0; i<4; i++) {
            reserves[i]=deck.removeFirst();
        }
    }


    private Card getTop(int position) {

        // c 0-7, r 8-15, f 16-19
        if (position >= 0 && position < 8) {
            return columns[position].get(0);
        }

        if(position >= 8 && position<16) {
            return reserves[position-8];
        }

        if (position >= 16 && position < 20) {
            return foundations[position-16].get(0);
        }

        return null;
    }

    private boolean sameSuit(Card a, Card b) {

        return a.getSuit().equals(b.getSuit());
    }


    private boolean validSequence(int column,int count) {

        if (column < 0 || column >= 8 || count < 1) {
            return false;
        }
        if(columns[column].size() < count) {
            return false;
        }

        // revisar el orden y palo
        for (int i = 0; i < count-1; i++) {

            Card a=columns[column].get(i);
            Card b = columns[column].get(i+1);

            if (!sameSuit(a,b) || a.getValue()+1 != b.getValue()) {
                return false;
            }
        }
        return true;
    }


    private int maxCards(int from, int to) {
        int libres=0;

        for(int i = 0; i < 8; i++) {
            if (reserves[i] == null) {
                libres++;
            }
        }

       return libres+1;

    }

    public boolean validMove(int from,int to) {

        return validMove(from,to,1);
    }


    public boolean validMove(int from, int to, int count) {

        if (from < 0 || from > 19 || to < 0 || to > 19) {
            return false;
        }

        if(from == to || count < 1) {
            return false;
        }

        if (from >= 16) {
            return false;
        }

        Card card=getTop(from);

        if(card == null) {
            return false;
        }

        if (from >= 8 && count != 1) {
            return false;
        }

        if(from < 8 && !validSequence(from,count)) {
            return false;
        }


        if (to < 8) {

            Card lastCard=card;

            if(count > 1) {
                lastCard=columns[from].get(count-1);
            }

            Card top = getTop(to);

            // columna vacia, solo reyes
            if (top == null) {
                if(lastCard.getValue() != 13) {
                    return false;
                }
            }
            else {
                if (!sameSuit(top,lastCard)) {
                    return false;
                }

                if(top.getValue() != lastCard.getValue()+1) {
                    return false;
                }
            }

            if (count > 1 && count > maxCards(from,to)) {
                return false;
            }

            return true;
        }


        if(to < 16) {

            if (count != 1) {
                return false;
            }

            if(from >= 8) {
                return false;
            }

            return reserves[to-8] == null;
        }


        if (count != 1) {
            return false;
        }

        int foundation=to-16;
        Card top=foundations[foundation].get(0);

        if(top == null) {
            return card.getValue()==1;
        }

        return sameSuit(card,top) && card.getValue() == top.getValue()+1;
    }



    private Card removeCard(int position) {

        if (position < 8) {
            return columns[position].removeFirst();
        }

        if(position < 16) {
            Card card=reserves[position-8];
            reserves[position-8] = null;

            return card;
        }

        return foundations[position-16].removeFirst();
    }


    private void addCard(int position, Card card) {
        if (position < 8) {
            columns[position].addFirst(card);
        }
        else if(position < 16) {
            reserves[position-8]=card;
        }
        else {
            foundations[position-16].addFirst(card);
        }
    }


    private void transfer(int from,int to,int count) {

        Card[] cards = new Card[count];

        for(int i = 0; i<count; i++) {
            cards[i]=removeCard(from);
        }

        for (int i = count-1; i >= 0; i--) {
            addCard(to,cards[i]);
        }
    }

    public boolean moveCard(int from, int to) {
        return moveCard(from,to,1);
    }

    public boolean moveCard(int from,int to,int count) {

        if (!validMove(from,to,count)) {
            return false;
        }

        Card card=getTop(from);
        transfer(from,to,count);

        // guardar el movimiento
        Move move = new Move(card,from,to,count);
        history.addFirst(move);

        moves++;
        return true;
    }


    public boolean undo() {
        Move move=history.removeFirst();

        if (move == null) {
            return false;
        }

        transfer(move.getTo(),move.getFrom(),move.getCount());

        if(moves > 0) {
            moves--;
        }
        return true;
    }


    public boolean isWon() {

        int total=0;
        for (int i = 0; i < 4; i++) {
            total+=foundations[i].size();
        }

        return total == 52;
    }


    public Move hint() {

        // buscar un movimiento disponible
        for (int i = 0; i < 16; i++) {
            for(int j=16; j<20; j++) {

                if(validMove(i,j)) {
                    return new Move(getTop(i),i,j);
                }
            }
        }

        for(int i=0; i<16; i++) {
            for (int j = 0; j < 8; j++) {
                if (validMove(i,j)) {
                    return new Move(getTop(i),i,j);
                }
            }
        }


        for (int i = 0; i < 8; i++) {
            for(int j=8; j<16; j++) {

                if(validMove(i,j)) {
                    return new Move(getTop(i),i,j);
                }
            }
        }

        return null;
    }

    public boolean hasMoves() {
        return hint()!=null;
    }

    public boolean isBlocked() {
        return !isWon() && !hasMoves();
    }


    public int getMoves() {
        return moves;
    }

    public Card getReserve(int position) {

        if (position < 0 || position >= 8) {
            return null;
        }

        return reserves[position];
    }


    public Card[] getColumn(int position) {

        if(position < 0 || position >= 8) {
            return new Card[0];
        }

        int total=columns[position].size();
        Card[] cards = new Card[total];

        for (int i = 0; i < total; i++) {
            cards[total-1-i]=columns[position].get(i);
        }

        return cards;
    }

    public Card getFoundation(int position) {

        if (position < 0 || position >= 4) {
            return null;
        }

        return foundations[position].get(0);
    }
}

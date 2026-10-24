package com.example.practica4_listas.logic;

public class PruebaJuego {

    public static void main(String[] args) {

        GameFlow juego = new GameFlow();
        juego.startGame();

        System.out.println("Cartas\n");

        for (int i =0; i < 8; i++) {
            Card[] cartas = juego.getColumn(i);

            System.out.println("Columna " + (i+1));

            for(Card carta : cartas) {
                System.out.println(carta);
            }
            System.out.println();
        }

        System.out.println("Reserva");

        for(int i = 0;i < 8; i++) {
            System.out.println("Reserva " + (i+1) + ": " + juego.getReserve(i));
        }


        System.out.println("\nPrueba");

        Card cartaAnterior = juego.getColumn(0)[5];

        boolean movimiento= juego.moveCard(0,12);
        System.out.println("Movimiento realizado: " + movimiento);
        System.out.println("Carta en reserva 5: " + juego.getReserve(4));

        System.out.println("\nDESHACER");

        boolean deshacer=juego.undo();

        System.out.println("Se deshizo: " + deshacer);
        System.out.println("Reserva 5: " + juego.getReserve(4));
        System.out.println("Carta regresada: " + juego.getColumn(0)[5]);
        System.out.println("Movimientos actuales: " + juego.getMoves());
    }
}
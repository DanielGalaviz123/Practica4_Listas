
package com.example.practica4_listas.logic;

public class PruebaJuego {

    public static void main(String[] args) {

        GameFlow juego = new GameFlow();

        int origen=-1;
        int destino=-1;
        int intentos=0;

        // buscar una partida con un movimiento de dos cartas
        for(int k=0; k<5000 && origen==-1; k++) {

            juego.startGame();
            intentos++;

            for(int i=0; i<8 && origen==-1; i++) {
                for(int j=0; j<8; j++) {

                    if(juego.validMove(i,j,2)) {
                        origen=i;
                        destino=j;
                        break;
                    }
                }
            }
        }

        if(origen == -1) {
            System.out.println("No se encontro un movimiento de dos cartas");
            return;
        }

        System.out.println("Movimiento encontrado despues de " + intentos + " partidas");

        Card[] cartasOrigen=juego.getColumn(origen);
        Card[] cartasDestino=juego.getColumn(destino);

        System.out.println("\nANTES DEL MOVIMIENTO");

        System.out.println("Columna origen: " + (origen+1));
        System.out.println("Carta 1: " + cartasOrigen[cartasOrigen.length-2]);
        System.out.println("Carta 2: " + cartasOrigen[cartasOrigen.length-1]);

        System.out.println("Columna destino: " + (destino+1));
        System.out.println("Carta superior: " + cartasDestino[cartasDestino.length-1]);


        System.out.println("\nPRUEBA DE MOVIMIENTO");

        boolean movimiento=juego.moveCard(origen,destino,2);

        System.out.println("Movimiento realizado: " + movimiento);
        System.out.println("Movimientos actuales: " + juego.getMoves());

        Card[] despues=juego.getColumn(destino);

        System.out.println("\nCartas superiores del destino:");
        System.out.println(despues[despues.length-2]);
        System.out.println(despues[despues.length-1]);


        System.out.println("\nDESHACER");

        boolean deshacer=juego.undo();

        System.out.println("Se deshizo: " + deshacer);
        System.out.println("Movimientos actuales: " + juego.getMoves());

        Card[] regresaron=juego.getColumn(origen);

        System.out.println("\nCartas que regresaron:");
        System.out.println(regresaron[regresaron.length-2]);
        System.out.println(regresaron[regresaron.length-1]);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class P52DanielNavarro {

    public static void main(String[] args) {
        //matriz de 7 por 7
        int[][] matriz = new int[8][8];

        //rellenar la matriz de 0s
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }
        enseñarMatriz(matriz);

        //poner el bicho
        matriz = rellenarMatriz(matriz);
        System.out.println("\n\nmatriz rellena:");
        enseñarMatriz(matriz);
        System.out.println("\n");

        //buscar bicho
        //primero recorro la matriz para encontrar donde esta la primera casilla:
        Casilla casillaInicial = new Casilla(0, 0);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {

                    casillaInicial.setFila(i);
                    casillaInicial.setColumna(j);
                    i = matriz.length - 1;
                    j = matriz[i].length - 1;
                }

            }
        }
                
        ArrayList<Casilla> listaBicho = new ArrayList<>();
        encontrarBicho(matriz, casillaInicial, listaBicho);
        listaBicho.forEach((k) -> System.out.println(k));
        
        rellenarMatriz(matriz);
        
        ArrayList<Casilla> listaIterativo = new ArrayList<>();
        listaIterativo = encontrarIterativo(matriz, casillaInicial);

        //enseñar bicho
        System.out.println("Enseñar el reiterativo");
        listaBicho.forEach((k) -> System.out.println(k));
        
        System.out.println("enseñar iterativo");
        listaIterativo.forEach((k) -> System.out.println(k));

    }

    //metodo recibe la matriz rellena y devuelve la lista donde esta el bicho
    public static Casilla encontrarBicho(int[][] matriz, Casilla casilla, ArrayList listaBicho) {
        // primero comprueba si no se sale de la matriz con un if, dentro del if hay otro if que comprueba si la casilla 
        //introducia es un uno, si lo es recursivamente comprueba la casilla superior,inferior derecha e izquierda, y estas
        // a su vez comprobaran las demas casillas-- si encuentra, cambia el valor a -1 para no volver a encontrarla

        if (!(casilla.getFila() < 0 || casilla.getFila() > matriz.length - 1)
                && !(casilla.getColumna() < 0 || casilla.getColumna() > matriz.length - 1)) {

            if (matriz[casilla.getFila()][casilla.getColumna()] == 1) {
                matriz[casilla.getFila()][casilla.getColumna()] = -1;
                listaBicho.add(new Casilla(casilla.getFila(), casilla.getColumna()));
                encontrarBicho(matriz, new Casilla(casilla.getFila() + 1, casilla.getColumna()), listaBicho);
                encontrarBicho(matriz, new Casilla(casilla.getFila(), casilla.getColumna() + 1), listaBicho);
                encontrarBicho(matriz, new Casilla(casilla.getFila() - 1, casilla.getColumna()), listaBicho);
                encontrarBicho(matriz, new Casilla(casilla.getFila(), casilla.getColumna() - 1), listaBicho);

            }

        }

        return casilla;
    }

    public static ArrayList encontrarIterativo(int[][] matriz, Casilla casilla) {
        ArrayList<Casilla> listaDevolver = new ArrayList<>();
        boolean repetir = true;
        //falta comprobar si se sale por algun sitio
        do {
            if (matriz[casilla.getFila()][casilla.getColumna()] == 1) {
                listaDevolver.add(new Casilla(casilla.getFila(), casilla.getColumna()));
                matriz[casilla.getFila()][casilla.getColumna()] = -1;
                if (matriz[casilla.getFila() - 1][casilla.getColumna()] == 1) {
                    casilla.setFila(casilla.getFila() - 1);
                } else if (matriz[casilla.getFila() + 1][casilla.getColumna()] == 1) {
                    casilla.setFila(casilla.getFila() + 1);
                } else if (matriz[casilla.getFila()][casilla.getColumna() - 1] == 1) {
                    casilla.setColumna(casilla.getColumna() - 1);
                } else if (matriz[casilla.getFila()][casilla.getColumna() + 1] == 1) {
                    casilla.setColumna(casilla.getColumna() + 1);
                }

            } else {
                repetir = false;
            }
        } while (repetir);

        return listaDevolver;
    }

    public static int[][] rellenarMatriz(int[][] matriz) {
        matriz[2][2] = 1;
        matriz[2][3] = 1;
        matriz[2][4] = 1;
        matriz[2][5] = 1;
        matriz[3][5] = 1;
        matriz[4][5] = 1;
        matriz[4][4] = 1;
        matriz[4][3] = 1;
        matriz[4][2] = 1;
        matriz[5][2] = 1;
        matriz[6][2] = 1;
        matriz[6][3] = 1;
        //matriz[5][5] = 1;
        //matriz[5][6] = 1;
        //matriz[6][6] = 1;

        return matriz;
    }

    public static void enseñarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
        }

    }
}

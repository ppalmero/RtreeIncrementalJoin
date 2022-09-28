/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import bdet.comun.Rectangulo;
import bdet.rtree.Dato;
import bdet.rtree.RTree;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import principal.estructura.Estructura;
import principal.estructura.IncDistJoin;
import principal.estructura.IncDistJoinParva;

/**
 *
 * @author Soporte_Tecnico
 */
public class Inicial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Dato[] animales = new Dato[11];
        animales[0] = new Dato(new Rectangulo(new Point(2, 7)), 0);
        animales[1] = new Dato(new Rectangulo(new Point(5, 4)), 1);
        animales[2] = new Dato(new Rectangulo(new Point(10, 1)), 2);
        animales[3] = new Dato(new Rectangulo(new Point(13, 5)), 3);
        animales[4] = new Dato(new Rectangulo(new Point(16, 8)), 4);
        animales[5] = new Dato(new Rectangulo(new Point(8, 9)), 5);
        animales[6] = new Dato(new Rectangulo(new Point(10, 10)), 6);
        animales[7] = new Dato(new Rectangulo(new Point(15, 15)), 7);
        animales[8] = new Dato(new Rectangulo(new Point(7, 16)), 8);
        animales[9] = new Dato(new Rectangulo(new Point(19, 17)), 9);
        animales[10] = new Dato(new Rectangulo(new Point(12, 19)), 10);

        RTree rtreeA = new RTree();
        rtreeA.insertar(animales);

        Dato[] comederos = new Dato[3];
        comederos[0] = new Dato(new Rectangulo(new Point(7, 4)), 0);
        comederos[1] = new Dato(new Rectangulo(new Point(8, 12)), 1);
        comederos[2] = new Dato(new Rectangulo(new Point(14, 15)), 2);

        RTree rtreeC = new RTree();
        rtreeC.insertar(comederos);

        HashMap resultado = new HashMap<>();
        rtreeA.consultar(resultado, new Rectangulo(9, 3, 17, 11), null);
        System.out.println(resultado.toString());

        Rectangulo rPrueba = new Rectangulo(6, 1, 8, 5);
        System.out.println(rPrueba.distancia(new Rectangulo(3, 7, 4, 9)));

        /**
         * * CALCULAR TIEMPO **
         */
        long inicio = System.currentTimeMillis();

        Map<Integer, ArrayList<Estructura>> vecinos = new IncDistJoin(rtreeC, rtreeA).getResultado();
 
        
        
        for (int comedero : vecinos.keySet()) {
            for (Estructura e : vecinos.get(comedero)) {
                System.out.println(((Dato) e.r1).getOid() + " - " + ((Dato) e.r2).getOid() + " - Distancia: " + e.d);
            }
        }

        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - inicio));
        System.out.println(tiempo + " segundos");
    }

}

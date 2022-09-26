/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.estructura;

import bdet.rtree.Dato;
import bdet.rtree.Entrada;
import bdet.rtree.Nodo;
import bdet.rtree.RTree;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Soporte_Tecnico
 */

class The_Comparator implements Comparator<Estructura> {
    public int compare(Estructura t, Estructura t1) {
        return t.d.compareTo(t.d);
    }
}

public class IncDistJoinParva {

    RTree r1, r2;
    ArrayList<Estructura> resultado;

    public IncDistJoinParva(RTree r1, RTree r2) {
        resultado = new ArrayList<>();
        this.r1 = r1;
        this.r2 = r2;

        PriorityQueue<Estructura> q = new PriorityQueue(100, new The_Comparator());
        q.add(new Estructura(this.r1.raiz, this.r2.raiz, 0));

        while (!q.isEmpty()) {
            Estructura elem = q.poll();
            /*if (elem.r1 instanceof Dato && elem.r2 instanceof Dato) {
                float d = ((Dato) elem.r1).getLimites().distancia(((Dato) elem.r2).getLimites());
                if (q.isEmpty() || d <= q.peek().d) {
                    
                    resultado.add(elem);
                } else {
                    elem.d = d;
                    q.add(elem);
                }
            } else*/ if (elem.r1 instanceof Nodo) {
                procesarNodo1(q, elem);
            } else if (elem.r2 instanceof Nodo) {
                procesarNodo2(q, elem);
            } else {
                resultado.add(elem);
            }
        }
    }

    private void procesarNodo1(PriorityQueue q, Estructura elem) {
        Nodo nodo = (Nodo) elem.r1;
        Entrada item2 = elem.r2;
        //if (nodo.isEsHoja()){
        for (int i = 0; i < nodo.getNumeroHijos(); i++) {
            q.add(new Estructura(nodo.getHijo(i), item2, nodo.getHijo(i).getLimites().distancia(item2.getLimites())));
        }
        //} else {

        //}
    }

    private void procesarNodo2(PriorityQueue q, Estructura elem) {
        Nodo nodo = (Nodo) elem.r2;
        Entrada item2 = elem.r1;
        for (int i = 0; i < nodo.getNumeroHijos(); i++) {
            q.add(new Estructura(item2, nodo.getHijo(i), nodo.getHijo(i).getLimites().distancia(item2.getLimites())));
        }
    }

    public ArrayList<Estructura> getResultado() {
        return resultado;
    }

}

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

/**
 *
 * @author Soporte_Tecnico
 */
public class IncDistJoin {

    RTree r1, r2;
    ArrayList<Estructura> resultado;

    public IncDistJoin(RTree r1, RTree r2) {
        resultado = new ArrayList<>();
        this.r1 = r1;
        this.r2 = r2;

        Cola q = new Cola();
        q.enQueue(new Estructura(this.r1.raiz, this.r2.raiz, 0));

        while (!q.isEmpty()) {
            Estructura elem = q.deQueue();
            if (elem.r1 instanceof Dato && elem.r2 instanceof Dato) {
                float d = ((Dato) elem.r1).getLimites().distancia(((Dato) elem.r2).getLimites());
                if (q.isEmpty() || d <= q.front().d) {
                    int i = 0;
                    for (i = 0; i < resultado.size(); i++){
                        if (elem.d < resultado.get(i).d) {
                            break;
                        }
                    }
                    resultado.add(i,elem);
                } else {
                    elem.d = d;
                    q.enQueue(elem);
                }
            } else if (elem.r1 instanceof Nodo) {
                procesarNodo1(q, elem);
            } else {
                procesarNodo2(q, elem);
            }
        }
    }

    private void procesarNodo1(Cola q, Estructura elem) {
        Nodo nodo = (Nodo) elem.r1;
        Entrada item2 = elem.r2;
        //if (nodo.isEsHoja()){
        for (int i = 0; i < nodo.getNumeroHijos(); i++) {
            q.enQueue(new Estructura(nodo.getHijo(i), item2, nodo.getHijo(i).getLimites().distancia(item2.getLimites())));
        }
        //} else {

        //}
    }

    private void procesarNodo2(Cola q, Estructura elem) {
        Nodo nodo = (Nodo) elem.r2;
        Entrada item2 = elem.r1;
        for (int i = 0; i < nodo.getNumeroHijos(); i++) {
            q.enQueue(new Estructura(item2, nodo.getHijo(i), nodo.getHijo(i).getLimites().distancia(item2.getLimites())));
        }
    }

    public ArrayList<Estructura> getResultado() {
        return resultado;
    }

}

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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Soporte_Tecnico
 */
public class IncDistJoin {

    RTree r1, r2;
    Map<Integer, ArrayList<Estructura>> resultado;

    public IncDistJoin(RTree r1, RTree r2) {
        resultado = new HashMap<>();
        this.r1 = r1;
        this.r2 = r2;

        Cola q = new Cola();
        q.enQueue(new Estructura(this.r1.raiz, this.r2.raiz, 0));

        while (!q.isEmpty()) {
            Estructura elem = q.deQueue();
            if (elem.r1 instanceof Dato && elem.r2 instanceof Dato) {
                float d = ((Dato) elem.r1).getLimites().distancia(((Dato) elem.r2).getLimites());
                if (q.isEmpty() || d <= q.front().d) {
                    if (resultado.containsKey(((Dato) elem.r1).getOid())) {
                        ArrayList<Estructura> resParcial = resultado.get(((Dato) elem.r1).getOid());
                        int i;
                        for (i = 0; i < resParcial.size(); i++) {
                            if (elem.d < resParcial.get(i).d) {
                                break;
                            }
                        }
                        resParcial.add(i, elem);
                    } else {
                        ArrayList<Estructura> resIndividual = new ArrayList<>();
                        resIndividual.add(elem);
                        resultado.put(((Dato) elem.r1).getOid(), resIndividual);
                    }

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

    public Map<Integer, ArrayList<Estructura>> getResultado() {
        return resultado;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.estructura;

import bdet.rtree.Entrada;
import bdet.rtree.Nodo;
import java.util.Queue;

/**
 *
 * @author Pablo
 */
public class Estructura {
    public Float d;
    public Entrada r1;
    public Entrada r2;

    public Estructura(Entrada r1, Entrada r2) {
        this.r1 = r1;
        this.r2 = r2;
        d = calcularDistancia(r1, r2);
    }
    
    public Estructura(Entrada r1, Entrada r2, float d) {
        this.r1 = r1;
        this.r2 = r2;
        this.d = d;
    }

    private float calcularDistancia(Entrada r1, Entrada r2) {
        if (r1 instanceof Nodo && r2 instanceof Nodo){
            
        } else if (r1 instanceof Nodo){
            
        } else if (r2 instanceof Nodo){
            
        } else { //r1 y r2 son instancia de Dato
            
        }
        return 0;
    }
    
}

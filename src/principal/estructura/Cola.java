/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.estructura;

import bdet.rtree.Entrada;

/**
 *
 * @author Soporte_Tecnico
 */
public class Cola {

    int queueLength = 100;
    Estructura items[] = new Estructura[queueLength];
    int front = -1;
    int back = -1;

    boolean isFull() {
        return back == queueLength - 1;
    }

    boolean isEmpty() {
        return front == -1 && back == -1;
    }

    void enQueue(Estructura itemValue) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else if (front == -1 && back == -1) {
            front = back = 0;
            items[back] = itemValue;
        } else {
            back++;
            items[back] = itemValue;
        }
    }

    Estructura deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Nothing to dequeue");
            return null;
        } else {
            Estructura retorno = items[front];
            if (front == back) {
                front = back = -1;
            } else {
                front++;
            }
            return retorno;
        }
    }

    void display() {
        int i;

        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            for (i = front; i <= back; i++) {
                System.out.println(items[i]);
            }
        }
    }

    void peak() {
        System.out.println("Front value is: " + items[front]);
    }

    Estructura front(){
        return items[front];
    }
}

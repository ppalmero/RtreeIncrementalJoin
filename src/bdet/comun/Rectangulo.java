package bdet.comun;

import java.awt.Point;

public class Rectangulo {

    public final float xmin, ymin, xmax, ymax; // L�mites del rect�ngulo

    public Rectangulo() {
        xmin = ymin = Float.POSITIVE_INFINITY;
        xmax = ymax = Float.NEGATIVE_INFINITY;
    }

    public Rectangulo(float xmin, float ymin, float xmax, float ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public Rectangulo(Punto punto) {
        this.xmin = this.xmax = punto.x;
        this.ymin = this.ymax = punto.y;
    }

    public Rectangulo(Point punto) {
        this.xmin = this.xmax = (int) punto.getX();
        this.ymin = this.ymax = (int) punto.getY();
    }

    public Punto getCentro() {
        return (new Punto((xmin + xmax) / 2.0f, (ymin + ymax) / 2.0f));
    }

    public Rectangulo agregar(Rectangulo r) {
        return (new Rectangulo(r.xmin < xmin ? r.xmin : xmin, r.ymin < ymin ? r.ymin : ymin, r.xmax > xmax ? r.xmax : xmax, r.ymax > ymax ? r.ymax : ymax));
    }

    public boolean intersecta(Rectangulo r) {
        return (r.xmin <= xmax && r.xmax >= xmin && r.ymin <= ymax && r.ymax >= ymin);
    }

    public boolean intersecta(Punto p) {
        return intersecta(new Rectangulo(p));
    }

    public String toString() {
        return ((new Punto(xmin, ymin)).toString() + " - " + (new Punto(xmax, ymax)).toString());
    }

    public boolean equals(Object o) {
        if (o instanceof Rectangulo) {
            if (((Rectangulo) o).xmin == xmin
                    && ((Rectangulo) o).ymin == ymin
                    && ((Rectangulo) o).xmax == xmax
                    && ((Rectangulo) o).ymax == ymax) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public float distancia(Rectangulo r) {
        if (intersecta(r)) {
            return 0;
        } else {
            float[] dist = new float[8];
            dist[0] = distSegmentoH(xmin, ymin, xmax, ymin, r.xmin, r.ymin, r.xmax, r.ymin);
            dist[1] = distSegmentoH(xmin, ymin, xmax, ymin, r.xmin, r.ymax, r.xmax, r.ymax);

            dist[2] = distSegmentoH(xmin, ymax, xmax, ymax, r.xmin, r.ymin, r.xmax, r.ymin);
            dist[3] = distSegmentoH(xmin, ymax, xmax, ymax, r.xmin, r.ymax, r.xmax, r.ymax);

            dist[4] = distSegmentoV(xmin, ymin, xmin, ymax, r.xmin, r.ymin, r.xmin, r.ymax);
            dist[5] = distSegmentoV(xmin, ymin, xmin, ymax, r.xmax, r.ymin, r.xmax, r.ymax);

            dist[6] = distSegmentoV(xmax, ymin, xmax, ymax, r.xmin, r.ymin, r.xmin, r.ymax);
            dist[7] = distSegmentoV(xmax, ymin, xmax, ymax, r.xmax, r.ymin, r.xmax, r.ymax);

            float distanciaMenor = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                if (dist[i] < distanciaMenor) {
                    distanciaMenor = dist[i];
                }
            }
            return distanciaMenor;
        }
    }

    private float distSegmentoH(float xmin, float ymin, float xmax, float ymax, float xmin0, float ymin0, float xmax0, float ymax0) {
        if (xmin <= xmax0 && xmax >= xmin0) {//intersecan?
            return Math.abs(ymin - ymin0);
        } else if (xmin < xmin0) {
            return (float) Math.sqrt(Math.pow((xmax - xmin0), 2) + Math.pow((ymin - ymin0), 2));
        } else {
            return (float) Math.sqrt(Math.pow((xmin - xmax0), 2) + Math.pow((ymin - ymin0), 2));
        }
    }

    private float distSegmentoV(float xmin, float ymin, float xmax, float ymax, float xmin0, float ymin0, float xmax0, float ymax0) {
        if (ymin <= ymax0 && ymax >= ymin0) {//intersecan?
            return Math.abs(xmin - xmin0);
        } else if (ymin < ymin0) {
            return (float) Math.sqrt(Math.pow((ymax - ymin0), 2) + Math.pow((xmin - xmin0), 2));
        } else {
            return (float) Math.sqrt(Math.pow((ymin - ymax0), 2) + Math.pow((xmin - xmin0), 2));
        }
    }
}

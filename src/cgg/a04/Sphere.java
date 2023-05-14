package cgg.a04;


import static cgtools.Vector.*;

import cgtools.Direction;

public class Sphere implements Shape {

    private double rad;
    private Direction center;
    private Material material;

    Sphere(double r, Direction center, Material material) {
        this.rad = r;
        this.center = center;
        this.material = material;
    }

    public Hit intersect(Raytracer r) {
        Direction d = r.d;
        Direction x0 = subtract(r.x0, center);

        double a = dotProduct(d, d);
        double b = dotProduct(multiply(2, x0), d);
        double c = dotProduct(x0, x0) - Math.pow(rad, 2);

        double denominator = 2 * a;
        if (denominator == 0) return null;

        double dis = (Math.pow(b, 2) - (4 * a * c));
        Hit h = null;

        if (dis >= 0) {
            double t1 = (-b + Math.sqrt(dis)) / denominator;
            Direction norm = divide(subtract(r.pointAt(t1), center), rad);
            if (dis == 0) {
                if (t1 > r.t0 && t1 < r.t1) h = new Hit(t1, r.pointAt(t1), normalize(norm), material);
            }
            if (dis > 0) {
                double t2 = (-b - Math.sqrt(dis)) / denominator;

                double t;
                double min = Math.min(t1, t2);
                double max = Math.max(t1, t2);
                if (min > r.t0) t = min;
                else {
                    if (max > r.t0) t = max;
                    else return null;
                }

                if (t > r.t0 && t < r.t1) {
                    norm = divide(subtract(r.pointAt(t), center), rad);
                    h = new Hit(t, r.pointAt(t), normalize(norm), material);
                }
            }
        }

        return h;
    }

}

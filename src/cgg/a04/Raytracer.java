package cgg.a04;

import cgtools.Direction;
import cgtools.Vector;

public class Raytracer {

    final Direction x0;
    final Direction d;
    final double t0, t1;

    public Raytracer(Direction x0, Direction d) {
        this.x0 = x0;
        this.d = Vector.normalize(d);
        this.t0 = 0.0001;
        this.t1 = Double.POSITIVE_INFINITY;
    }

    Direction pointAt(double t) {
        return Vector.add(x0, Vector.multiply(t,d));
    }

}

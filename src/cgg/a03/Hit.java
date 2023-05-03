package cgg.a03;

import cgtools.Vec3;

/**
 * Schnittpunkt.
 * Created by young on 05.11.2017.
 */
public class Hit {

    private double t;
    private Vec3 s;
    private Vec3 n;

    Hit(double t, Vec3 s, Vec3 n) {
        this.t = t;
        this.s = s;
        this.n = n;
    }

    Vec3 getN() {
        return n;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", s=" + s +
                ", n=" + n +
                '}';
    }
}

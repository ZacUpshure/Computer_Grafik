package cgg.a03;

import cgtools.Vec3;

/**
 * der Strahl.
 * Created by young on 05.11.2017.
 */
public class Ray {

    private Vec3 x0;
    private Vec3 d;

    public Ray(Vec3 x0, Vec3 d) {
        this.x0 = x0;
        this.d = Vec3.normalize(d);
    }

    Vec3 pointAt(double t) {
        return Vec3.add(x0, Vec3.multiply(t, d));
    }

    public Vec3 getX0() {
        return x0;
    }

    public Vec3 getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "x0=" + x0 +
                ", d=" + d +
                '}';
    }

    public static void main(String[] args) {
        Ray r = new Ray(Vec3.vec3(0, 0, 0), Vec3.vec3(3, 4, 5));
        System.out.println(r.pointAt(1));
    }

}

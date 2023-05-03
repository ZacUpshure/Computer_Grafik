package cgg.a03;

import cgtools.Vec3;

/**
 * die Sphere.
 * Created by young on 05.11.2017.
 */
public class Kugel {

    private double rad;
    private Vec3 center;

    Kugel(double r, Vec3 center) {
        this.rad = r;
        this.center = center;
    }

    Hit intersect(Ray r) {
        Vec3 d = r.getD();
        Vec3 x0 = Vec3.subtract(r.getX0(), center);

        double a = Vec3.dotProduct(d, d);
        double b = Vec3.dotProduct(Vec3.multiply(2, x0), d);
        double c = Vec3.dotProduct(x0, x0) - Math.pow(rad, 2);

        double dis = (Math.pow(b, 2) - (4 * a * c));

        double t1 = (-b + Math.sqrt(dis)) / (2 * a);
        double t2 = (-b - Math.sqrt(dis)) / (2 * a);

        Vec3 schnittT1 = r.pointAt(t1);
        Vec3 schnittT2 = r.pointAt(t2);

        Vec3 schnittT1Norm = Vec3.normalize(Vec3.divide(Vec3.subtract(schnittT1, center), rad));
        Vec3 schnittT2Norm = Vec3.normalize(Vec3.divide(Vec3.subtract(schnittT2, center), rad));

        if (dis == 0) return new Hit(t1, schnittT1, schnittT1Norm);

        if (dis > 0) {
            if (t1 > 0 && t1 < t2 || t1 > 0 && t2 < 0) return new Hit(t1, schnittT1, schnittT1Norm);
            else if (t2 > 0 && t2 < t1 || t2 > 0 && t1 < 0) return new Hit(t2, schnittT2, schnittT2Norm);
        }

        return null;
    }

    public static void main(String[] args) {
        Kugel k = new Kugel(1, Vec3.vec3(0, 0, -2));
        System.out.println(k.intersect(new Ray(Vec3.vec3(0, 0, 0), Vec3.vec3(0, 0, -1))));

        k = new Kugel(1, Vec3.vec3(0, 0, -2));
        System.out.println(k.intersect(new Ray(Vec3.vec3(0, 0, 0), Vec3.vec3(0, 1, -1))));

        k = new Kugel(1, Vec3.vec3(0, -1, -2));
        System.out.println(k.intersect(new Ray(Vec3.vec3(0, 0, 0), Vec3.vec3(0, 0, -1))));

        k = new Kugel(1, Vec3.vec3(0, 0, -2));
        System.out.println(k.intersect(new Ray(Vec3.vec3(0, 0, -4), Vec3.vec3(0, 0, -1))));
    }
}

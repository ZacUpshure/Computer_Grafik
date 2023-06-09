package cgg.a03;

import cgtools.Vec3;

public class Kugel implements Shape {

    private double rad;
    private Vec3 center;
    private Material material;
    
    Kugel(double r, Vec3 center, Material material) {
        this.rad = r;
        this.center = center;
        this.material = material;
    }

    public Hit intersect(Ray r) {
        Vec3 d = r.d;
        Vec3 x0 = Vec3.subtract(r.x0, center);

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

        if (dis == 0) return new Hit(t1, schnittT1, schnittT1Norm, material);

        if (dis > 0) {
            if (t1 > 0 && t1 < t2 || t1 > 0 && t2 < 0) return new Hit(t1, schnittT1, schnittT1Norm, material);
            else if (t2 > 0 && t2 < t1 || t2 > 0 && t1 < 0) return new Hit(t2, schnittT2, schnittT2Norm, material);
        }

        return null;
    }

}

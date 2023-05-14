package cgg.a03;

import cgtools.Vec3;

public class Hit {

    final double t;
    final Vec3 position;
    final Vec3 normal;
    final Material material;

    public Hit(double t, Vec3 position, Vec3 normal, Material material) {
        this.t = t;
        this.position = position;
        this.normal = normal;
        this.material = material;
    }

}

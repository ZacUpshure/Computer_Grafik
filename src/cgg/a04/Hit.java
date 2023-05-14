package cgg.a04;

import cgtools.Direction;

public class Hit {

    final double t;
    final Direction position;
    final Direction normal;
    final Material material;

    public Hit(double t, Direction position, Direction normal, Material material) {
        this.t = t;
        this.position = position;
        this.normal = normal;
        this.material = material;
    }

}

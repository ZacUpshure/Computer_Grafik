package cgg.a04;

import cgtools.Vector;

public class Background implements Shape {

    private Material material;

    Background(Material material) {
        this.material = material;
    }

    @Override
    public Hit intersect(Raytracer r) {
        return new Hit(r.t1, r.pointAt(r.t1), Vector.direction(1,0,0), material);
    }
}

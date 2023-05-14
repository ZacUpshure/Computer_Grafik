package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class MatColor implements Material {

    private Color alb;

    MatColor(Color alb) {
        this.alb = alb;
    }

    @Override
    public Color emittedRadiance(Raytracer r, Hit h) {
        return Vector.color(0);
    }

    @Override
    public Raytracer scatteredRay(Raytracer r, Hit h) {
        Direction drnd = Vector.direction(Random.random(), Random.random(), Random.random());

        while (Vector.length(drnd) > 1 || Vector.length(drnd) < -1) drnd = Vector.direction(Random.random(), Random.random(), Random.random());

        return new Raytracer(h.position, Vector.normalize(Vector.add(Vector.normalize(h.normal), drnd)));
    }

    @Override
    public Color albedo(Raytracer r, Hit h) {
        return alb;
    }
}

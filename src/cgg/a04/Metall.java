package cgg.a04;

import cgtools.Random;
import cgtools.Vector;

import static cgtools.Vector.*;

import cgtools.Color;
import cgtools.Direction;

public class Metall implements Material {

    private Color albedo;
    private double s;

    public Metall(Color albedo, double s) {
        this.albedo = albedo;
        this.s = s;
    }

    @Override
    public Color emittedRadiance(Raytracer r, Hit h) {
        return Vector.black;
    }

    @Override
    public Raytracer scatteredRay(Raytracer r, Hit h) {

        Direction xrnd = direction(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
        while (length(xrnd) > 1 || length(xrnd) < -1)
            xrnd = direction(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);

        Direction d = r.d;
        Direction n = normalize(h.normal);
        double skalarND = dotProduct(d, n);

        Direction dr = subtract(d, multiply(skalarND * 2, n));
        Direction ds = add(multiply(s, xrnd), normalize(dr));
        if (dotProduct(ds, n) < 0) return null;
        return new Raytracer(h.position, ds);
    }

    @Override
    public Color albedo(Raytracer r, Hit h) {
        return albedo;
    }
}

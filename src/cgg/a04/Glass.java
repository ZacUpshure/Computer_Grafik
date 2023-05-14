package cgg.a04;

import cgtools.Random;

import static cgtools.Vector.*;

import cgtools.Color;
import cgtools.Direction;


public class Glass implements Material {

    private static final double GLAS = 1.5;
    private static final double LUFT = 1.0;

    Color albedo;

    public Glass(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public Color emittedRadiance(Raytracer r, Hit h) {
        return black;
    }

    @Override
    public Raytracer scatteredRay(Raytracer r, Hit h) {
        Direction d = r.d;
        Direction n = h.normal;

        double n1 = LUFT;
        double n2 = GLAS;

        double nd = dotProduct(d, n);
        if (nd > 0) {
            double x = n1;
            n1 = n2;
            n2 = x;
            n = multiply(-1, n);
        }

        double refraction = n1 / n2;
        double c = dotProduct(multiply(-1, n), d);
        double sqrt = 1 - (Math.pow(refraction, 2) * (1 - Math.pow(c, 2)));

        double R0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double sch = R0 + (1 - R0) * Math.pow(1 + dotProduct(d, n), 5);
        Raytracer ray;

        if (sqrt >= 0 && Random.random() > sch) {
            Direction rd = multiply(refraction, d);
            double paren = refraction * c - Math.sqrt(sqrt);
            Direction dt = add(rd, multiply(paren, n));
            ray = new Raytracer(h.position, dt);
        } else {
            Direction dr = subtract(d, multiply(dotProduct(d, n) * 2, n));
            ray = new Raytracer(h.position, dr);
        }

        return ray;
    }

    @Override
    public Color albedo(Raytracer r, Hit h) {
        return albedo;
    }

}

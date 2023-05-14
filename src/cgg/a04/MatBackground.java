package cgg.a04;

import cgtools.Color;
import cgtools.Vector;

public class MatBackground implements Material {

    @Override
    public Color emittedRadiance(Raytracer r, Hit h) { return Vector.color(0.690, 0.819, 0.949); }

    @Override
    public Raytracer scatteredRay(Raytracer r, Hit h) {
        return null;
    }

    @Override
    public Color albedo(Raytracer r, Hit h) {
        return null;
    }
}

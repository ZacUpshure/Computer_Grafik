package cgg.a04;

import cgtools.Color;

public interface Material {

    Color emittedRadiance (Raytracer r, Hit h);
    Raytracer scatteredRay(Raytracer r, Hit h);
    Color albedo (Raytracer r, Hit h);

}

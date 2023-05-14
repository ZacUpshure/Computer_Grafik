package cgg.a04;

import cgtools.Direction;
import cgtools.Vector;

class Camera {

    private double phi;
    private int width;
    private int height;

    Camera(double phi, int width, int height) {
        this.phi = phi;
        this.width = width;
        this.height = height;
    }

    Raytracer generateRay(double x, double y) {
        double a = x - width / 2;
        double b = height / 2 - y;
        double c = -1 * ((width/2)/Math.tan(phi/2));

        return new Raytracer(Vector.direction(0,0,0), Vector.normalize(new Direction(a,b,c)));
    }

}

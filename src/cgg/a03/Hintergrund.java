package cgg.a03;

import cgtools.Vec3;

public class Hintergrund implements Material {

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) { return Vec3.vec3(0.690, 0.819, 0.949); }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return null;
    }
}

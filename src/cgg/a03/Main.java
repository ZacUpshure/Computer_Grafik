package cgg.a03;

import cgtools.Random;
import cgtools.Vec3;
import cgg.Image;

import java.io.IOException;

import static cgtools.Vec3.*;

public class Main {

    private static int sampling;

    private static Camera camera;
    private static Group group;

    public static void main(String[] args) {
        int width = 720;
        int height = 405;
        Image image = new Image(width, height);

        raytrace(new Camera(Math.PI / 2, width, height), genObjects(), 100);

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixelGamma(x, y, pixelColor(x, y));
            }
        }

        String filename = "doc/a03-spheres.png";

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    private static void raytrace(Camera c, Group g, int s) {
        camera = c;
        group = g;
        sampling = s;
    }

    private static Group genObjects() {
        return new Group(
                // new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new Lambertsches(vec3(0.396, 0.498, 0.603))), // boden

                // wolken
                // new Kugel(0.4, vec3(-7.8, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(-6.2, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.6, vec3(-7, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(7.8, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(6.2, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.6, vec3(7, 6, -15.5), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(-7.8, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(-6.2, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.6, vec3(-7, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(7.8, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.4, vec3(6.2, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),
                // new Kugel(0.6, vec3(7, 4, -9), new Lambertsches(vec3(0.949, 0.980, 0.988))),

                // steine
                // new Kugel(0.4, vec3(-4, -0.5, -8), new Lambertsches(vec3(0.250, 0.384, 0.729))),
                // new Kugel(0.4, vec3(4, -0.5, -8), new Lambertsches(vec3(0.250, 0.384, 0.729))),
                // new Kugel(0.8, vec3(-4, -0.5, -6), new Lambertsches(vec3(0.184, 0.243, 0.396))),
                // new Kugel(0.8, vec3(4, -0.5, -6), new Lambertsches(vec3(0.184, 0.243, 0.396))),
                // new Kugel(1.2, vec3(-4, -0.5, -4), new Lambertsches(vec3(0.227, 0.317, 0.552))),
                // new Kugel(1.2, vec3(4, -0.5, -4), new Lambertsches(vec3(0.227, 0.317, 0.552))),

                // new Kugel(1, vec3(0.0, 0.3, -3), new Lambertsches(vec3(0.913, 0.584, 0.686))), // body

                new Kugel(0.4, vec3(-0.5, -0.6, -1.5), new Lambertsches(vec3(0.701, 0.160, 0.290))), // linkes bein
                new Kugel(0.2, vec3(-1.5, 0.6, -2.5), new Lambertsches(vec3(0.701, 0.160, 0.290))), // linkes bein
                new Kugel(0.4, vec3(0.5, -0.4, -1.5), new Lambertsches(vec3(0.701, 0.260, 0.290))), // rechtes bein

                new Kugel(0.18, vec3(-0.8, 0.1, -2.2), new Lambertsches(vec3(0.807, 0.294, 0.415))), // linker arm
                new Kugel(0.18, vec3(0.8, 0.1, -2.2), new Lambertsches(vec3(0.807, 0.294, 0.415))), // rechter arm

                // new Kugel(0.13, vec3(-0.18, 0.6, -2.0), new Lambertsches(vec3(0))), // linkes auge
                // new Kugel(0.13, vec3(0.18, 0.6, -2.0), new Lambertsches(vec3(0))), // rechtes auge

                // new Kugel(0.02, vec3(-0.09, 0.33, -1.0), new Lambertsches(vec3(1))), // linkes auge weiss
                // new Kugel(0.02, vec3(0.09, 0.33, -1.0), new Lambertsches(vec3(1))), // rechtes auge weiss

                new Kugel(0.033, vec3(0.0, 0.175, -1.1), new Lambertsches(vec3(0))), // mund
                new Kugel(0.05, vec3(-0.2, 0.22, -1.0), new Lambertsches(vec3(0.964, 0.756, 0.819))), // linkes blush
                new Kugel(0.05, vec3(0.2, 0.22, -1.0), new Lambertsches(vec3(0.964, 0.756, 0.819))), // rechtes blush
                new Background(new Hintergrund())
        );
    }

    private static Vec3 pixelColor(int x, int y) {
        Vec3 bgColor = Vec3.white;

        double n = Math.sqrt(sampling);
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rdmx = Random.random();
                double rdmy = Random.random();
                double xs = x + ((xi + rdmx) / n);
                double ys = y + ((yi + rdmy) / n);
                Vec3 newColor = pixelColor(xs, ys);
                bgColor = Vec3.add(bgColor, newColor);
            }
        }

        bgColor = Vec3.divide(bgColor, sampling);
        return bgColor;
    }

    private static Vec3 radiance(Ray r, Shape g, int depth) {
        if (depth == 0) return Vec3.zero;

        Hit hit = g.intersect(r);

        Vec3 emission = hit.material.emittedRadiance(r, hit);
        Ray scattered = hit.material.scatteredRay(r, hit);

        if (scattered != null) {
            return Vec3.add(emission,
                    Vec3.multiply(hit.material.albedo(r, hit), radiance(scattered, g, depth - 1)));
        } else return emission;
    }

    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor;
        Ray ray = camera.generateRay(x, y);
        bgColor = radiance(ray, group, 5);
        return bgColor;
    }
    
}

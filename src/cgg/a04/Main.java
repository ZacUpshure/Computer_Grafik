package cgg.a04;

import cgtools.Color;
import cgtools.Random;

import java.io.IOException;

import cgg.Image;

import static cgtools.Vector.*;

public class Main {

    private static int sampling;

    private static Camera camera;
    private static Group group;

    public static void main(String[] args) {
        int width = 720;
        int height = 405;
        Image image = new Image(width, height);
        Image image2 = new Image(width, height);

        raytrace(new Camera(Math.PI / 2, width, height), genFirstObjects(), 100);

        writeNewImage(image, width, height);

        raytrace(new Camera(Math.PI / 2, width, height), genSecondObjects(), 100);

        writeNewImage(image, width, height);

        String filename = "doc/a04-scene.png";
        String filename2 = "doc/a06-mirrors-glass-2.png";

        try {
            image.write(filename);
            image2.write(filename2);
            System.out.println("Wrote image: " + filename);
            System.out.println("Wrote image: " + filename2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void raytrace(Camera c, Group g, int s) {
        camera = c;
        group = g;
        sampling = s;
    }

    private static Group genFirstObjects() {
        return new Group(
            new Sphere(2, direction(-8.5, 1, -25), new Metall(color(0.839, 0.443, 0.474), 0.2)),
            new Sphere(2, direction(0, 1, -10), new Metall(color(0.839, 0.658, 0.443), 0)),
            new Sphere(2, direction(8.5, 1, -25), new Metall(color(0.827, 0.839, 0.443), 0.2)),
            new Sphere(0.7, direction(-3, -0.3, -5), new MatColor(color(0.509, 0.839, 0.443))),
            new Sphere(0.7, direction(-1, -0.3, -5), new MatColor(color(0.443, 0.705, 0.839))),
            new Sphere(0.7, direction(1, -0.3, -5), new MatColor(color(0.721, 0.443, 0.839))),
            new Sphere(0.7, direction(3, -0.3, -5), new MatColor(color(0.839, 0.443, 0.756))),

            new Plane(direction(0,-1,0), direction(0, 1, 0), new MatColor(color(0.396, 0.498, 0.603))),
            new Background(new MatBackground())
        );
    }
    private static Group genSecondObjects() {
        return new Group(

                // new Sphere(0.35, direction(-4.5, -0.7, -8), new MatColor(color(0.839, 0.443, 0.474))),
                // new Sphere(1.2, direction(-4, -0.7, -8), new MatColor(color(0.839, 0.658, 0.443))),
                new Sphere(1.7, direction(-2, -0.3, -8), new MatColor(color(0.827, 0.839, 0.443))),
                new Sphere(2.0, direction(-0, -0.3, -8), new MatColor(color(0.509, 0.839, 0.443))),
                new Sphere(1.7, direction(2, -0.3, -8), new MatColor(color(0.443, 0.705, 0.839))),
                // new Sphere(1.2, direction(4, -0.7, -8), new MatColor(color(0.721, 0.443, 0.839))),
                // new Sphere(0.35, direction(4.5, -0.7, -8), new MatColor(color(0.839, 0.443, 0.756))),
                // new Sphere(0.5, direction(-0.8, 0.35, -2), new Glass(white)),

                // new Plane(direction(0,-1,0), direction(0, 1, 0), new MatColor(color(0.396, 0.498, 0.603))),
                new Background(new MatBackground())
        );
    }

    private static Color pixelColor(int x, int y) {
        Color bgColor = color(1);

        double n = Math.sqrt(sampling);
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Random.random();
                double ry = Random.random();
                double xs = x + ((xi + rx) / n);
                double ys = y + ((yi + ry) / n);
                bgColor = add(bgColor, pixelColor(xs, ys));
            }
        }

        bgColor = divide(bgColor, sampling);
        return bgColor;
    }

    private static Color radiance(Raytracer r, Shape g, int depth) {
        if (depth == 0) return black;

        Hit hit = g.intersect(r);

        Color emission = hit.material.emittedRadiance(r, hit);
        Raytracer scattered = hit.material.scatteredRay(r, hit);

        if (scattered != null) {
            return add(emission,
                    multiply(hit.material.albedo(r, hit), radiance(scattered, g, depth - 1)));
        } else return emission;
    }

    private static Color pixelColor(double x, double y) {
        Color bgColor;
        Raytracer r = camera.generateRay(x, y);
        bgColor = radiance(r, group, 5);
        return bgColor;
    }

    private static Image writeNewImage(Image image, int width, int height) {
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixelGammaTwo(x, y, pixelColor(x, y));
            }
        }
        return image;
    }
    
}

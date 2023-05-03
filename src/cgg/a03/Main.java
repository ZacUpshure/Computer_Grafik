package cgg.a03;

import cgtools.Random;
import cgtools.Vec3;
import cgg.Image;

import java.io.IOException;

import static cgtools.Vec3.add;
import static cgtools.Vec3.vec3;

public class Main {
    private static int width = 720;
    private static int height = 405;

    private static double samples = 100;

    //from color (first color)
    private static double r = 0.900;
    private static double g = 0.600;
    private static double b = 0.800;

    //from color (first color)
    private static double rs = 0.900;
    private static double gs = 0.600;
    private static double bs = 0.800;

    //to color (second color)
    private static double newr = 0.600;
    private static double newg = 0.900;
    private static double newb = 0.800;

    //find constants to change first color to second color
    private static double rr = (newr - r) / width;
    private static double gg = (newg - g) / width;
    private static double bb = (newb - b) / width;

    private static Camera camera;
    private static Kugel kugel;

    public static void main(String[] args) {
        Image image = new Image(width, height);
        camera = new Camera(Math.PI / 2, width, height);
        kugel = new Kugel(1, Vec3.vec3(0, 0, -2.30));

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

    private static Vec3 pixelColor(int x, int y) {
        Vec3 bgColor = vec3(r + x * rr, g + x * gg, b + x * bb);
        double n = Math.sqrt(samples);

        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Random.random();
                double ry = Random.random();
                double xs = x + ((xi + rx) / n);
                double ys = y + ((yi + ry) / n);
                Vec3 temp = pixelColor(xs, ys);
                bgColor = Vec3.add(bgColor, temp);
            }
        }
        bgColor = Vec3.divide(bgColor, samples);

        return bgColor;
    }

    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor = vec3(r + x * rr, g + x * gg, b + x * bb);

        Ray ray = camera.generateRay(x, y);
        Hit hit = kugel.intersect(ray);

        if (hit != null) bgColor = hit.getN();

        return bgColor;
    }
}

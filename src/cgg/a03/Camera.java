package cgg.a03;

import cgtools.Vec3;

/**
 * die Kamera.
 * Created by young on 05.11.2017.
 */
public class Camera {

    double phi;
    int width;
    int height;

    public Camera(double phi, int width, int height) {
        this.phi = phi;
        this.width = width;
        this.height = height;
    }

    public Ray generateRay(double x, double y) {
        double x1 = x - width / 2;
        double x2 = height / 2 - y;
        double x3 = -((width / 2) / Math.tan(phi / 2));

        return new Ray(Vec3.vec3(0, 0, 0), Vec3.normalize(new Vec3(x1, x2, x3)));
    }

    @Override
    public String toString() {
        return "Camera{" +
                "phi=" + phi +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public static void main(String[] args) {
        Camera c = new Camera(Math.PI / 2, 10, 10);
        System.out.println(c.generateRay(0, 0));
    }
}

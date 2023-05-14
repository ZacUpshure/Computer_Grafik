package cgg.a02;

import static cgtools.Vec3.vec3;

import java.io.IOException;
import java.util.ArrayList;

import cgtools.Random;
import cgtools.Vec3;
import cgg.Image;

public class Main {
	static int width = 480;
	static int height = 270;
	static ArrayList<Kreis> kreis = new ArrayList<Kreis>();
	private static int anzahl = 100;

	private static int sampling = 100;

	//from color (first color)
	private static double r = 0.900;
	private static double g = 0.600;
	private static double b = 0.800;


	//to color (second color)
	private static double newr = 0.600;
	private static double newg = 0.900;
	private static double newb = 0.800;

	//find constants to change first color to second color
	private static double rr = (newr - r) / width;
	private static double gg = (newg - g) / width;
	private static double bb = (newb - b) / width;

	public static void main(String[] args) {
		Image image = new Image(width, height);
		Image img = new Image(width, height);

		for (int i = 0; i < anzahl; i++) {
			kreis.add(new Kreis(width, height));
		}

		kreis.sort((a, b) -> Double.compare(b.getRad(), a.getRad()));

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				img.setPixel(x, y, oldPixelColor(x, y));
				image.setPixelGamma(x, y, pixelColor(x, y));
			}
		}

		String filename1 = "doc/a02-disks.png";
		String filename2 = "doc/a02-discs-supersampling.png";

		try {
			img.write(filename1);
			System.out.println("Wrote image: " + filename1);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing:%s: %s", filename1, error));
		}

        try {
            image.write(filename2);
            System.out.println("Wrote image: " + filename2);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename2, error));
        }

	}

	private static Vec3 pixelColor(int x, int y) {
		Vec3 bgColor = vec3(r + x * rr, g + x * gg, b + x * bb);
		Vec3 genColor;
		double n = Math.sqrt(sampling);
		for (Kreis k : kreis) {
			double z = 0;
			double cx = k.getPosx();
			double cy = k.getPosy();
			double r = k.getRad();
			for (int xi = 0; xi < n; xi++) {
				for (int yi = 0; yi < n; yi++) {
					double rx = Random.random();
					double ry = Random.random();
					double xs = x + (xi + rx) / n;
					double ys = y + (yi + ry) / n;
					double ds = Math.sqrt(Math.pow((cx - xs), 2) + Math.pow((cy - ys), 2));
					if (ds <= r) {
						z++;
					}
				}
			}
			double q = z / sampling;
			genColor = Vec3.multiply(q, k.getColor());
			bgColor = Vec3.multiply(1 - q, bgColor);
			bgColor = Vec3.add(genColor, bgColor);
		}
		return bgColor;
	}

	private static Vec3 oldPixelColor(int x, int y) {
		Vec3 bgColor = vec3(r + x * rr, g + x * gg, b + x * bb);
		for (Kreis k : kreis) {
			double cx = k.getPosx();
			double cy = k.getPosy();
			double r = k.getRad();
			double ds = Math.sqrt(Math.pow((cx - x), 2) + Math.pow((cy - y), 2));
			if (ds <= r) bgColor = k.getColor();
		}
		return bgColor;
	}
}
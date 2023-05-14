package cgg.a02;

import cgtools.Vec3;
import cgtools.Random;
import java.lang.Math;

public class Kreis implements Comparable<Kreis> {
	private double posx;
	private double posy;
	private Vec3 color;
	private double rad;

	private Random random = new Random();

	public Kreis(int width, int height) {
		posx = Math.abs(random.nextDouble()) * width;
		posy = Math.abs(random.nextDouble()) * height;
		color = Vec3.vec3(Random.random(),Random.random(),Random.random());
		rad = Math.abs(10 + random.nextInt(30) + random.nextDouble());
	}

	public double getPosx() {
		return posx;
	}

	public double getPosy() {
		return posy;
	}

	public Vec3 getColor() {
		return color;
	}

	public double getRad() {
		return rad;
	}

	@Override
	public int compareTo(Kreis ks) {
		if (this.getRad() < ks.getRad())
			return -1;
		if (this.getRad() > ks.getRad())
			return 1;
		return 0;
	}

}
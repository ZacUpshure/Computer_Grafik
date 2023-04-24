/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import java.io.IOException;

//import cgg.a02.ColoredDiscs;
import cgtools.*;

public class Image {

    private int width;
    private int height;
    private double colors[];
    private double[] d;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.colors = new double[width * height * 3];
  }

  public void setPixel(int x, int y, Color color) {
    int point = this.width * y + x;
    point = 3 * point;
    this.colors[point] = color.r();
    this.colors[point+1] = color.g();
    this.colors[point+2] = color.b();
  }

  public void write(String filename) throws IOException {
    ImageWriter.write(filename, colors, width, height);
    
  }

//   public void sample(Sampler s) {
//     for (int x = 0; x != width; x++) {
//       for (int y = 0; y != height; y++) {
//         setPixel(x, y, ColoredDiscs.pixelColor(x, y));
//       }
//     }
//   }

//   public void setPixelGamma(int x, int y, Color color) {
//     double gamma = 1 / 2.2;

//     double red = Math.pow(color.r(), gamma);
//     double green = Math.pow(color.g(), gamma);
//     double blue = Math.pow(color.b(), gamma);

//     colors[3 * (x + width * y)] = red;
//     colors[3 * (x + width * y) + 1] = green;
//     colors[3 * (x + width * y) + 2] = blue;

//   }

public void setPixel(int x, int y, Vec3 color) {
    colors[3 * (x + width * y)] = color.x;
    colors[3 * (x + width * y) + 1] = color.y;
    colors[3 * (x + width * y) + 2] = color.z;

    //System.out.println("setPixel: Image not yet implemented.");
}

    public void setPixelGamma(int x, int y, Vec3 color) {
        double gamma = 1 / 2.2;

        //System.out.println(x+", " +y);

        double red = Math.pow(color.x, gamma);
        double green = Math.pow(color.y, gamma);
        double blue = Math.pow(color.z, gamma);

        colors[3 * (x + width * y)] = red;
        colors[3 * (x + width * y) + 1] = green;
        colors[3 * (x + width * y) + 2] = blue;

        //System.out.println("setPixel: Image not yet implemented.");
    }

}
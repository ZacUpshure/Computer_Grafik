/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import javax.lang.model.util.ElementScanner14;

import cgtools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public record ConstantColor(Color color) implements Sampler {

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
      int ix = (int) x / 10;
      int iy = (int) y / 10;
      
      double u = (x / 10) - ix;
      double v = (y / 10) - iy;

      if ((ix + iy) % 2 == 0)
      if(u * v < 0.99)
        return Vector.red;
        else
        return Vector.green;
        else
        return Vector.black;
            // if ((x + y) % 2 == 0) {
            //   return Vector.gray;
            // } else {
            //   return Vector.black;
            // }
  }
}
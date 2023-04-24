package cgg.a01;
import cgtools.*;

public record Stripes(Color farbe1, Color farbe2, int size) implements Sampler {

    // Returns the color for the given position.
    public Color getColor(double x, double y) {
        int ix = (int) x;
        int iy = (int) y;
        
        if ((ix + iy)/10 % 2 == 0){  // create a checkerboard pattern
            return farbe1;
        }
        else {
            return farbe2;
        }
       
    }
  }
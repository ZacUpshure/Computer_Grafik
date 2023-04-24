/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import static cgtools.Vector.*;

import java.io.IOException;

import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 480;
    final int height = 270;

    // This class instance defines the contents of the image.
    ConstantColor content = new ConstantColor(gray);
    Stripes stripes = new Stripes(red, green, height);
    regurlarlyColor regurlarlyColor = new regurlarlyColor(red, green, height);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, stripes.getColor(x, y));
      }
    }
    
    Image image1 = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image1.setPixel(x, y, content.getColor(x, y));
      }
    }

    Image image2 = new Image(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image2.setPixel(x, y, regurlarlyColor.getColor(x, y));
      }
    }

    // Write the image to disk.
    final String filename = "doc/a01-pattern.png";
    try {
      image.write(filename);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Wrote image: " + filename);

    // Write the image to disk.
    final String filenameTwo = "doc/a01-image-1.png";
    try {
      image1.write(filenameTwo);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Wrote image: " + filenameTwo);

    // Write the image to disk.
    final String filenameThree = "doc/a01-image-2.png";
    try {
      image2.write(filenameThree);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Wrote image: " + filenameThree);
  }
}

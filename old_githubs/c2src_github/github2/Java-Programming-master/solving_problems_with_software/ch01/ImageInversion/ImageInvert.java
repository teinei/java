/**
 * Write a description of ImageInvert here.
 * 
 * @author Win Wu
 * @version 2015/12/06
 */

import edu.duke.*;
import java.io.*;

public class ImageInvert {
  public ImageResource makeInvert(ImageResource inImage) {
    ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
    //for each pixel in outImage
    for (Pixel pixel: outImage.pixels()) {
      //look at the corresponding pixel in inImage
      Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

      pixel.setRed(255 - inPixel.getRed());

      pixel.setGreen(255 - inPixel.getGreen());

      pixel.setBlue(255 - inPixel.getBlue());
    }
    //outImage is your answer
    return outImage;
  } 

  public void selectAndConvert () {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource inImage = new ImageResource(f);
      String fname = inImage.getFileName();
      String newFileName = "inverted-" + fname;
      ImageResource invertImage = makeInvert(inImage);
      invertImage.setFileName(newFileName);
      invertImage.draw();
      invertImage.save();
    }
  }
}

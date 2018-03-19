import edu.duke.*;
import java.io.*;

/**
 * Write a description of BatchGrayscale here.
 * 可一次開啟多個圖檔
 * 灰階處理所有所選取的圖片
 * 然後將所有圖片另存檔名 並已gray- 開頭
 * 
 * @author WinWu
 * @version 2015/12/0
 */
public class BatchGrayscale {

  /*public void doSave() {
      DirectoryResource dr = new DirectoryResource();
      // loop files
      for(File f : dr.selectedFiles()) {
          ImageResource image = new ImageResource(f);
          String fname = image.getFileName();
          String newName = "copy-" + fname;
          image.setFileName(newName);
          image.draw();
          image.save();
      }
  }
  */
  
  //I started with the image I wanted (inImage)
  public ImageResource makeGray(ImageResource inImage) {
	  //I made a blank image of the same size
	  ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
	  //for each pixel in outImage
	  for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
	  //outImage is your answer
	  return outImage;
  } 

  public void selectAndConvert () {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      ImageResource inImage = new ImageResource(f);
      String fname = inImage.getFileName();
      String newFileName = "gray-" + fname;
      ImageResource gray = makeGray(inImage);
      gray.setFileName(newFileName);
      gray.draw();
      gray.save();
    }
  }

}

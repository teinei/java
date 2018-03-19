//start with the foreground image you want (fgImage)
var fgImage = new SimpleImage("drewRobert.png");
//... and with the background image you want (bgImage)
var bgImage = new SimpleImage("dinos.png");
//Make a blank image of the same size (output)
var output = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());

//write code for each of these steps:
//for each pixel (currentPixel) in fgImage
for (var pixel of fgImage.values()) {
    //look at currentPixel and if it is green,
    if (pixel.getGreen() > pixel.getRed() + pixel.getBlue()) {
        //look at same position in bgImage
        var x = pixel.getX();
        var y = pixel.getY();
        var bgPixel = bgImage.getPixel(x, y);
        //and set output's corresponding pixel to bgImage's pixel
        output.setPixel(x, y, bgPixel);
    }
    //otherwise: set output's corresponding pixel to currentPixel
    else {
        output.setPixel(pixel.getX(), pixel.getY(), pixel);
    }
}
print(output);

package ui_base;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class ImageProcessor {

    // Converting image to black and white
    public static Image getBlackWhiteImage(Image image) {

        BufferedImage bufferedImage = toBufferedImage(image);

        for (int y = 0; y < bufferedImage.getHeight(); y++) {

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int pixel = bufferedImage.getRGB(x, y);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;

                // Normalize and gamma correct:
                double rr = Math.pow(r / 255.0, 2.2);
                double gg = Math.pow(g / 255.0, 2.2);
                double bb = Math.pow(b / 255.0, 2.2);

                // Calculate average
                int avg = (r+g+b)/3;

                // Replace RGB value with avg
                pixel = (a<<24) | (avg<<16) | (avg<<8) | avg;
                bufferedImage.setRGB(x, y, pixel);
            }
        }
        return bufferedImage;
    }

    // Converting image to inverted colors
    public static Image getInvertImage(Image image) {

        BufferedImage bufferedImage = toBufferedImage(image);

        for (int y = 0; y < bufferedImage.getHeight(); y++) {

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int pixel = bufferedImage.getRGB(x, y);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                pixel = (a<<24) | (r<<16) | (g<<8) | b;
                bufferedImage.setRGB(x, y, pixel);
            }
        }
        return bufferedImage;
    }


    // Converting image to sepia
    public static Image getSepiaImage(Image image) {

        BufferedImage bufferedImage = toBufferedImage(image);

        for (int y = 0; y < bufferedImage.getHeight(); y++) {

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int pixel = bufferedImage.getRGB(x, y);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;

                // Calculate tr, tg, tb
                int tr = (int)(0.393*r + 0.769*g + 0.189*b);
                int tg = (int)(0.349*r + 0.686*g + 0.168*b);
                int tb = (int)(0.272*r + 0.534*g + 0.131*b);

                // Check condition
                r = Math.min(tr, 255);
                g = Math.min(tg, 255);
                b = Math.min(tb, 255);

                pixel = (a<<24) | (r<<16) | (g<<8) | b;
                bufferedImage.setRGB(x, y, pixel);
            }
        }
        return bufferedImage;
    }





// Converting image into a BufferedImage

    /**
     * Copied from: https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = image.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return image;
    }
}

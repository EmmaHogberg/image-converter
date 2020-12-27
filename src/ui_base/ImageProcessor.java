package ui_base;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageProcessor {

    public static Image invertImage(Image image) {

        BufferedImage bufferedImage = toBufferedImage(image);

        for (int i = 0; i < bufferedImage.getHeight(); i++) {

            for (int j = 0; j < bufferedImage.getWidth(); j++) {
                int pixel = bufferedImage.getRGB(i, j);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                pixel = (a<<24) | (r<<16) | (g<<8) | b;
                bufferedImage.setRGB(i, j, pixel);
            }

        }
        return bufferedImage;
    }

    /**
     * Converts a given Image into a BufferedImage
     * Copied from: https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}

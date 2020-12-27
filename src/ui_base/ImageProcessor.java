package ui_base;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageProcessor {

    public static BufferedImage invertImage(Image image) {

        BufferedImage bufferedImage = (BufferedImage) image;

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
}

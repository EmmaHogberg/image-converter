package ui_base;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FileButtonListener implements ActionListener {

    JLabel originalImageLabel;
    JLabel changedImageLabel;

    public FileButtonListener(JLabel originalImageLabel, JLabel changedImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.changedImageLabel = changedImageLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Du tryckte på knappen för filer");
        String imagePath = "img/Elefant.jpg";
        originalImageLabel.setIcon(importImageLabel(imagePath));
        changedImageLabel.setIcon(importImageLabel(imagePath));
    }

    // Method for create the image and return it as a label
    private ImageIcon importImageLabel(String imagePath) {

        // Create image
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Calculate preferred image size
        double heightScale = Constantans.IMAGE_DIMENSION.getHeight() / imageIcon.getIconHeight();
        double widthScale = Constantans.IMAGE_DIMENSION.getWidth() / imageIcon.getIconWidth();
        double scale = Math.min(heightScale, widthScale);
        Dimension scaledDimension = new Dimension((int) Math.round(imageIcon.getIconWidth() * scale),
                (int) Math.round(imageIcon.getIconHeight() * scale));

        // Scale image
        Image scaledImage = imageIcon.getImage()
                .getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
        imageIcon.setImage(scaledImage);

        return imageIcon;
    }
}

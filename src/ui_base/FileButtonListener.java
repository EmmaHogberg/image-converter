package ui_base;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class FileButtonListener implements ActionListener {
    JLabel originalImageLabel;
    JLabel changedImageLabel;

    public FileButtonListener(JLabel originalImageLabel, JLabel changedImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.changedImageLabel = changedImageLabel;
    }

    // Listener for choosing image buttons
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Du tryckte på knapp för filer");
        String action = ae.getActionCommand();
        String imagePath = "img/Elefant.jpg";

        // Open a image on the computer
        if (action.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePath = selectedFile.getAbsolutePath();
            }
            originalImageLabel.setIcon(importImageLabel(imagePath));
            changedImageLabel.setIcon(importImageLabel(imagePath));
        }
        // Open a example image
        else if (action.equals("Example")) {
            originalImageLabel.setIcon(importImageLabel(imagePath));
            changedImageLabel.setIcon(importImageLabel(imagePath));
        }
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

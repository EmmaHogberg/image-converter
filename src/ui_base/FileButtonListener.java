package ui_base;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FileButtonListener implements ActionListener {

    JLabel originalImageLabel;
    JLabel changedImageLabel;

    public FileButtonListener(JLabel originalImageLabel, JLabel changedImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.changedImageLabel = changedImageLabel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Du tryckte på knappen för filer");

        String action = ae.getActionCommand();

        if (action.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            String imagePath = "img/Elefant.jpg";

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePath = selectedFile.getAbsolutePath();
            }
            originalImageLabel.setIcon(importImageLabel(imagePath));
            changedImageLabel.setIcon(importImageLabel(imagePath));
        }

        else if (action.equals("Save")) {
            JFrame parentFrame = new JFrame();

            ImageIcon icon = (ImageIcon) changedImageLabel.getIcon();
            Image image = icon.getImage();
            BufferedImage newImage = ImageProcessor.toBufferedImage(image);

            try {
                JFileChooser fileChooser = new JFileChooser();
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                fileChooser.setDialogTitle("Välj ett namn för bilden du vill spara");
                File fileToSave = fileChooser.getSelectedFile();

                File newImageToSave = new File(fileToSave.getAbsolutePath());
                ImageIO.write(newImage, "jpg", newImageToSave);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileChooser.setSelectedFile(newImageToSave);
                    File file = fileChooser.getSelectedFile();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

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

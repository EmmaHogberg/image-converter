package ui_base;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ImageProcessorButtonListener implements ActionListener {

    JLabel originalImageLabel;
    JLabel changedImageLabel;

    public ImageProcessorButtonListener(JLabel originalImageLabel, JLabel changedImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.changedImageLabel = changedImageLabel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        ImageIcon icon = (ImageIcon) originalImageLabel.getIcon();
        Image image = icon.getImage();

        String action = ae.getActionCommand();

        if (action.equals("BlackWhite")) {
            System.out.println("Svart!");
        }

        else if (action.equals("Inverted")) {
            System.out.println("Negativ!");
            image = ImageProcessor.invertImage(image);
            icon.setImage(image);
            changedImageLabel.setIcon(icon);
        }

        else if (action.equals("Sepia")) {
            System.out.println("Brunt!");
        }





    }
}

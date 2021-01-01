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
            System.out.println("Du tryckte på knappen för Svart");
            image = ImageProcessor.getGrayscaleImage(image);
        }

        else if (action.equals("Inverted")) {
            System.out.println("Du tryckte på knappen för negativ");
            image = ImageProcessor.getInvertImage(image);
        }

        else if (action.equals("Sepia")) {
            System.out.println("Du tryckte på knappen för brunt");
            image = ImageProcessor.getSepiaImage(image);

        }

        ImageIcon changedIcon = new ImageIcon(image);
        changedImageLabel.setIcon(changedIcon);





    }
}

package ui_base;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageProcessorButtonListener implements ActionListener {

    String imagePath = "";
    JLabel label;

    public ImageProcessorButtonListener(JLabel label) {
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        JLabel label = new JLabel();
        ImageIcon icon = (ImageIcon) label.getIcon();
        Image image = icon.getImage();

        System.out.println("Du tryckte på knappen för process");
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image originalImage = imageIcon.getImage();

        String action = ae.getActionCommand();

        if (action.equals("BlackWhite")) {
            System.out.println("Svart!");
        }

        else if (action.equals("Inverted")) {
            System.out.println("Negativ!");
            ImageProcessor.invertImage(originalImage);

        }

        else if (action.equals("Sepia")) {
            System.out.println("Brunt!");
        }





    }
}

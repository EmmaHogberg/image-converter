package ui_base;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageProcessorButtonListener implements ActionListener {

    String imagePath = "";

    public ImageProcessorButtonListener(String originalImage) {
        imagePath = originalImage;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Du tryckte på knappen för process");
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image originalImage = imageIcon.getImage();

        String action = ae.getActionCommand();

        if (action.equals("BlackWhite")) {

        }

        else if (action.equals("Inverted")) {

        }

        else if (action.equals("Sepia")) {
            
        }


    }
}

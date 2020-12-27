package ui_base;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class UserInterface implements Runnable {


    private JFrame frame;

    public UserInterface() {
    }


    @Override
    public void run() {

        frame = new JFrame();
        frame.setTitle("Bildomvandlare");
        frame.setPreferredSize(Constantans.FRAME_DIMENSION);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {

        JPanel imageContainer = new JPanel();
        GridLayout imageLayout = new GridLayout(1,2);
        imageContainer.setLayout(imageLayout);

        JPanel buttonsContainer = new JPanel();

        // Image labels
        String imagePath = "img/Elefant.jpg";
        JLabel originalImageLabel = importImageLabel(imagePath);
        JLabel changedImageLabel = new JLabel(originalImageLabel.getIcon());

        // Buttons for processing image
        JButton buttonBlackWhite = new JButton("Svart/Vit");
        JButton buttonInverted = new JButton("Invertera");
        JButton buttonSepia = new JButton("Sepiatoner");

        // Listen to processing buttons
        ImageProcessorButtonListener imageProcessorButtonListener = new ImageProcessorButtonListener(changedImageLabel);
        buttonBlackWhite.addActionListener(imageProcessorButtonListener);
        buttonBlackWhite.setActionCommand("BlackWhite");

        buttonInverted.addActionListener(imageProcessorButtonListener);
        buttonInverted.setActionCommand("Inverted");

        buttonSepia.addActionListener(imageProcessorButtonListener);
        buttonSepia.setActionCommand("Sepia");


        // Buttons for choosing and saving processed image
        JButton buttonChooseImage = new JButton("Välj bild");
        JButton buttonSave = new JButton("Spara bilden");

        // Listen to file buttons
        FileButtonListener fileButtonListener = new FileButtonListener();
        buttonChooseImage.addActionListener(fileButtonListener);
        buttonSave.addActionListener(fileButtonListener);

        // Set layout
        imageContainer.add(originalImageLabel);
        imageContainer.add(changedImageLabel);
        buttonsContainer.add(buttonChooseImage);
        buttonsContainer.add(buttonBlackWhite);
        buttonsContainer.add(buttonInverted);
        buttonsContainer.add(buttonSepia);
        buttonsContainer.add(buttonSave);
        container.add(imageContainer, BorderLayout.NORTH);
        container.add(buttonsContainer, BorderLayout.SOUTH);
    }


    private JLabel importImageLabel(String imagePath) {

        JLabel imageLabel = new JLabel();

        // Create image

        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(originalImage);

            // Calculate preferred image size
            double heightScale = Constantans.IMAGE_DIMENSION.getHeight() / imageIcon.getIconHeight();
            double widthScale = Constantans.IMAGE_DIMENSION.getWidth() / imageIcon.getIconWidth();
            double scale = Math.min(heightScale, widthScale);
            Dimension scaledDimension = new Dimension((int) Math.round(imageIcon.getIconWidth() * scale),
                    (int) Math.round(imageIcon.getIconHeight() * scale));

            // Scale image
            Image scaledImage = originalImage
                    .getScaledInstance(scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);
            imageIcon.setImage(scaledImage);
            imageLabel.setIcon(imageIcon);
            imageLabel.setPreferredSize(scaledDimension);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageLabel;
    }


    public JFrame getFrame() {
        return frame;
    }

}

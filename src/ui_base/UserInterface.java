package ui_base;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;



public class UserInterface implements Runnable {


    public UserInterface() {
    }

    @Override
    public void run() {

        JFrame frame = new JFrame();
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
        JLabel originalImageLabel = new JLabel();
        JLabel changedImageLabel = new JLabel();

        // Buttons for processing image
        JButton buttonBlackWhite = new JButton("Svart/Vit");
        JButton buttonInverted = new JButton("Invertera");
        JButton buttonSepia = new JButton("Sepiatoner");

        // Listen to processing buttons
        ImageProcessorButtonListener imageProcessorButtonListener = new ImageProcessorButtonListener(originalImageLabel, changedImageLabel);
        buttonBlackWhite.addActionListener(imageProcessorButtonListener);
        buttonBlackWhite.setActionCommand("BlackWhite");

        buttonInverted.addActionListener(imageProcessorButtonListener);
        buttonInverted.setActionCommand("Inverted");

        buttonSepia.addActionListener(imageProcessorButtonListener);
        buttonSepia.setActionCommand("Sepia");


        // Buttons for choosing and saving processed image
        JButton buttonChooseImage = new JButton("Välj bild");
        buttonChooseImage.setActionCommand("Open");
        JButton buttonSave = new JButton("Spara bilden");
        buttonSave.setActionCommand("Save");

        // Listen to file buttons
        FileButtonListener fileButtonListener = new FileButtonListener(originalImageLabel, changedImageLabel);
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
        container.add(imageContainer, BorderLayout.CENTER);
        container.add(buttonsContainer, BorderLayout.SOUTH);
    }
}

package ca.uwo.csd.cs2212.team09;

import java.awt.Color;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Implements the utility of the panels including buttons and style
 *
 * @author Team 9
 */
public class Utils {

    /**
     * Sets the style/colouring of the button
     *
     * @param btn button to be adjusted
     */
    public static void styleButton(JLabel btn) {
        btn.setOpaque(true);
        btn.setBackground(normalButtonColor());
        btn.setForeground(new Color(51, 51, 51));
    }

    /**
     * Sets the style/background of the panel
     *
     * @param panel the panel to be adjusted
     */
    public static void stylePanel(JPanel panel) {
        panel.setBackground(normalButtonColor());
    }

    /**
     * Create a square style button
     *
     * @param btn original button to be styled
     * @param image the image to be shown on the button
     * @param sideLength the size of the button
     */
    public static void styleSquareImageButton(JLabel btn, Image image, int sideLength) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(sideLength, sideLength, java.awt.Image.SCALE_SMOOTH));
        btn.setIcon(icon);
    }

    /**
     * Creates a style image
     *
     * @param btn original button to be styled
     * @param image the image to be shown on the button
     * @param width the width of the button
     * @param height the height of the button
     */
    public static void styleImage(JLabel btn, Image image, int width, int height) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
        btn.setIcon(icon);
    }

    /**
     * Stores the colour of the buttons
     *
     * @return the colour of the normal buttons
     */
    public static Color normalButtonColor() {
        return new Color(166, 171, 173);
    }

    
    /** Stores the image of the button
     * @param orig image of the button
     * @return a class storing the button and image
     */
    public static ImageIcon getImageIconForList(Image orig) {
       	return new ImageIcon(orig.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
    }
    
    /** Shows error messages
     * @param msg the message to be displayed
     */
    public static void showErrorMsg(String msg) {
    	JOptionPane.showMessageDialog(null, "Seems some error happened, beacuse " + msg);
    }
    
    /** Shows tips
     * @param msg the message to be displayed
     */
    public static void showTipsMsg(String msg) {
    	JOptionPane.showMessageDialog(null, msg);
    }
}

package io.github.wckq.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A custom button with a specified text, position, and action listener.
 */
public class CustomButton extends JButton {

    /**
     * Creates a new CustomButton with the specified text, position, and action listener.
     *
     * @param text the text of the button
     * @param x the x coordinate of the button
     * @param y the y coordinate of the button
     * @param actionListener the action listener for the button
     */
    public CustomButton(String text, int x, int y, ActionListener actionListener) {
        super(text);
        this.setBounds(x, y, 100, 40);
        this.addActionListener(actionListener);
    }
}

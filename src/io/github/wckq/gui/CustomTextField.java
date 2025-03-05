package io.github.wckq.gui;

import javax.swing.*;
import java.awt.*;

/**
 * A custom text field with a specified text, position, and font size.
 */
public class CustomTextField extends JTextField {

    /**
     * Creates a new CustomTextField with the specified text, position, and font size.
     *
     * @param text the text of the text field
     * @param x the x coordinate of the text field
     * @param y the y coordinate of the text field
     * @param size the font size of the text field
     */
    public CustomTextField(String text, int x, int y, int size) {
        super(text);
        this.setBounds(x, y, 380, 30);
        this.setFont(new Font("Segoe UI", Font.BOLD, size));
    }
}

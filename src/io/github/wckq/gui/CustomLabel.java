package io.github.wckq.gui;

import javax.swing.*;
import java.awt.*;

/**
 * A custom label with a specified text, position, and font size.
 */
public class CustomLabel extends JLabel {

    /**
     * Creates a new CustomLabel with the specified text, position, and font size.
     *
     * @param text the text of the label
     * @param x the x coordinate of the label
     * @param y the y coordinate of the label
     * @param size the font size of the label
     */
    public CustomLabel(String text, int x, int y, int size) {
        super(text);
        this.setBounds(x, y, 200, 50);
        this.setFont(new Font("Segoe UI", Font.BOLD, size));
    }
}

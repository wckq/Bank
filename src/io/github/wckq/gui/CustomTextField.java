package io.github.wckq.gui;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {

    public CustomTextField(String text, int x, int y, int size) {
        super(text);
        this.setBounds(x, y, 380, 30);
        this.setFont(new Font("Segoe UI", Font.BOLD, size));
    }
}

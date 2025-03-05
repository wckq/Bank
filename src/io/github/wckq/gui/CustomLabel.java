package io.github.wckq.gui;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public CustomLabel(String text, int x, int y, int size) {
        super(text);
        this.setBounds(x, y, 200, 50);
        this.setFont(new Font("Segoe UI", Font.BOLD, size));
    }
}

package io.github.wckq.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {

    public CustomButton(String text, int x, int y, ActionListener actionListener) {
        super(text);
        this.setBounds(x, y, 100, 40);
        this.addActionListener(actionListener);
    }
}

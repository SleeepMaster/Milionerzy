package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MilionareButton extends JButton {
    private Color pressedColor = Color.orange;
    private Color rolloverColor = Color.gray;
    private Color normalColor = Color.black;
    private Color borderColor = Color.blue;
    private Color textColor = Color.white;

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public void setRolloverColor(Color rolloverColor) {
        this.rolloverColor = rolloverColor;
    }

    public void setNormalColor(Color normalColor) {
        this.normalColor = normalColor;
    }

    public MilionareButton(String text) {
        super(text);
        setBorder(BorderFactory.createLineBorder(borderColor));
        setFocusPainted(false);

        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(normalColor);
        setForeground(textColor);
        setText(text);

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                    setForeground(normalColor);
                } else if (getModel().isRollover()) {
                    setBackground(rolloverColor);
                    setForeground(normalColor);
                } else {
                    setBackground(normalColor);
                    setForeground(textColor);
                }
            }
        });
    }
}



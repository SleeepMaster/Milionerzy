package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

/**
 * Created by SleepMaster on 2017-09-11.
 */
public class test extends JFrame {

    public test() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        getContentPane().setBackground(Color.black);
        setSize(450, 450);

        JButton button = new JButton("press");
        panel.add(button);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Float(200, 20, 80, 90));
        g2.draw(new Line2D.Float(40, 30, 90, 100));
        g2.clearRect(0,0,450,450);
        getContentPane().setBackground(Color.black);
        g2.draw(new Line2D.Float(300, 20, 80, 90));
    }

    public static void main(String[] args) {
        test s = new test();
        s.setVisible(true);
    }
}
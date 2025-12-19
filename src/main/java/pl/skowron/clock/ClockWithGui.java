package pl.skowron.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    public ClockWithGui() {
        new ClockThread().start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(getWidth() / 2, getHeight() / 2);

        for (int i = 1; i < 13; i++) {
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            Point2D src = new Point2D.Float(0, -120);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);
            g2d.drawString(Integer.toString(i), (int) trg.getX() - 5, (int) trg.getY() + 5);
        }

        for (int i = 0; i < 60; i++) {
            AffineTransform saveAT = g2d.getTransform();
            g2d.rotate(2 * Math.PI / 60 * i);
            if (i % 5 == 0) {
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, -140, 0, -150);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, -145, 0, -150);
            }
            g2d.setTransform(saveAT);
        }

        double second = time.getSecond();
        double minute = time.getMinute() + second / 60.0;
        double hour = time.getHour() + minute / 60.0;

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(hour % 12 * 2 * Math.PI / 12);
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(0, 0, 0, -80);
        g2d.setTransform(saveAT);

        saveAT = g2d.getTransform();
        g2d.rotate(minute * 2 * Math.PI / 60);
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(0, 0, 0, -110);
        g2d.setTransform(saveAT);

        saveAT = g2d.getTransform();
        g2d.rotate(second * 2 * Math.PI / 60);
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g2d.drawLine(0, 0, 0, -110);
        g2d.setTransform(saveAT);
    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            for (;;) {
                time = LocalTime.now();
                repaint();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


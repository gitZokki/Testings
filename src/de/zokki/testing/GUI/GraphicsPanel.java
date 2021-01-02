package de.zokki.testing.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Dimension preferredSize;

    public GraphicsPanel(int width, int height) {
	this.preferredSize = new Dimension(width, height);

	new Thread(() -> {
	    while (true) {
		repaint();
		try {
		    Thread.sleep((long) (Math.random() * 20));
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
    }

    @Override
    public Dimension getPreferredSize() {
	return preferredSize;
    }

    float x = 0f, y = 0f;
    int rotator = 1;

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	int xCenter = getWidth() / 2;
	int yCenter = getHeight() / 2;

	int xLine = (int) (xCenter + (xCenter - 25) * Math.sin(x * (2 * Math.PI / 60)));
	int yLine = (int) (yCenter - (yCenter - 25) * Math.cos(y * (2 * Math.PI / 60)));
	g.drawLine(xCenter, yCenter, xLine, yLine);

	x += 0.1;
	y += 0.1;

	for (float i = 0; i < 60; i += 0.5) {
	    int xPoint = (int) (xCenter + (xCenter - 25) * Math.sin(i * (2 * Math.PI / 60)));
	    int yPoint = (int) (yCenter - (yCenter - 25) * Math.cos(i * (2 * Math.PI / 60)));
	    if (yPoint + 25 == getHeight()) {
		g.setColor(Color.RED);
	    } else {
		g.setColor(Color.BLACK);
	    }
	    g.fillOval(xPoint, yPoint, getWidth() / 100, getHeight() / 100);
	}

    }
}

package de.zokki.testing.GUI;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import de.zokki.testing.Utils.OSValidator;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public GUI(String name, int width, int height) {
	super(name);
	setMinimumSize(new Dimension(width, height));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setFocusable(true);

	setContentPane(new GraphicsPanel(width, height));
	pack();
	setVisible(true);

	addWindowStateListener(new WindowStateListener() {
	    @Override
	    public void windowStateChanged(WindowEvent e) {
		if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
		    setFullScreen();
		}
	    }
	});

	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (getExtendedState() == JFrame.MAXIMIZED_BOTH && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    dispose();
		    setUndecorated(false);
		    setVisible(true);
		} else if (getExtendedState() != JFrame.MAXIMIZED_BOTH && e.getKeyCode() == KeyEvent.VK_F11) {
		    setFullScreen();
		}
	    }
	});
    }

    private void setFullScreen() {
	if (OSValidator.IS_UNIX) {
	    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    gd.setFullScreenWindow(this);
	} else {
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    dispose();
	    setUndecorated(true);
	    setVisible(true);
	}
    }
}

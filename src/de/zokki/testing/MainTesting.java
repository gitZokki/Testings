package de.zokki.testing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class MainTesting implements NativeKeyListener, NativeMouseListener, NativeMouseInputListener,
	NativeMouseWheelListener, NativeMouseMotionListener {

    private static BufferedOutputStream output;

    private DateFormat formatter = new SimpleDateFormat("dd.MM.YY HH:mm:ss.SSS");

    public static void main(String[] args) {
	// new GUI("TESTING", 500, 500);

	// LogManager.getLogManager().reset();

	// Get the logger for "org.jnativehook" and set the level to warning.
	Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	logger.setLevel(Level.WARNING);

	// Don't forget to disable the parent handlers.
	logger.setUseParentHandlers(false);
	try {
	    GlobalScreen.registerNativeHook();
	} catch (NativeHookException e) {
	    e.printStackTrace();
	}
	try {
	    output = new BufferedOutputStream(new FileOutputStream(new File("output.txt")));
	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	}
	GlobalScreen.addNativeKeyListener(new MainTesting());
	GlobalScreen.addNativeMouseListener(new MainTesting());
	GlobalScreen.addNativeMouseMotionListener(new MainTesting());
	GlobalScreen.addNativeMouseWheelListener(new MainTesting());
    }

    public static void write(byte[] toWrite) {
	try {
	    output.write(toWrite);
	    output.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
	write((getDate() + " - Key Pressed \t- " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "\n").getBytes());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
	write((getDate() + " - Key Released\t- " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "\n").getBytes());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
	write((getDate() + " - Key Typed\t- " + NativeKeyEvent.getKeyText(e.getKeyCode()) + "\n").getBytes());
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
	write((getDate() + " - Mouse Clicked\t- " + e.getButton() + "\n").getBytes());
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
	write((getDate() + " - Mouse Pressed\t- " + e.getButton() + "\n").getBytes());
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
	write((getDate() + " - Mouse Released\t- " + e.getButton() + "\n").getBytes());
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
	write((getDate() + " - Mouse Dragged\t- x:" + e.getX() + " y:" + e.getY() + "\n").getBytes());
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
	write((getDate() + " - Mouse Moved\t\t- x:" + e.getX() + " y:" + e.getY() + "\n").getBytes());
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
	write((getDate() + " - Mouse Wheel\t\t- " + e.getWheelRotation() + "\n").getBytes());
    }

    private String getDate() {
	return formatter.format(new Date());
    }
}

package bruton_rodriguez.controller;

import bruton_rodriguez.model.Browse;
import bruton_rodriguez.view.Window;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dylonrodriguez on 2/23/17.
 */
public class Listeners implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    private Object listener_type;

    public Listeners(Object listener_type) {
        this.listener_type = listener_type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Browse.loadPage("http://www.neumont.edu");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (listener_type instanceof JButton) {
            if (((JButton) listener_type).getName().equals("close")) {
                Browser_engine.getWindow().dispatchEvent(new WindowEvent(Browser_engine.getWindow(), WindowEvent.WINDOW_CLOSING));
            } else if (((JButton) listener_type).getName().equals("go")) {
                Platform.runLater(() -> Browse.loadPage("http://www.google.com"));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (listener_type instanceof JFrame) {
            ((JFrame) listener_type).setLocation(e.getLocationOnScreen());
        } else {
            System.out.println("mouse dragged");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

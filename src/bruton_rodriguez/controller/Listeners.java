package bruton_rodriguez.controller;

import bruton_rodriguez.model.Browse;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dylonrodriguez on 2/23/17.
 */
public class Listeners implements ActionListener, MouseListener, MouseMotionListener, KeyListener, WindowListener {
    private int px, py;

    @Override
    public void actionPerformed(ActionEvent e) {

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
        if (e.getComponent() instanceof JButton) {
            JButton t = (JButton) e.getComponent();
            if (t.getName().equals("go")) {
                Platform.runLater(() -> Browse.loadPage(Browser_engine.getUrl()));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        px = e.getXOnScreen();
        py = e.getYOnScreen();
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
        if (e.getComponent().getName().equals("title_bar")) {
            int x = e.getXOnScreen(), y = e.getYOnScreen();
            int dx = (int) (Browser_engine.getWindow().getLocationOnScreen().getX() + x - px);
            int dy = (int) (Browser_engine.getWindow().getLocationOnScreen().getY() + y - py);

            Browser_engine.getWindow().setLocation(dx, dy);

            px = x;
            py = y;
        } else {
            //System.out.println(e.getComponent().getName());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

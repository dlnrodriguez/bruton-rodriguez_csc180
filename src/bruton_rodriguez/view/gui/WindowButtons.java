package bruton_rodriguez.view.gui;

import bruton_rodriguez.controller.Browser_engine;
import bruton_rodriguez.developer.MSG;
import bruton_rodriguez.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import static bruton_rodriguez.E.dln;

/**
 * Created by dylonrodriguez on 3/3/17.
 */
public class WindowButtons extends JPanel implements MouseListener {
    private JButton close, maximize, minimize;
    private JButton[] buttons;

    WindowButtons() {
        super(new GridLayout(0, 3));
        initializeButtons();
        affectAllButtons();
    }

    private void initializeButtons() { // 590
        // close button
        close = new JButton("\u25A0"); // 02df 0fd5 2715
        close.setName("close_btn");

        // maximize button
        maximize = new JButton("\u25A0"); //11b7
        maximize.setName("max_btn");

        // minimize button
        minimize = new JButton("\u25A0");
        minimize.setName("min_btn");

        buttons = new JButton[]{minimize, maximize, close};
        defaults();
    }

    private void affectAllButtons() {
        for (JButton jb : buttons) {
            add(jb);
            jb.setOpaque(true);
            jb.setBorderPainted(false);
            jb.addMouseListener(this);
            jb.setBorder(null);
            jb.setMargin(new Insets(0, 0, 0, 0));
        }
    }

    private void defaults() {
        for (JButton jb : buttons) {
            if (!jb.getName().equals("close_btn")) {
                jb.setBackground(null);
                jb.setForeground(new Color(0x80_80_80));
            }
        }
        close.setForeground(new Color(0x52_22_22));
        close.setBackground(new Color(0xFF_C3_C0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "close_btn":
                dln.printm(MSG.SCSF, getClass(), "Successfully closed.");
                Browser_engine.getWindow().dispatchEvent(new WindowEvent(Browser_engine.getWindow(), WindowEvent.WINDOW_CLOSING));
                break;
            case "max_btn":
                switch (Browser_engine.getWindow().getExtendedState()) {
                    case JFrame.NORMAL:
                        Browser_engine.getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
                        break;
                    default:
                        Browser_engine.getWindow().setExtendedState(JFrame.NORMAL);
                }
                break;
            case "min_btn":
                Browser_engine.getWindow().setExtendedState(JFrame.ICONIFIED);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.getComponent().setBackground(e.getComponent().getBackground().darker());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        defaults();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "close_btn":
                close.setForeground(new Color(0x52_22_22));
                close.setBackground(new Color(0xE6_56_57));
                break;
            case "max_btn":
                maximize.setForeground(new Color(0x99_95_41));
                maximize.setBackground(new Color(0xFF_F0_D7));
                break;
            case "min_btn":
                minimize.setForeground(new Color(0x4FB44F));
                minimize.setBackground(new Color(0xE4_FF_E8));
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        defaults();
    }


}

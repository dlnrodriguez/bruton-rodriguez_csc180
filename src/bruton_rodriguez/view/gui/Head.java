package bruton_rodriguez.view.gui;

import bruton_rodriguez.controller.Browser_engine;
import bruton_rodriguez.developer.MSG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import static bruton_rodriguez.E.dln;

/**
 * Created by dylonrodriguez on 3/1/17.
 */
public class Head extends JPanel {
    private JLabel title;
    private Toolbar toolbar;

    public Head() {
        super(new BorderLayout());

        WindowButtons buttons = new WindowButtons();
        buttons.setPreferredSize(new Dimension(90, 30));
        buttons.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 6));

        add(buttons, BorderLayout.LINE_END);

        toolbar = new Toolbar();
        toolbar.setPreferredSize(new Dimension(50, 30));
        add(toolbar, BorderLayout.PAGE_END);
        initializeTitle();

        //setBackground(new Color(0x1A_FF_FF_FF, true));
    }

    private void initializeTitle() {
        title = new JLabel("\u0192lagellum");
        title.setPreferredSize(getSize());
        title.setFont(new Font(Font.SERIF, Font.BOLD, 12));
//        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0x9B9B9B));
        title.setName("title_bar");
        title.addMouseListener(Browser_engine.getListener());
        title.addMouseMotionListener(Browser_engine.getListener());
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(title, BorderLayout.CENTER);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getNextUrl() {
        return toolbar.get();
    }
}

class WindowButtons extends JPanel implements MouseListener {
    private JButton close, maximize, minimize;
    private JButton[] buttons;

    WindowButtons() {
        super(new GridLayout(0, 3));
        initializeButtons();
        affectAllButtons();
    }

    private void initializeButtons() { // 590
        // close button
        close = new JButton("\u2715"); // 02df 0fd5
        close.setName("close_btn");

        // maximize button
        maximize = new JButton("\u11b7"); //
        maximize.setName("max_btn");

        // minimize button
        minimize = new JButton("_");
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
                dln.printm(MSG.SUCC, getClass(), "Successfully closed.");
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
                maximize.setForeground(new Color(0x5F_5B_2F));
                maximize.setBackground(new Color(0xFF_F0_D7));
                break;
            case "min_btn":
                minimize.setForeground(new Color(0x3F_86_3F));
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

class Toolbar extends JPanel {
    private JTextPane url = new JTextPane();

    Toolbar() {
        super(new BorderLayout(5, 5));
        initialize();
        initializeGo();
    }

    private void initialize() {
        url.setSelectedTextColor(Color.magenta);
        url.setBackground(new Color(0xFF_FF_FF));
        url.addKeyListener(Browser_engine.getListener());
        url.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        url.setMargin(new Insets(0, 15, 0, 15));


        add(url, BorderLayout.CENTER);
    }

    private void initializeGo() {
        JButton go = new JButton(">");
        go.setName("go");
        go.setOpaque(true);
        go.setBorderPainted(false);
        go.setPreferredSize(new Dimension(90, 30));

        go.setBackground(new Color(0x3F_3F_3F));
        go.setForeground(new Color(0xF3_F3_F3));

        go.addMouseListener(Browser_engine.getListener());
        add(go, BorderLayout.LINE_END);
    }

    String get() {
        return url.getText();
    }
}


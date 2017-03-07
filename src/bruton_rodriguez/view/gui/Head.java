package bruton_rodriguez.view.gui;

import bruton_rodriguez.controller.Browser_engine;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylonrodriguez on 3/1/17.
 *
 * Top of the browser window.
 */

public class Head extends JPanel {
    private Toolbar toolbar;

    public Head() {
        super(new BorderLayout());
        initializeWindowManagementButtons();
        initializeTitle();
        initializeToolbar();
    }

    private void initializeWindowManagementButtons() {
        WindowButtons buttons = new WindowButtons();
        buttons.setPreferredSize(new Dimension(90, 30));
        buttons.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 6));
        this.add(buttons, BorderLayout.LINE_END);
    }

    JLabel title = new JLabel("\u0192lagellum");
    private void initializeTitle() {
        title.setName("title_bar");
        title.setVerticalAlignment(JLabel.CENTER);
        title.setPreferredSize(getSize());
        title.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        title.setForeground(new Color(0x9C_9C_9C));
        title.addMouseListener(Browser_engine.getListener());
        title.addMouseMotionListener(Browser_engine.getListener());
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.add(title, BorderLayout.CENTER);
    }

    private void initializeToolbar() {
        toolbar = new Toolbar();
        toolbar.setPreferredSize(new Dimension(50, 30));
        this.add(toolbar, BorderLayout.AFTER_LAST_LINE);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setTitle(String title) {
        this.title.setText(title);
        repaint();
    }
}


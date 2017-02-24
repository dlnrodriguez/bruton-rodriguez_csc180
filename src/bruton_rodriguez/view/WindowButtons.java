package bruton_rodriguez.view;

import bruton_rodriguez.controller.Listeners;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylonrodriguez on 2/24/17.
 */
public class WindowButtons extends JPanel {
    private JButton close, hide, min_max;

    public WindowButtons() {
        super(new GridLayout(1, 3));
        close = new JButton("X");
        hide = new JButton("#");
        min_max = new JButton("_");

        close.setName("close");
        close.addMouseListener(new Listeners(close));
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
        setPreferredSize(new Dimension(60, 20));
        setSize(60, 20);

        add(close);
        add(hide);
        add(min_max);
    }
}

package bruton_rodriguez.view;

import bruton_rodriguez.controller.Listeners;
import com.sun.java.swing.plaf.windows.WindowsIconFactory;
import sun.swing.ImageIconUIResource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylonrodriguez on 2/24/17.
 */
public class TaskBar extends JPanel {
    public TaskBar() {
        super(new GridLayout(1, 20));
        setMaximumSize(new Dimension(Window.getFrames()[0].getWidth(), 50));
        setBackground(new Color(0xA0_00_00_00, true));
        title();
        windowButtons();
    }

    private void title() {
        JLabel title = new JLabel(Window.getFrames()[0].getTitle().toUpperCase());
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        add(title, BorderLayout.CENTER);
    }

    private void windowButtons() {
        JButton close, hide, min_max;

        close = new JButton("X");
        hide = new JButton("#");
        min_max = new JButton("_");

        close.setName("close");
        min_max.setName("max");
        min_max.addMouseListener(new Listeners());
        close.addMouseListener(new Listeners());
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
        setPreferredSize(new Dimension(60, 20));
        setSize(60, 20);

        add(close, BorderLayout.WEST);
        add(hide, BorderLayout.WEST);
        add(min_max, BorderLayout.WEST);
    }
}

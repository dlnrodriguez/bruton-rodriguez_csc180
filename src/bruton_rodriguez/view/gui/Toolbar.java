package bruton_rodriguez.view.gui;

import bruton_rodriguez.controller.Browser_engine;
import bruton_rodriguez.developer.ERR;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static bruton_rodriguez.E.dln;

public class Toolbar extends JPanel {
    private JTextPane urlBar = new JTextPane();
    private JLabel icn = new JLabel();

    Toolbar() {
        super(new BorderLayout(5, 5));
        initialize();
    }

    private void initialize() {
        initializeUrlBar();
        initializeGo();
        initializeListeners();
    }

    private void initializeUrlBar() {

        // Sizing and spacing
        icn.setVerticalAlignment(SwingConstants.CENTER);
        icn.setHorizontalAlignment(SwingConstants.CENTER);
        icn.setPreferredSize(new Dimension(30, 30));
        icn.setBackground(Color.WHITE);
        urlBar.setMargin(new Insets(0, 0, 0, 15));

        // Color
        urlBar.setBackground(new Color(0xFF_FF_FF));
        urlBar.setForeground(new Color(0xAB_AB_AB));

        // Font
        urlBar.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        add(icn, BorderLayout.WEST);
        add(urlBar, BorderLayout.CENTER);
    }

    private void initializeGo() {
        JButton go = new JButton(">");
        go.setName("go");
        go.setOpaque(true);
        go.setBorderPainted(false);
        go.setPreferredSize(new Dimension(40, 30));
        go.setMargin(null);
        go.setBackground(new Color(0xBF_BF_BF));
        go.setForeground(new Color(0xF3_F3_F3));

        go.addMouseListener(Browser_engine.getListener());
        add(go, BorderLayout.LINE_END);
    }

    private void initializeListeners() {

        urlBar.addKeyListener(Browser_engine.getListener());
    }

    public String getUrlBar() {
        return urlBar.getText();
    }

    public void setIcn() {
        ImageIcon img;
        try {
            img = new ImageIcon(ImageIO.read(new URL("https://www.google.com/s2/favicons?domain=" + getUrlBar())));
            icn.setIcon(img);
        } catch (IOException ignore) {
            dln.printm(ERR.LOAD, getClass(), "Can't get input stream from URL, " + getUrlBar());
        } catch (NullPointerException ignore) {
            dln.printm(ERR.NULL, getClass(), "ImageIcon");
        }
    }
}

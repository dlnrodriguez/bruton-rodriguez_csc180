package bruton_rodriguez.view;

import bruton_rodriguez.controller.Browser_engine;
import bruton_rodriguez.controller.Listeners;
import bruton_rodriguez.view.gui.Head;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dylonrodriguez on 2/23/17.
 */
public class Window extends JFrame implements Runnable {
    public static WebEngine webEngine;
    private Page $page = new Page();
    private JTextField pageDetails;
    private JFXPanel webDisplay = new JFXPanel();
    private Head head = new Head();

    public Window() {
        init();
        addKeyListener(Browser_engine.getListener());
    }

    private boolean init() {
        pageDetails = new JTextField();
        setMinimumSize(new Dimension(800, 615));
        setTitle("null");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setBackground(new Color(0xA6_FF_FF_FF, true));
        addMouseMotionListener(Browser_engine.getListener());
        initialize_view();
        setResizable(true);
        setVisible(true);
        return false;
    }

    private void initialize_view() {
        Platform.runLater(this);
        add(head, BorderLayout.PAGE_START);
        add(webDisplay, BorderLayout.CENTER);
    }

    public String url() {
        return head.getNextUrl();
    }

    @Override
    public void run() {
        WebView webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.titleProperty().addListener((a, b, c) -> SwingUtilities.invokeLater(() -> head.setTitle(c)));

        //webEngine.setOnStatusChanged((e) -> SwingUtilities.invokeLater(() -> label.setText(e.getData())));
        webEngine.locationProperty().addListener((a, b, c) -> SwingUtilities.invokeLater(() -> pageDetails.setText(c)));
        webDisplay.setScene(new Scene(webView));
    }
}

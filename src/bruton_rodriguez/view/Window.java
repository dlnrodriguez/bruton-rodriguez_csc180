package bruton_rodriguez.view;

import bruton_rodriguez.controller.Listeners;
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
    private JLabel label;
    private JButton $closeButton;
    private JButton btn_go;
    private Page $page = new Page();
    private JTextField pageDetails;
    private JTextField addressBar;
    private JProgressBar progressBar;
    private JFXPanel webDisplay = new JFXPanel();

    public Window() {
        initCloseButton();
        init();
    }

    private boolean init() {
        pageDetails = new JTextField();
        setMinimumSize(new Dimension(800, 615));
        setTitle("New Browser");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLocationRelativeTo(null);
        setBackground(new Color(0xA6FFFFFF, true));
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        $closeButton.addMouseListener(new Listeners($closeButton));
        addMouseMotionListener(new Listeners(this));
        initialize_view();
        setVisible(true);
        return false;
    }

    private void initialize_view() {
        Platform.runLater(this);
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(250, 10));
        addressBar = new JTextField("address");
        btn_go = new JButton("go");
        btn_go.setName("go");
        btn_go.addMouseListener(new Listeners(btn_go));

        add(webDisplay);
        JPanel toolbar = new JPanel(new BorderLayout(5, 0));
        toolbar.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        toolbar.add(addressBar);
        toolbar.add(btn_go);

        JPanel detailsBar = new JPanel(new BorderLayout(5, 0));
        detailsBar.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        detailsBar.add(pageDetails, BorderLayout.WEST);
        detailsBar.add(progressBar, BorderLayout.CENTER);
        detailsBar.add(new WindowButtons());

        add(toolbar, BorderLayout.NORTH);
        //      add(webDisplay, BorderLayout.CENTER);
        add(detailsBar, BorderLayout.SOUTH);
    }

    private void initCloseButton() {
        $closeButton = new JButton("C");
        $closeButton.setName("close");
        $closeButton.setForeground(new Color(0xFF_E6_56_57, true));
        $closeButton.setBackground(new Color(0x00FFFFFF, true));
        //$closeButton.setBorderPainted(false);
        $closeButton.setBounds(getWidth() - 50, getHeight() - 50, 50, 50);
        $closeButton.setMaximumSize(new Dimension(20, 20));
        $closeButton.setPreferredSize(new Dimension(20, 20));
    }

    @Override
    public void run() {
        WebView webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.titleProperty().addListener((a, b, c) -> SwingUtilities.invokeLater(() -> setTitle(c)));

        //webEngine.setOnStatusChanged((e) -> SwingUtilities.invokeLater(() -> label.setText(e.getData())));
        webEngine.locationProperty().addListener((a, b, c) -> SwingUtilities.invokeLater(() -> pageDetails.setText(c)));
        webDisplay.setScene(new Scene(webView));
    }
}

package bruton_rodriguez.model;

import bruton_rodriguez.developer.ERR;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;

import static bruton_rodriguez.E.dln;

/**
 * Created by dylonrodriguez on 2/22/17.
 */
public class Tab {
    private WebView view;
    private WebEngine driver;

    public Tab(String url) {
//        try {
//            view = new WebView();
//            driver = view.getEngine();
//            driver.load(url);
//        } catch (ExceptionInInitializerError e) {
//            dln.printm(ERR.INIT, getClass(), "%s", e.getException().getMessage());
//        }
    }

    public WebEngine getPage() {
        return driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String get() {
        return driver.getLocation();
    }
}

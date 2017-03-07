package bruton_rodriguez.controller;

import bruton_rodriguez.developer.ERR;
import bruton_rodriguez.developer.Print;
import bruton_rodriguez.model.Browse;
import bruton_rodriguez.view.Window;

import java.util.concurrent.RejectedExecutionException;

/**
 * Handles instances of necessary components for
 * making the final application run.
 */
public class Browser_engine implements Runnable {
    private static Window window;
    private static Listeners listener;

    public void beginEngine() {
        Print out = new Print(Print.initialize("files/testing.txt"));
        out.printf("Hello world!");

        Browse browser = new Browse();
        try {
            run();
        } catch (RejectedExecutionException e) {
            out.printe(ERR.U404, "\t***\t[CAUSED BY: %s]\t***\n%s", e.getCause().toString().toUpperCase(), e.getMessage());
        }
        //browser.loadableOrSearchable("http://100.000.000.244");
        //browser.loadableOrSearchable("https://www.google.com/search?q=neumont+university&rlz=1C5CHFA_enUS717US717&oq=neumont+unive&aqs=chrome.0.0j69i60l3j0l2.7607j0j7&sourceid=chrome&ie=UTF-8");
    }

    @Override
    public void run() {
        listener = new Listeners();
        window = new Window();
        window.setTitle("New Browser");
    }

    public static Window getWindow() {
        return window;
    }

    public static Listeners getListener() {
        return listener;
    }

    public static String getUrl() {
        return window.getUrl();
    }
}

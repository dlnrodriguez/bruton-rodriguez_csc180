package bruton_rodriguez.controller;

import bruton_rodriguez.developer.Print;
import bruton_rodriguez.model.Browse;
import bruton_rodriguez.view.Window;

/**
 * Handles instances of necessary components for
 * making the final application run.
 */
public class Browser_engine implements Runnable {
    private static Window window;

    public void beginEngine() {
        Print out = new Print(Print.initialize("files/testing.txt"));
        out.printf("Hello world!");

        Browse browser = new Browse();
        run();
        browser.loadableOrSearchable("http://www.google.com/");
    }

    @Override
    public void run() {
        window = new Window();
        window.setTitle("New Browser");
    }

    public static Window getWindow() {
        return window;
    }
}

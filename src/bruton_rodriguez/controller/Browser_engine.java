package bruton_rodriguez.controller;

import bruton_rodriguez.developer.Print;
import bruton_rodriguez.model.Browser_browse;

/**
 * Handles instances of necessary components for
 * making the final application run.
 */
public class Browser_engine {
    public void beginEngine() {
        Print out = new Print(Print.initialize("files/testing.txt"));
        out.printf("Hello world!");
        Browser_browse browser = new Browser_browse();
    }
}

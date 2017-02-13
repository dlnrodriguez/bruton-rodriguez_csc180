package bruton_rodriguez.controller;

import bruton_rodriguez.developer.Print;

/**
 * Created by dylonrodriguez on 2/13/17.
 */
public class Browser_engine {
    public void setup(String fileName) {
        java.io.PrintStream stream = Print.initialize(fileName);
        if (stream == null) {
            System.err.println("could not continue");
            return;
        }
        Print out = new Print(stream);
        out.printf("Testing printf function.\n%d %.2f", 50, 9.5f);
    }
}

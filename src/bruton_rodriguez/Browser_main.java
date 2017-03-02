package bruton_rodriguez;

import bruton_rodriguez.controller.Browser_engine;

/**
 *
 */
public abstract class Browser_main {
    public static void main(String[] args) {
//        for (Character c = '\u0000' ; c < '\uffff'; c++) System.out.printf("%-4d %-4s | %c\n", (int) c, Integer.toHexString(c), c);
        new Browser_engine().beginEngine();
    }

    /* Possible Names //

    flagellum (name given by Brad Gustafson) \u03dd

     */
}

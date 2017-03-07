package bruton_rodriguez;

import bruton_rodriguez.controller.Browser_engine;
import bruton_rodriguez.model.Browse;

/**
 *
 */
public abstract class Browser_main {
    public static void main(String[] args) {
        new Browser_engine().beginEngine();

        /* Test:
        //Browse.getUrl("abc://username:password@example.com:123/path/data?key=value&key2=value2#fragid1");
        Browse.getUrl("http://www.apple.com");
        Browse.getUrl("http://www.google.com");
        Browse.getUrl("http://www.neumont.edu/");
        Browse.getUrl("http://www.twitter.com/POTUS");
        Browse.getUrl("https://www.twitter.com/realdonaldtrump");
        Browse.getUrl("www.twitter.com/ladygaga");
        Browse.getUrl("facebook.com");
        */
    }

    /* Possible Names

    flagellum (name given by Brad Gustafson) \u03dd

     */
}

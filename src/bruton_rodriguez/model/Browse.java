package bruton_rodriguez.model;

import bruton_rodriguez.developer.ERR;
import bruton_rodriguez.developer.MSG;

import java.net.MalformedURLException;
import java.net.URL;

import static bruton_rodriguez.E.dln;

/**
 * Retrieves and processes pages from the World
 * Wide Web.
 */
public class Browse {
    public Browse() {
        dln.printf("Browse was initialized.");
    }

    /*
    Returns true if the app is able to reach the
    specified web address.
     */
    public boolean loadPage(String webAddress) {
        // return false if the provided string is blank
        if (webAddress == null || webAddress.isEmpty() || webAddress.matches("\\s+")) return false;

        webAddress = webFormat(webAddress);

        try {
            URL url = new URL(webAddress);
            dln.printm(MSG.SUCC, getClass(), "Loaded page '%s' successfully!", url.toExternalForm());
            return true;
        } catch (MalformedURLException ignore) {
            dln.printm(ERR.LOAD, getClass(), "Cannot load '%s'", webAddress);
            return false;
        }
    }

    /*
    Returns true if the app is able to reach a
    search engine and search the provided query.
     */
    public boolean webSearch(String searchQuery) {
        String stringUrl = "http://www.google.com/#q=" + searchQuery.replace(' ', '+');

        try {
            URL url = new URL(stringUrl);
            dln.printm(MSG.SUCC, getClass(), "Successfully searched for \'%s\'", searchQuery);
        } catch (MalformedURLException e) {
            dln.printm(ERR.LOAD, getClass(), "Could not search for \'%s\', web address attempted: \'%s\'", searchQuery, stringUrl);
        }
        return true;
    }

    private String webFormat(String webAddress) {
        if (webAddress.matches("https?://www\\.\\S+\\.\\S+")) return webAddress;

        return "http://" + webAddress;
    }
}

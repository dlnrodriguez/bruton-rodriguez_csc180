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

        if (!webAddress.contains("http")) webAddress = "http://" + webAddress;

        try {
            URL url = new URL(webAddress);
            dln.printe(MSG.SUCC, "Loaded page '%s' successfully!", url.toExternalForm());
            return true;
        } catch (MalformedURLException ignore) {
            dln.printe(ERR.LOAD, "Cannot load '%s'", webAddress);
            return false;
        }
    }
}

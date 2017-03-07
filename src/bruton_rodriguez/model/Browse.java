package bruton_rodriguez.model;

import bruton_rodriguez.developer.ERR;
import bruton_rodriguez.developer.MSG;
import bruton_rodriguez.view.Window;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import static bruton_rodriguez.E.dln;

/**
 * Retrieves and processes pages from the World
 * Wide Web.
 */
public class Browse {
    private static OpenTabs openTabs = new OpenTabs();

    public Browse() {
        dln.printf("Browse was initialized.");
    }

    public void loadableOrSearchable(String s) {
        getUrl(s);
    }

    /**
     * Returns true if the app is able to reach the
     * specified web address.
     */
    public static void loadPage(String webAddress) {
        // return false if the provided string is blank
        if (webAddress == null || webAddress.isEmpty() || webAddress.matches("\\s+")) return;

        webAddress = getUrl(webAddress);

        try {
            URL url = new URL(webAddress);
            dln.printm(MSG.SCSF, Browse.class, "Loaded page '%s' successfully!", url.toExternalForm());
            openTabs.add(new Tab(webAddress));
            Window.webEngine.load(webAddress);
        } catch (MalformedURLException ignore) {
            dln.printm(ERR.LOAD, Browse.class, "Cannot load '%s'", webAddress);
        } catch (IllegalStateException ignore) {
            dln.printm(ERR.LOAD, Browse.class, "Not on FX application thread. Current thread: '%s', %d", Thread.currentThread().getName(), Thread.currentThread().getId());
        }
    }

    /**
     * Returns true if the app is able to reach a
     * search engine and search the provided query.
     */
    private boolean webSearch(String searchQuery) {
        String stringUrl = "http://www.google.com/#q=" + searchQuery.replace(' ', '+');

        try {
            new URL(stringUrl);
            dln.printm(MSG.SCSF, getClass(), "Successfully searched for \'%s\'", searchQuery);
        } catch (MalformedURLException e) {
            dln.printm(ERR.LOAD, getClass(), "Could not search for \'%s\', web address attempted: \'%s\'", searchQuery, stringUrl);
        }
        return true;
    }

    /**
     * Returns a reachable web address.
     */
    public static String getUrl(String webAddress) {
        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]

        // any combinations of letters, digits, plus,
        // period, and hyphen.
        String scheme = "(?<scheme>\\p{Alnum}[\\p{Alnum}+.\\-]+):";

        // username and password separated by a colon and
        // followed by an at symbol. (optional)
        String user_password = "((?<username>\\S+):(?<password>\\S+)@)?";

        // hostname or ip address. IPv4 must be in
        // dot-decimal notation. IPv6 must be enclosed in
        // brackets.
        String hostname = "(?<hostname>[\\p{LD}\\-.+]+)";
        String host_ipv4 = "(?<hostipv4>(\\d{1,3}\\.){3}\\d{1,3})";
        String host_ipv6 = "(?<hostipv6>(\\[\\d]))";

        String host = "(?<host>" + hostname + '|' + host_ipv4 + '|' + host_ipv6 + ")/?";
        //String host = "(?<host>" + hostname + ')';

        // port number. (optional)
        String port = "(:(?<portnumber>\\d{0,3}))?";

        //
        String path = "(/(?<path>((\\p{Alnum})/?)+))?";

        //
        String query = "(\\?(?<query>([\\p{LD}&;=])+))?";

        //
        String fragment = "(#(?<fragment>(\\S+)))?";

        //
        String authority = user_password + host + port;

        Pattern f = Pattern.compile('(' + scheme + "//)?" + authority + path + query + fragment);
        java.util.regex.Matcher matcher = f.matcher(webAddress);

        if (matcher.matches()) {
            dln.printm(MSG.SCSF, Browse.class, "Web url detected, " + webAddress + " [" +
                               "scheme='%s', " +
                               "username='%s', " +
                               "password='%s', " +
                               "host='%s', " +
                               "port number='%s', " +
                               "path='%s', " +
                               "query='%s', " +
                               "fragment='%s']"
                       //////////////////////////////
                    , matcher.group("scheme")
                    , matcher.group("username")
                    , matcher.group("password")
                    , matcher.group("host")
                    , matcher.group("portnumber")
                    , matcher.group("path")
                    , matcher.group("query")
                    , matcher.group("fragment"));

            if (matcher.group("scheme") == null) return "http://" + webAddress;
            return webAddress;
        }

        dln.printm(ERR.FRMT, Browse.class, "URL '%s'", webAddress);

        return null;
    }
}

package bruton_rodriguez.model;

import bruton_rodriguez.developer.ERR;
import bruton_rodriguez.developer.MSG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import static bruton_rodriguez.E.dln;

/**
 * Retrieves and processes pages from the World
 * Wide Web.
 */
public class Browse {
    public Browse() {
        dln.printf("Browse was initialized.");
    }

    public boolean loadableOrSearchable(String s) {
        if (s.contains(" ")) return webSearch(s);
        return loadPage(s);
    }

    /**
     * Returns true if the app is able to reach the
     * specified web address.
     */
    private boolean loadPage(String webAddress) {
        // return false if the provided string is blank
        if (webAddress == null || webAddress.isEmpty() || webAddress.matches("\\s+")) return false;

        webAddress = getUrl(webAddress);

        try {
            URL url = new URL(webAddress);
            dln.printm(MSG.SUCC, getClass(), "Loaded page '%s' successfully!", url.toExternalForm());
            return true;
        } catch (MalformedURLException ignore) {
            dln.printm(ERR.LOAD, getClass(), "Cannot load '%s'", webAddress);
            return false;
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
            dln.printm(MSG.SUCC, getClass(), "Successfully searched for \'%s\'", searchQuery);
        } catch (MalformedURLException e) {
            dln.printm(ERR.LOAD, getClass(), "Could not search for \'%s\', web address attempted: \'%s\'", searchQuery, stringUrl);
        }
        return true;
    }

    /**
     * Returns a reachable web address.
     */
    private String getUrl(String webAddress) {
        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]

        // any combinations of letters, digits, plus,
        // period, and hyphen.
        String scheme = "(?<scheme>\\p{Alnum}[\\p{Alnum}+.\\-]+)";

        // username and password separated by a colon and
        // followed by an at symbol. (optional)
        String user_password = "((?<username>\\S+):(?<password>\\S+)@)?";

        // hostname or ip address. IPv4 must be in
        // dot-decimal notation. IPv6 must be enclosed in
        // brackets.
        String hostname = "(?<hostname>\\S)";
        String host_ipv4 = "(?<hostipv4>(\\d{1,3}\\.){3}\\d{1,3})";
        String host_ipv6 = "(?<hostipv6>(\\[\\d]))";
        String host = hostname + '|' + host_ipv4 + '|' + host_ipv6;

        // port number. (optional)
        String port = "(?<portnumber>:\\d+)?";

        //
        String path = "(?<path>(\\S+)?)";

        //
        String query = "(?<query>(\\?\\S+)?)";

        //
        String fragment = "(?<fragment>(#\\S+)?)";

        //
        String authority = user_password + host + port;

        Pattern f = Pattern.compile(scheme + "(//)?" + authority + "/?" + path + query + fragment);
        Boolean matches = f.matcher(webAddress).matches();

        if (matches) return webAddress;

        java.util.regex.Matcher matcher = f.matcher(webAddress);

        dln.printm(ERR.FRMT, getClass(), "URL '%s'", webAddress);
        dln.printf("" +
                        "\tscheme = '%s'\n" +
                        "\tusername = '%s'\n" +
                        "\tpassword = '%s'\n" +
                        "\thost = '%s'" +
                        "\tport_number = '%s'\n" +
                        "\tpath = '%s'\n" +
                        "\tquery = '%s'\n" +
                        "\tfragment = '%s'\n"
                //////////////////////////////
                , matcher.group("scheme")
                , matcher.group("username")
                , matcher.group("password")
                , matcher.group("host")
                , matcher.group("portnumber")
                , matcher.group("path")
                , matcher.group("query")
                , matcher.group("fragment"));
        return "http://" + webAddress;
    }
}

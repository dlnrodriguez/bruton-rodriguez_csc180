package bruton_rodriguez;

import bruton_rodriguez.developer.ERR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Temporary class to keep track of errors for
 * each member in the group. Instance of
 * PrintStream for each member to avoid conflict
 * when committing changes to GitHub.
 */
public abstract class E {
    // ANJELIKA BRUTON
    public static LogForDevelopment anj = new LogForDevelopment().initialize("error_logs/bruton.anj");
    // DYLON RODRIGUEZ
    public static LogForDevelopment dln = new LogForDevelopment().initialize("error_logs/rodriguez.dln");

    /**
     * TO BE DELETED
     */
    public static class LogForDevelopment {
        private PrintStream stream;

        @SuppressWarnings("ResultOfMethodCallIgnored")
        private LogForDevelopment initialize(String fileName) {
            if (fileName.contains("/")) {
                Matcher matcher = Pattern.compile("(?<folder>.+/).+").matcher(fileName);
                matcher.matches();

                new File(matcher.group("folder")).mkdirs();
            }

            try {
                stream = new PrintStream(new FileOutputStream(fileName), true);
                return this;
            } catch (IOException e) {
                System.err.printf("File \'%s\' not found.", fileName);
                return null;
            }
        }

        public PrintStream printf(String format, Object... args) {
            return stream.printf(format + '\n', args);
        }

        public PrintStream printe(Enum e, String format, Object... args) {
            if (e instanceof ERR) stream.printf("> { %s } - ", e);
            else stream.printf("[ %s ] - ", e);
            return stream.printf(format + '\n', args);
        }
    }
}

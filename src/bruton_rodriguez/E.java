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
                dictionary();
                return this;
            } catch (IOException e) {
                System.err.printf("File \'%s\' not found.", fileName);
                return null;
            }
        }

        private void dictionary() {
            stream.println("/*");
            for (ERR e : ERR.values()) stream.printf(" * %s = %s\n", e.name(), e);
            stream.println(" */\n");
        }

        // print formatted
        public PrintStream printf(String format, Object... args) {
            return stream.printf(format + '\n', args);
        }

        // print message
        public PrintStream printm(Enum e, Class c, String format, Object... args) {
            // if the provided enum is a known error, give
            // it special formatting and provide the in
            // which the error occurred
            if (e instanceof ERR) stream.printf("* { %s, %s.class } - ", e.name(), c.getSimpleName());
            else stream.printf("[ %s ] - ", e.name());
            return stream.printf(format + '\n', args);
        }
    }
}

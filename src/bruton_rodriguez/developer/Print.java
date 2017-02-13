package bruton_rodriguez.developer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by dylonrodriguez on 2/13/17.
 *
 * Writes messages to a file.
 */
public class Print {
    private static PrintStream printStream;

    public Print(PrintStream stream) {
        printStream = stream;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static PrintStream initialize(String fileName) {
        if (fileName.contains("/")) {
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(?<folder>.+/).+").matcher(fileName);
            matcher.matches();
            new java.io.File(matcher.group("folder")).mkdir();
        }

        try {
            printStream = new PrintStream(new FileOutputStream(fileName), true);
            return printStream;
        } catch (FileNotFoundException e) {
            System.err.printf("File \'%s\' not found.", fileName);
            return null;
        }
    }

    public PrintStream printf(String format, Object ... args) {
        return printStream.printf(format, args);
    }

    public PrintStream printe(Enum e, String format, Object ... args) {
        printStream.printf("{ %s } - ", e);
        return printStream.printf(format, args);
    }
}

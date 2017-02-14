package bruton_rodriguez.developer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Writes messages to a single file.
 */
public class Print {
    private static PrintStream printStream;

    public Print(PrintStream stream) {
        printStream = stream;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static PrintStream initialize(String fileName) {
        // If "fileName" contains a folder name, sure that
        // the appropriate folders are created.
        if (fileName.contains("/")) {
            Matcher matcher = Pattern.compile("(?<folder>.+/).+").matcher(fileName);
            matcher.matches();
            new File(matcher.group("folder")).mkdirs();
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
        return printStream.printf(format + '\n', args);
    }

    public PrintStream printe(Enum e, String format, Object ... args) {
        if (e instanceof ERR) printStream.printf("> { %s } - ", e); // "ERR" is a known error type.
        else printStream.printf("[ %s ] - ", e);
        return printStream.printf(format + '\n', args);
    }
}
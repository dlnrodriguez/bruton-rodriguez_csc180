package bruton_rodriguez.developer;

/**
 * Enum that contains types of errors for easy
 * debugging.
 */
public enum ERR {
    NULL("Null Object"), U404("Unknown Error"), INIT("Initialization Error"), LOAD("Load Error"), FRMT("Formatting Error");

    private String msg;

    ERR(String string) {
        this.msg = string;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
